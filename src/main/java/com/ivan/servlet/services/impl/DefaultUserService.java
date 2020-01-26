package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.*;
import com.ivan.servlet.repositories.DefaultRepository;
import com.ivan.servlet.repositories.UserDao;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.UserService;

public class DefaultUserService implements UserService {

  private RestService service;
  private DefaultRepository defaultRepository;

  public DefaultUserService(RestService service, DefaultRepository defaultRepository) {
    this.service = service;
    this.defaultRepository = defaultRepository;
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
      user = defaultRepository.getRepository(UserDao.class).getUser(email);
    } catch (ServiceException e) {
      throw new InternalErrorException("Unknown internal error getting user with email = " + email);
    }
    return user;
  }

  @Override
  public User getUser(Integer userId) throws ServiceException {
    User user;
    try {
      user = defaultRepository.getRepository(UserDao.class).getUser(userId);
    } catch (ServiceException e) {
      throw new InternalErrorException("Unknown internal error getting user with id = " + userId);
    }
    return user;
  }

  @Override
  public User addUser(String email) throws ServiceException {
    User user;
    try {
      user = defaultRepository.getRepository(UserDao.class).addUser(email);
    } catch (ServiceException e) {
      throw new InternalErrorException("Unknown internal error adding user with email = " + email);
    }
    return user;
  }

  @Override
  public void validateUserExists(Integer userId) throws ServiceException {
    boolean exists;
    try {
      exists = defaultRepository.getRepository(UserDao.class).checkUserExists(userId);
    } catch (DaoException e) {
      throw new InternalErrorException("Unknown internal error getting user with id = " + userId);
    }
    if (!exists) {
      throw new UserNotExistsException("User with id [" + userId + "] doesn't exists");
    }
  }

  @Override
  public void validateUserExists(String email) throws ServiceException {
    boolean exists;
    try {
      exists = defaultRepository.getRepository(UserDao.class).checkUserExists(email);
    } catch (DaoException de) {
      String message = "Unknown internal error getting user with email = " + email;
      throw new InternalErrorException(message);
    }
    if (!exists) {
      throw new UserNotExistsException("User with email [" + email + "] doesn't exists");
    }
  }
}
