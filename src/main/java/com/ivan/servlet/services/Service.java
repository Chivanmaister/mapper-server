package com.ivan.servlet.services;

import com.ivan.servlet.exceptions.ServiceException;

public interface Service {

  public <T> T getService(Class<T> serviceClass) throws ServiceException;
}
