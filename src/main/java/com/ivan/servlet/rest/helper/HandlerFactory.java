package com.ivan.servlet.rest.helper;

import com.ivan.servlet.exceptions.InvalidMethodException;
import com.ivan.servlet.exceptions.InvalidServiceExcepton;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.services.RestService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HandlerFactory {
    private static final ConcurrentMap<String, ClassMethodCreator> pathToMethod = new ConcurrentHashMap<>();
    private static final String GET = "GET";
    private static final String POST = "POST";

    public HandlerFactory() {
        pathToMethod.put("/user/get", new ClassMethodCreator(com.ivan.servlet.handlers.user.GetHandler.class, GET));
        pathToMethod.put("/user/add", new ClassMethodCreator(com.ivan.servlet.handlers.user.AddHandler.class, POST));
        pathToMethod.put("/route/add", new ClassMethodCreator(com.ivan.servlet.handlers.route.AddHandler.class, POST));
        pathToMethod.put("/coordinate/add", new ClassMethodCreator(com.ivan.servlet.handlers.coordinate.AddHandler.class, POST));
    }

    @SuppressWarnings("unchecked")
    public Object getDispatcher(String namespace, String method) throws ServiceException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassMethodCreator creator = pathToMethod.get(namespace);
        if (creator == null) {
            throw new InvalidServiceExcepton("");
        }
        if (!method.equalsIgnoreCase(creator.getMethod())) {
            throw new InvalidMethodException();
        }
        Class foundClass = creator.getHandlerClass();
        Constructor constructor = foundClass.getConstructor(RestService.class);
        RestService restService = new RestService();
        Handler object = (Handler) constructor.newInstance(restService);

        return object;
    }
}
