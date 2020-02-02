package com.java.exception;

public class IncompatibleCategoryException extends BadRequestException {

    public IncompatibleCategoryException(String message) {
        super(message);
    }
}
