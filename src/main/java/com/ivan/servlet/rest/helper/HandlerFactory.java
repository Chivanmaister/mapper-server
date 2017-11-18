package com.ivan.servlet.rest.helper;

import com.ivan.servlet.exceptions.InvalidMethodException;
import com.ivan.servlet.exceptions.InvalidServiceExcepton;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.rest.handlers.Handler;
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
        pathToMethod.putIfAbsent("/user/get", new ClassMethodCreator(com.ivan.servlet.rest.handlers.user.GetHandler.class, GET));
        pathToMethod.putIfAbsent("/user/add", new ClassMethodCreator(com.ivan.servlet.rest.handlers.user.AddHandler.class, POST));
        pathToMethod.putIfAbsent("/route/add", new ClassMethodCreator(com.ivan.servlet.rest.handlers.route.AddHandler.class, POST));
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
