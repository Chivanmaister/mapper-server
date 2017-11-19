package com.ivan.servlet.handlers.coordinate;

import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.services.CoordinateService;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddHandler implements Handler {

    private CoordinateService coordinateService;
    private RouteService routeService;

    public AddHandler(RestService restService) throws ServiceException {
        coordinateService = restService.getService(CoordinateService.class);
        routeService = restService.getService(RouteService.class);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        Double latitude = getLatitude(request);
        Double longitude = getLongitude(request);
        Integer routeId = getRouteId(request);

        coordinateService.addCoordinate(latitude, longitude, routeId);
    }

    private Double getLatitude(HttpServletRequest request) throws ServiceException {
        Double latitude = Double.valueOf(request.getParameter("latitude"));
        coordinateService.validateLatitude(latitude);
        return latitude;
    }

    private Double getLongitude(HttpServletRequest request) throws ServiceException {
        Double longitude = Double.valueOf(request.getParameter("longitude"));
        coordinateService.validateLatitude(longitude);
        return longitude;
    }

    private Integer getRouteId(HttpServletRequest request) throws ServiceException {
        Integer routeId = Integer.valueOf(request.getParameter("routeId"));
        routeService.validateRouteExists(routeId);
        return routeId;
    }
}
