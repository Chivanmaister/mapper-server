package com.ivan.servlet.exceptions;

public class InvalidMethodException extends ServiceException {

    public InvalidMethodException() {
        super(ErrorCodes.INTERNAL_ERROR, "Invalid method");
    }

    public InvalidMethodException(String message) {
        super(ErrorCodes.INTERNAL_ERROR, message);
    }
}