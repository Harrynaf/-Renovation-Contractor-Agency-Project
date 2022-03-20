package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.repository.Repository;
import com.mycompany.renovationcontractoragency.repository.UserRepoImpl;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class UserServiceImpl implements UserService {

    private EntityManager entityManager;
    private Repository userRepo;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.userRepo = new UserRepoImpl(entityManager);
    }

    @Override
    public User create(User user) {
        if (checkExists(user) == false) {
            userRepo.save(user);
            return user;
        } else {
            throw new EntityExistsException();
        }

    }

    @Override
    public void delete(User user) {
        if (checkExists(user) == true) {
            userRepo.delete(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User update(User user) {
        if (checkExists(user) == true) {
            userRepo.save(user);
            return user;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public User get(long id) {
        return (User) userRepo.get(id);
    }

    @Override
    public boolean checkExists(User user) {

        List<User> resultList = entityManager.createQuery("SELECT s FROM User s WHERE s.username = :username", User.class).setParameter("username", user.getUsername()).getResultList();
        if (!resultList.isEmpty())
            return true;
        else return false;

    }

    @Override
    public User searchByVat(String vat) {
        User result;
        return result = entityManager.createQuery("SELECT s FROM User s WHERE s.vat = :vat", User.class).setParameter("vat", vat).getSingleResult();
    }

    @Override
    public User searchByEmail(String email) {
        User result;
        return result = entityManager.createQuery("SELECT s FROM User s WHERE s.email = :email", User.class).setParameter("email", email).getSingleResult();
    }
}

