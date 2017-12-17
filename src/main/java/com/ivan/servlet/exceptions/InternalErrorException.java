package com.ivan.servlet.exceptions;

public class InternalErrorException extends ServiceException {

    public InternalErrorException() {
        super(ErrorCodes.INTERNAL_ERROR, "Internal error");
    }

    public InternalErrorException(String message) {
        super(ErrorCodes.INTERNAL_ERROR, message);
    }
}
