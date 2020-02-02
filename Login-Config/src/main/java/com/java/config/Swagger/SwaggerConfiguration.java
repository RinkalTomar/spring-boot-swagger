package com.java.config.Swagger;




import com.java.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfiguration {


   /* @Value("${context.path}")
    String contextPath;
*/
    @Bean
    public Docket greatsgamesApi() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(new ResponseMessageBuilder().code(500).message("500 message")
                         .responseModel(new ModelRef("Result")).build());
        list.add(new ResponseMessageBuilder().code(401).message("Unauthorized")
                         .responseModel(new ModelRef("Result")).build());
        list.add(new ResponseMessageBuilder().code(406).message("Not Acceptable")
                         .responseModel(new ModelRef("Result")).build());

        return new Docket(DocumentationType.SWAGGER_2)
                       .select().apis(RequestHandlerSelectors.basePackage("com.java"))
                       .paths(PathSelectors.any())
                       .build()
                       .directModelSubstitute(LocalDate.class, String.class)
                       .genericModelSubstitutes(ResponseEntity.class)
                       .securitySchemes(Collections.singletonList(securitySchema()))
                       .securityContexts(Collections.singletonList(securityContext())).pathMapping("/")
                       .useDefaultResponseMessages(false).apiInfo(apiInfo()).globalResponseMessage(RequestMethod.GET, list)
                       .globalResponseMessage(RequestMethod.POST, list)
                       .apiInfo(apiInfo());
    }

    @Bean
    public OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        authorizationScopeList.add(new AuthorizationScope(Constants.SCOPE_READ, "read all"));
        authorizationScopeList.add(new AuthorizationScope(Constants.SCOPE_TRUST, "trust all"));
        authorizationScopeList.add(new AuthorizationScope(Constants.SCOPE_WRITE, "access all"));

        List<GrantType> grantTypes = new ArrayList();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant( "/oauth/token");
        grantTypes.add(creGrant);

        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {

        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/**"))
                       .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfo(
                "Login REST APIs",
                "REST APIs" +
                        ", Developed by Rinkal Tomar",
                "1.0",
                "Terms of service",
                new Contact("ABC",
                        "www.example.com",
                        "ABC@gmail.com"),
                "Copyright 2017 - 2018 Login, All rights reserved.",
                " ",
                Collections.emptyList());
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope(Constants.SCOPE_READ, "read all");
        authorizationScopes[1] = new AuthorizationScope(Constants.SCOPE_TRUST, "trust all");
        authorizationScopes[2] = new AuthorizationScope(Constants.SCOPE_WRITE, "write all");

        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration securityInfo() {

        return SecurityConfigurationBuilder.builder()
                       .clientId(Constants.CLIENT_ID)
                       .scopeSeparator(",")
                       .clientSecret(Constants.CLIENT_SECRET_PLAIN)
                       .realm(Constants.RESOURCE_ID)
                       .useBasicAuthenticationWithAccessCodeGrant(true)
                       .appName("Login CMS")
                       .additionalQueryStringParams(null)
                       .build();
    }
}
