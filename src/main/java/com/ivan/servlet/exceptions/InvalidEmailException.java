package com.ivan.servlet.exceptions;

public class InvalidEmailException extends ServiceException {

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException() {
        super("Invalid email");
    }
}
