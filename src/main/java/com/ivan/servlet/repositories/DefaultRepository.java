package com.ivan.servlet.repositories;

import com.ivan.servlet.exceptions.InvalidClassException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.impl.BaseCoordinateDao;
import com.ivan.servlet.repositories.impl.BaseRouteDao;
import com.ivan.servlet.repositories.impl.BaseUserDao;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DefaultRepository implements Repository {
  private Map<Class, Object> repositories = new HashMap<>();
//  private DataSource dataSource;

  public DefaultRepository(DataSource dataSource) {
    repositories.put(UserDao.class, new BaseUserDao(dataSource));
    repositories.put(RouteDao.class, new BaseRouteDao(dataSource));
    repositories.put(CoordinateDao.class, new BaseCoordinateDao(dataSource));
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T getRepository(Class<T> repositoryClass) throws ServiceException {
    if (!repositories.containsKey(repositoryClass)) {
      throw new InvalidClassException("Error getting repository");
    }
    return (T) repositories.get(repositoryClass);
  }
}
