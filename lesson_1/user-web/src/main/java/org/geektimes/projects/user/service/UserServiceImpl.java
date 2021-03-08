package org.geektimes.projects.user.service;

import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Validator;
import javax.xml.crypto.Data;

public class UserServiceImpl implements org.geektimes.projects.user.service.UserService {


    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Resource(name = "name/DbUserRepository")
    private DatabaseUserRepository repository;


    @Override
    public boolean register(User user) {
        return repository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
       return repository.getByNameAndPassword(name, password);
    }
}
