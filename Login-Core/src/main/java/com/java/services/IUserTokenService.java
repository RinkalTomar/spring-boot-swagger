package com.java.services;


import com.java.beans.UserTokenBean;

public interface IUserTokenService {
    Long createEmailVerificationTokenRequest(Long user_id) throws Exception;

    Long createPasswordResetRequest(Long id, String verificationToken) throws Exception;

    // void verifyPasswordResetRequest(String token)throws Exception;


    UserTokenBean getUserTokenByToken(String token) throws Exception;

    void verifyRequest(String token) throws Exception;
}
