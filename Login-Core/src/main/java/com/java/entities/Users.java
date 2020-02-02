package com.java.entities;


import com.java.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Users extends AbstractAuditableEntity {

    @Column(nullable = false, unique = true)
    String username;
    String password;
    String firstname;
    String lastname;

    String emailCommunicationToken;
    LocalDateTime tokenExpiryDate;


    @Column(nullable = false, unique = true,length = 100)
    String email;

    String userProfilePicture;

    @Enumerated(EnumType.STRING)
    Enumeration.UserAccountStatus status;

    Long usergroupId;

    @ManyToOne(targetEntity = Usergroup.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usergroupId", nullable = false, insertable = false, updatable = false)
    Usergroup usergroup;

    Boolean emailVerified;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userId")
    List<UserToken> userTokens;

}
