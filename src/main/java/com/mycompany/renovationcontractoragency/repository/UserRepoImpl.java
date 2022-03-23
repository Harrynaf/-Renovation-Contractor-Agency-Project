package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.User;

import javax.persistence.EntityManager;
import java.util.List;


public class UserRepoImpl extends ManageEntity implements UserRepo {
    private final EntityManager entityManager;

    public UserRepoImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        super.saveEntity(user);
    }

    @Override
    public void delete(User user) {
        super.deleteEntity(user);
    }
    
    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    public User get(long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Finds user by username and returns boolean
     *
     * @param user
     * @return
     */
    @Override
    public boolean findByUsername(User user) {
        List<User> resultList = entityManager.createQuery("SELECT s FROM User s WHERE s.username = :username", User.class).setParameter("username", user.getUsername()).getResultList();
        return !resultList.isEmpty();
    }

    /**
     * Finds and returns a user by vat
     *
     * @param vat
     * @return
     */
    @Override
    public User getByVat(String vat) {
        return entityManager.createQuery("SELECT s FROM User s WHERE s.vat = :vat", User.class).setParameter("vat", vat).getSingleResult();
    }

    /**
     * Finds and returns a user by email
     *
     * @param email
     * @return
     */
    @Override
    public User getByEmail(String email) {
        return entityManager.createQuery("SELECT s FROM User s WHERE s.email = :email", User.class).setParameter("email", email).getSingleResult();
    }
}