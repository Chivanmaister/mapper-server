package com.ivan.servlet.exceptions;

public class InvalidClassException extends ServiceException {

    public InvalidClassException() {
        super("Unknown class");
    }

    public InvalidClassException(String message) {
        super(message);
    }
}
