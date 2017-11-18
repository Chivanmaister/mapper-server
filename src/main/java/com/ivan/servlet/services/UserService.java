package com.ivan.servlet.services;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.InternalErrorException;
import com.ivan.servlet.exceptions.ServiceException;

public interface UserService {

    void validateEmail(String email) throws ServiceException;

    User getUser(String email) throws ServiceException;

    User addUser(String email) throws ServiceException;

    void validateUserExists(Integer userId) throws ServiceException;
}
