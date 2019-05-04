package com.ivan.servlet.services;

import com.ivan.servlet.exceptions.InvalidServiceException;
import com.ivan.servlet.exceptions.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class RestService implements Service {

  private Map<String, Object> services = new HashMap<>();

  public void addService(String name, Object instance) {
    services.put(name, instance);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T getService(Class<T> serviceClass) throws ServiceException {
    if (!services.containsKey(serviceClass.getName())) {
      throw new InvalidServiceException("Service not found for class = " + serviceClass.toString());
    }
    return (T) services.get(serviceClass.getName());
  }
}
