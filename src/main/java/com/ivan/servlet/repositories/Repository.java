package com.ivan.servlet.repositories;

import com.ivan.servlet.exceptions.ServiceException;

public interface Repository {

  <T> T getRepository(Class<T> repositoryClass) throws ServiceException;
}
