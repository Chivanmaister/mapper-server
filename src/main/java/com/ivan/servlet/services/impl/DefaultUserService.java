package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.InternalErrorException;
import com.ivan.servlet.exceptions.InvalidEmailException;
import com.ivan.servlet.exceptions.InvalidUserException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.Repository;
import com.ivan.servlet.repositories.UserDao;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.UserService;

public class DefaultUserService implements UserService {

  private RestService service;
  private Repository repository;

  public DefaultUserService(RestService service, Repository repository) {
    this.service = service;
    this.repository = repository;
  }

  @Override
  public void validateEmail(String email) throws ServiceException {
    if (email == null) {
      throw new InvalidEmailException("Invalid parameter for email");
    }
  }

  @Override
  public User getUser(String email) throws ServiceException {
    User user;
    try {
      user = repository.getRepository(UserDao.class).getUser(email);
    } catch (ServiceException e) {
      throw new InternalErrorException("Unknown internal error getting user with email = " + email);
    }
    return user;
  }

  @Override
  public User addUser(String email) throws ServiceException {
    User user;
    try {
      user = repository.getRepository(UserDao.class).addUser(email);
    } catch (ServiceException e) {
      throw new InternalErrorException("Unknown internal error adding user with email = " + email);
    }
    return user;
  }

  @Override
  public void validateUserExists(Integer userId) throws ServiceException {
    User user;
    try {
      user = repository.getRepository(UserDao.class).getUser(userId);
      if (user == null) {
        throw new InvalidUserException("User with id [" + userId + "] doesn't exists");
      }
    } catch (ServiceException e) {
      throw new InternalErrorException("Unknown internal error getting user with id = " + userId);
    }
  }
}
