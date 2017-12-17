package com.ivan.servlet.exceptions;

public class InvalidEmailException extends ServiceException {

    public InvalidEmailException(String message) {
        super(ErrorCodes.INVALID_EMAIL, message);
    }

    public InvalidEmailException() {
        super(ErrorCodes.INVALID_EMAIL, "Invalid email");
    }
}
