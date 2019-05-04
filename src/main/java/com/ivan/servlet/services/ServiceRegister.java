package com.ivan.servlet.services;

import com.ivan.servlet.repositories.DefaultRepository;
import com.ivan.servlet.services.impl.DefaultCoordinateService;
import com.ivan.servlet.services.impl.DefaultHistoryService;
import com.ivan.servlet.services.impl.DefaultRouteService;
import com.ivan.servlet.services.impl.DefaultUserService;

public class ServiceRegister {

  public ServiceRegister(RestService service, DefaultRepository repository) {
    service.addService(UserService.class.getName(), new DefaultUserService(service, repository));
    service.addService(RouteService.class.getName(), new DefaultRouteService(service, repository));
    service.addService(CoordinateService.class.getName(), new DefaultCoordinateService(service, repository));
    service.addService(HistoryService.class.getName(), new DefaultHistoryService(service, repository));
  }
}
