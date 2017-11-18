package com.ivan.servlet.handlers.route;


import com.ivan.servlet.entities.Route;
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
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, ServiceException {

    }
}
