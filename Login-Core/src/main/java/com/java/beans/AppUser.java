package com.java.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.enums.Enumeration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class AppUser implements UserDetails {


    Long id;
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    String userProfilePictureUrl;
    Boolean isActive;
    Set<PermissionsBean> permissions;
    String usergroup;
    Boolean deleted;

    public AppUser(UserBean u) {

        this.id = u.getId();
        this.username = u.getUsername();
        this.firstname = u.getFirstname();
        this.lastname = u.getLastname();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.userProfilePictureUrl = u.getUserProfilePictureUrl();
        this.isActive = u.getStatus() == Enumeration.UserAccountStatus.ACTIVE?true:false;
        this.permissions = u.getUsergroup().getAuthorities();
        this.usergroup = u.getUsergroup().getGroup();
        this.deleted = u.getIsDeleted();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (PermissionsBean permission : this.permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
        }
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return isActive;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public Long getId() {

        return id;
    }

    public String getUsergroup() {

        return usergroup;
    }

    public String getFirstname() {

        return firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public String getEmail() {

        return email;
    }

    public String getUserProfilePictureUrl() {

        return userProfilePictureUrl;
    }

}