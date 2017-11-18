package com.ivan.servlet.exceptions;

public class UserRepositoryException extends ServiceException {

    public UserRepositoryException() {
        super("Error getting user");
    }

    public UserRepositoryException(String message) {
        super(message);
    }
}
