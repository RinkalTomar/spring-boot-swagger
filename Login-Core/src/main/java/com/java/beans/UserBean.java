
package com.java.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.java.enums.Enumeration;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
@ApiModel(description = "Captures identifying Information Who can logs into the Great Games Applicaiton")
public class UserBean extends AbstractAuditableBean {

    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    String userProfilePicture;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String emailCommunicationToken;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime tokenExpiryDate;

    Enumeration.UserAccountStatus status;

    Long usergroupId;
    UsergroupBean usergroup;

    Boolean emailVerified;

    public Long getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Long usergroup_id) {
        this.usergroupId = usergroup_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserProfilePictureUrl() {
        return userProfilePicture;
    }

    public void setUserProfilePictureUrl(String userProfilePictureUrl) {
        this.userProfilePicture = userProfilePictureUrl;
    }

    public Enumeration.UserAccountStatus getStatus() {
        return status;
    }
    public void setStatus(Enumeration.UserAccountStatus status) {
        this.status = status;
    }
    public Boolean getEmailVerified() {
        return emailVerified;
    }
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    public UsergroupBean getUsergroup() {
        return usergroup;
    }
    public void setUsergroup(UsergroupBean usergroup) {
        this.usergroup = usergroup;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getEmailCommunicationToken() {
        return emailCommunicationToken;
    }

    public void setEmailCommunicationToken(String emailCommunicationToken) {
        this.emailCommunicationToken = emailCommunicationToken;
    }

    public LocalDateTime getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(LocalDateTime tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
    }
}
