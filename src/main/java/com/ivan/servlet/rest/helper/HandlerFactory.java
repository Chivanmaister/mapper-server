package com.ivan.servlet.rest.helper;

import com.ivan.servlet.exceptions.ErrorCodes;
import com.ivan.servlet.exceptions.InvalidMethodException;
import com.ivan.servlet.exceptions.InvalidServiceException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.handlers.Handler;
import com.ivan.servlet.services.RestService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {
  private static final Map<String, ClassMethodCreator> pathToMethod = new HashMap<>();
  private static final String GET = "GET";
  private static final String POST = "POST";

  public HandlerFactory() {
    pathToMethod.put("/ping", new ClassMethodCreator(com.ivan.servlet.handlers.PingHandler.class, GET));
    pathToMethod.put("/user/get", new ClassMethodCreator(com.ivan.servlet.handlers.user.GetHandler.class, GET));
    pathToMethod.put("/user/add", new ClassMethodCreator(com.ivan.servlet.handlers.user.AddHandler.class, POST));
    pathToMethod.put("/route/add", new ClassMethodCreator(com.ivan.servlet.handlers.route.AddHandler.class, POST));
    pathToMethod.put("/route/update", new ClassMethodCreator(com.ivan.servlet.handlers.route.UpdateHandler.class, POST));
    pathToMethod.put("/coordinate/add", new ClassMethodCreator(com.ivan.servlet.handlers.coordinate.AddHandler.class, POST));
    pathToMethod.put("/history/get", new ClassMethodCreator(com.ivan.servlet.handlers.history.GetHandler.class, GET));
  }

  @SuppressWarnings("unchecked")
  public Object getDispatcher(String namespace, String method) throws ServiceException {
    ClassMethodCreator creator = pathToMethod.get(namespace);
    if (creator == null) {
      throw new InvalidServiceException("Unknown service");
    }
    if (!method.equalsIgnoreCase(creator.getMethod())) {
      throw new InvalidMethodException("Unknown class to instantiate");
    }
    Class foundClass = creator.getHandlerClass();
    Constructor constructor;
    Handler handler;
    try {
      constructor = foundClass.getConstructor(RestService.class);
    } catch (NoSuchMethodException e) {
      throw new ServiceException(ErrorCodes.INTERNAL_ERROR, "Unknown method");
    }
    RestService restService = new RestService();
    try {
      handler = (Handler) constructor.newInstance(restService);
    } catch (IllegalAccessException e) {
      throw new ServiceException(ErrorCodes.INTERNAL_ERROR, "Illegal access");
    } catch (InstantiationException e) {
      throw new ServiceException(ErrorCodes.INTERNAL_ERROR, "Error instantiating");
    } catch (InvocationTargetException e) {
      throw new ServiceException(ErrorCodes.INTERNAL_ERROR, "Error in invocation target");
    } catch (Exception e) {
      throw new ServiceException(ErrorCodes.INTERNAL_ERROR, "Unexpected error");
    }
    return handler;
  }
}
