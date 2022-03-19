package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.User;
import javax.persistence.EntityManager;

public class UserRepoImpl implements UserRepo {

    private EntityManager entityManager;

    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User owner) {
        entityManager.getTransaction().begin();
        entityManager.persist(owner);
        entityManager.getTransaction().commit();
    }

    @Override
    public User get(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(User owner) {
        entityManager.getTransaction().begin();
        entityManager.remove(owner);
        entityManager.getTransaction().commit();
    }
}