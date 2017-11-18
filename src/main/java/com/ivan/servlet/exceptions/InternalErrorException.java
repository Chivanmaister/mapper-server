package com.ivan.servlet.exceptions;

public class InternalErrorException extends ServiceException {

    public InternalErrorException() {
        super("Internal error");
    }

    public InternalErrorException(String message) {
        super(message);
    }
}
