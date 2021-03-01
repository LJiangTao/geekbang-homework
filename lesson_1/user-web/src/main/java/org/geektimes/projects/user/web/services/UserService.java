package org.geektimes.projects.user.web.services;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.xml.crypto.Data;

public class UserService implements org.geektimes.projects.user.service.UserService {

    private static final DatabaseUserRepository repository;

    static {
        repository = new DatabaseUserRepository(DBConnectionManager.getInstance());
    }

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
        return null;
    }
}
