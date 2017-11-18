package com.ivan.servlet.exceptions;

public class InvalidNameException extends ServiceException {

    public InvalidNameException() {
        super("Invalid name");
    }

    public InvalidNameException(String message) {
        super(message);
    }
}
