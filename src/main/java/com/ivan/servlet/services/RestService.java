package com.ivan.servlet.services;

import com.ivan.servlet.exceptions.InvalidServiceExcepton;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.Repository;
import com.ivan.servlet.services.impl.DefaultRouteService;
import com.ivan.servlet.services.impl.DefaultUserService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.HashMap;
import java.util.Map;

public class RestService {

    private Map<Class, Object> serviceImpl = new HashMap<>();

    public RestService() {
        serviceImpl.put(UserService.class, new DefaultUserService(this, new Repository()));
        serviceImpl.put(RouteService.class, new DefaultRouteService(this, new Repository() ));
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) throws ServiceException {
        if (!serviceImpl.containsKey(serviceClass)) {
            throw new InvalidServiceExcepton("Service not found for class = " + serviceClass.toString());
        }
        return (T) serviceImpl.get(serviceClass);
    }
}
