package com.java.beans;

import com.java.enums.Enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenBean extends AbstractAuditableBean{

    public UserTokenBean(String token, LocalDateTime expiry, Enumeration.UserTokenType type, Long userId)
    {
        this.token = token;
        this.token_expiry = expiry;
        this.userId = userId;
        this.tokenType = type;
    }

    String token;
    LocalDateTime token_expiry;
    Boolean isExpired = false;
    Enumeration.UserTokenType tokenType;
    Long userId;

    UserBean user;



}
