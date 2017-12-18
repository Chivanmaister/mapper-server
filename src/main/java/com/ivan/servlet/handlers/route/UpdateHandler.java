package com.ivan.servlet.handlers.route;


import com.ivan.servlet.exceptions.InvalidNameException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateHandler implements Handler {

    private RouteService routeService;

    public UpdateHandler(RestService service) throws ServiceException {
        routeService = service.getService(RouteService.class);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Integer id = getId(request);
        String name = getName(request);
        routeService.updateRouteName(id, name);
    }

    private Integer getId(HttpServletRequest request) throws ServiceException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        routeService.validateRouteExists(id);
        return id;
    }

    private String getName(HttpServletRequest request) throws InvalidNameException {
        String name = request.getParameter("name");
        if (name == null) {
            throw new InvalidNameException("Route name is null");
        }
        return name;
    }
}
