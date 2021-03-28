package org.geektimes.projects.user.service;

import org.apache.commons.collections.CollectionUtils;
import org.geektimes.injection.context.ClassicComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.sql.LocalTransactional;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

public class UserServiceImpl implements org.geektimes.projects.user.service.UserService {

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Resource(name = "bean/DatabaseUserRepository")
    private DatabaseUserRepository repository;

    public UserServiceImpl() {
        ClassicComponentContext instance = ClassicComponentContext.getInstance();
        this.entityManager = instance.getComponent("bean/EntityManager");
        this.validator = instance.getComponent("bean/Validator");
        this.repository = instance.getComponent("bean/DatabaseUserRepository");
    }

    @Override
    @LocalTransactional
    public boolean register(User user) {
        try {
            Set<ConstraintViolation<User>> validate = validator.validate(user, Default.class);
            if (CollectionUtils.isEmpty(validate)) entityManager.persist(user);

        } catch (IllegalArgumentException | TransactionRequiredException | EntityExistsException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
