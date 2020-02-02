package com.java.beans.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAcountResetOrActivateRequest {

    String email;
    String password;
    String emailCommunicationToken;
}
