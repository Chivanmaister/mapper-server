package com.ivan.servlet.exceptions;

public class InvalidUserException extends ServiceException {

    public InvalidUserException() {
        super(ErrorCodes.INVALID_USER, "Invalid user");
    }

    public InvalidUserException(String message) {
        super(ErrorCodes.INVALID_USER, message);
    }
}
