package com.java.exception;

public class LoginAuthorizationException extends LoginException {

    public LoginAuthorizationException(String message) {
        super(message);
    }

    public LoginAuthorizationException()
    {
        super("You may not have the correct rights to access the requested resource.");
    }
}
