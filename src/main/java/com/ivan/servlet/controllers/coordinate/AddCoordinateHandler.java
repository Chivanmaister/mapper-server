package com.ivan.servlet.controllers.coordinate;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.json.JsonUtils;
import com.ivan.servlet.services.CoordinateService;
import com.ivan.servlet.services.RouteService;
import com.ivan.servlet.services.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

@RestController
public class AddCoordinateHandler {

  private final Service service;

  public AddCoordinateHandler(Service service) {
    this.service = service;
  }

  @PostMapping("/coordinate/add")
  public String addRoute(@RequestParam("routeId") Integer routeId, @RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) throws ServiceException, IOException {
    StringWriter response = new StringWriter();
    service.getService(RouteService.class).validateRouteExists(routeId);
    Coordinate coordinate = service.getService(CoordinateService.class).addCoordinate(latitude, longitude, routeId);
    JsonUtils.createCoordinateResponse(response, coordinate);

    return response.toString();
  }
}
