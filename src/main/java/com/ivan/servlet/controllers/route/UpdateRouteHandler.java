package com.ivan.servlet.controllers.route;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

@RestController
public class UpdateRouteHandler {

  private final Service service;

  public UpdateRouteHandler(Service service) {
    this.service = service;
  }

  @PostMapping("/route/update")
  public String updateRouteHandler(@RequestParam("id") Integer routeId, @RequestParam("name") String routeName) throws ServiceException {
    StringWriter response = new StringWriter();
    service.getService(RouteService.class).validateRouteExists(routeId);
    service.getService(RouteService.class).updateRouteName(routeId, routeName);
    return response.toString();
  }
}
