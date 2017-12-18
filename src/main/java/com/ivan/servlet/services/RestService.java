package com.ivan.servlet.services;

import com.ivan.servlet.entities.details.History;
import com.ivan.servlet.exceptions.InvalidServiceException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.Repository;
import com.ivan.servlet.services.impl.DefaultCoordinateService;
import com.ivan.servlet.services.impl.DefaultHistoryService;
import com.ivan.servlet.services.impl.DefaultRouteService;
import com.ivan.servlet.services.impl.DefaultUserService;

import java.util.HashMap;
import java.util.Map;

public class RestService {

  private Map<Class, Object> serviceImpl = new HashMap<>();

  public RestService() {
    serviceImpl.put(UserService.class, new DefaultUserService(this, new Repository()));
    serviceImpl.put(RouteService.class, new DefaultRouteService(this, new Repository()));
    serviceImpl.put(CoordinateService.class, new DefaultCoordinateService(this, new Repository()));
    serviceImpl.put(HistoryService.class, new DefaultHistoryService(this, new Repository()));
  }

  @SuppressWarnings("unchecked")
  public <T> T getService(Class<T> serviceClass) throws ServiceException {
    if (!serviceImpl.containsKey(serviceClass)) {
      throw new InvalidServiceException("Service not found for class = " + serviceClass.toString());
    }
    return (T) serviceImpl.get(serviceClass);
  }
}
