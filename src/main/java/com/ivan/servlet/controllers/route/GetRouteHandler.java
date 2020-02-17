package com.ivan.servlet.controllers.route;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class GetRouteHandler {

  Service service;

  public GetRouteHandler(Service service) {
    this.service = service;
  }

  @GetMapping("/route/getById")
  public String getRouteById(@RequestParam("id") Integer routeId) throws ServiceException, IOException {
    StringWriter response = new StringWriter();
    service.getService(RouteService.class).validateRouteExists(routeId);
    Route route = service.getService(RouteService.class).getById(routeId);
    JsonUtils.createRouteResponse(response, route);

    return response.toString();
  }
}
