package com.java.config.AuthorizationServer;


import com.java.beans.AppUser;
import com.java.constants.Constants;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class OauthTokenEnhancer implements TokenEnhancer {

    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        if (oAuth2Authentication.getOAuth2Request().getGrantType().equals(Constants.GRANT_TYPE_PASSWORD)) {
            AppUser user = (AppUser) oAuth2Authentication.getUserAuthentication().getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>();
            DefaultExpiringOAuth2RefreshToken refreshToken = (DefaultExpiringOAuth2RefreshToken) oAuth2AccessToken.getRefreshToken();
            additionalInfo.put("access_token_expires_on", oAuth2AccessToken.getExpiration().getTime());
            additionalInfo.put("refresh_token_expires_on", refreshToken.getExpiration().getTime());
            additionalInfo.put("user", user);
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        }

        return oAuth2AccessToken;
    }
}
