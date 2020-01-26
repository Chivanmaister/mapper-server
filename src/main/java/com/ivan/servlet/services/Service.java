package com.ivan.servlet.services;

import com.ivan.servlet.exceptions.ServiceException;

public interface Service {

  <T> T getService(Class<T> serviceClass) throws ServiceException;
}
