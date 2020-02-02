package com.java.beans;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class CurrentUser {

    Long id;
    String username;
    String firstname;
    String lastname;
    String email;
    Boolean isActive;
    String usergroup;
    Set<String> authorities = new HashSet<>();

    public CurrentUser(AppUser appuser) {
        this.id = appuser.getId();
        this.email = appuser.getEmail();
        this.firstname = appuser.getFirstname();
        this.lastname = appuser.getLastname();
        this.isActive = appuser.isAccountNonLocked();
        this.usergroup = appuser.getUsergroup();
        this.username = appuser.getUsername();
        for (GrantedAuthority authority : appuser.getAuthorities()) {
            this.authorities.add(authority.getAuthority());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
