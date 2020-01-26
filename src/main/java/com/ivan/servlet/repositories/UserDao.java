package com.ivan.servlet.repositories;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.DaoException;
import com.ivan.servlet.exceptions.ServiceException;

public interface UserDao {

  User getUser(String email) throws ServiceException;

  User getUser(Integer id) throws ServiceException;

  User addUser(String email) throws ServiceException;

  boolean checkUserExists(String email) throws DaoException;

  boolean checkUserExists(Integer userId) throws DaoException;
}
