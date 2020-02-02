package com.java.config.AuthorizationServer;

import com.java.beans.response.AbstractResponse;
import com.java.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Configuration
@EnableAuthorizationServer
public class AuthorizatioServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Qualifier("authUserDetailService")
    @Autowired
    UserDetailsService _userDetailService;

    @Autowired
    PasswordEncoder oauthClientPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(oauthClientPasswordEncoder);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient(Constants.CLIENT_ID)
                .secret(Constants.CLIENT_SECRET)
                .authorizedGrantTypes(Constants.GRANT_TYPE_CLIENT_REFRESH_TOKEN, Constants.GRANT_TYPE_PASSWORD)
                .autoApprove(true)
                .scopes(Constants.SCOPE_READ, Constants.SCOPE_TRUST, Constants.SCOPE_WRITE)
                .resourceIds(Constants.RESOURCE_ID)
                .accessTokenValiditySeconds(Constants.ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(Constants.REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {

        endpointsConfigurer.authenticationManager(authenticationManager).userDetailsService(_userDetailService)
                .tokenEnhancer(new OauthTokenEnhancer())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                .exceptionTranslator(loggingExceptionTranslator())
                .reuseRefreshTokens(false);

    }

    @Bean
    public WebResponseExceptionTranslator loggingExceptionTranslator() {

        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                // This is the line that prints the stack trace to the log. You can customise this to format the trace etc if you like
                e.printStackTrace();
                // Carry on handling the exception
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                OAuth2Exception excBody = responseEntity.getBody();
                AbstractResponse errorResponse = new AbstractResponse();
                errorResponse.setSuccess(false);
                errorResponse.getError().put(excBody.getOAuth2ErrorCode(), excBody.getMessage());
                errorResponse.setAuthenticated(false);
                return new ResponseEntity(errorResponse, headers, responseEntity.getStatusCode());
            }
        };
    }
}
