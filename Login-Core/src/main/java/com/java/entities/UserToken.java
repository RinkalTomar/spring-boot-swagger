package com.java.entities;


import com.java.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserToken extends AbstractAuditableEntity {

    @Column(nullable = false)
    String token;

    @Column(nullable = false)
    LocalDateTime token_expiry;
    Boolean isExpired;

    @Enumerated(EnumType.STRING)
    Enumeration.UserTokenType tokenType;

    Long userId;

    @ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    Users user;


}
