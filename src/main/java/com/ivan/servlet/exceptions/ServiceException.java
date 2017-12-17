package com.ivan.servlet.exceptions;

public class ServiceException extends Exception {

    private int errorCode;

    public ServiceException() {
        super("Error in service layer");
    }

    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
