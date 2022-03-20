package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.User;
import java.util.List;
import javax.persistence.EntityManager;

public class UserRepoImpl implements UserRepo {

    private final EntityManager entityManager;

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
    public void delete(User owner) {
        entityManager.getTransaction().begin();
        entityManager.remove(owner);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    public User get(long id) {
        return entityManager.find(User.class, id);
    }
    
    @Override
    public boolean checkExists(User user) {
        List<User> resultList = entityManager.createQuery("SELECT s FROM User s WHERE s.username = :username", User.class).setParameter("username", user.getUsername()).getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public User searchByVat(String vat) {
        return entityManager.createQuery("SELECT s FROM User s WHERE s.vat = :vat", User.class).setParameter("vat", vat).getSingleResult();
    }

    @Override
    public User searchByEmail(String email) {
        return entityManager.createQuery("SELECT s FROM User s WHERE s.email = :email", User.class).setParameter("email", email).getSingleResult();
    }
}
