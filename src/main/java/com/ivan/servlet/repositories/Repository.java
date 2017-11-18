package com.ivan.servlet.repositories;

import com.ivan.servlet.exceptions.InvalidClassException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.impl.BaseRouteDao;
import com.ivan.servlet.repositories.impl.BaseUserDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private Map<Class, Object> repositoryMap = new HashMap<>();
    private MysqlDataSource dataSource;

    public Repository() {
        setDataSource();
        repositoryMap.put(UserDao.class, new BaseUserDao(dataSource));
        repositoryMap.put(RouteDao.class, new BaseRouteDao(dataSource));
    }

    private void setDataSource() {
        dataSource = new MysqlDataSource();
        ( dataSource).setUser("mapperdb");
        ((MysqlDataSource) dataSource).setPassword("Zarawa2410985");
        ((MysqlDataSource) dataSource).setDatabaseName("mapper");
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/mapper");
    }

    @SuppressWarnings("unchecked")
    public <T> T getRepository(Class<T> repositoryClass) throws ServiceException {
        if (!repositoryMap.containsKey(repositoryClass)) {
            throw new InvalidClassException("Unknown repository");
        }
        return (T) repositoryMap.get(repositoryClass);
    }
}
