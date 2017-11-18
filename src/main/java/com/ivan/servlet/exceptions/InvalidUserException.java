package com.ivan.servlet.exceptions;

public class InvalidUserException extends ServiceException {

    public InvalidUserException() {
        super("Invalid user");
    }

    public InvalidUserException(String message) {
        super(message);
    }
}
