package com.java.exception;

public class BadRequestException extends LoginException {

    public BadRequestException(String message) {
        super(message);
    }
}
