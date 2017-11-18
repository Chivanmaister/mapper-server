package com.ivan.servlet.exceptions;

public class InvalidMethodException extends ServiceException {

    public InvalidMethodException() {
        super("Invalid method");
    }

    public InvalidMethodException(String message) {
        super(message);
    }
}