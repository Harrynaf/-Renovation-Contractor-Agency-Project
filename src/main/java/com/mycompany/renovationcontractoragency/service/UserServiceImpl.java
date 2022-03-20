package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.repository.Repository;
import com.mycompany.renovationcontractoragency.repository.UserRepo;
import com.mycompany.renovationcontractoragency.repository.UserRepoImpl;

import javax.persistence.*;
import java.util.List;



public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final UserRepo userRepo;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.userRepo = new UserRepoImpl(entityManager);
    }

    @Override
    public User create(User user) {
        if (!checkExists(user)) {
            userRepo.save(user);
            return user;
        } else {
            throw new EntityExistsException();
        }

    }

    @Override
    public void delete(User user) {
        if (checkExists(user)) {
            userRepo.delete(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User update(User user) {
        if (checkExists(user)) {
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
        return userRepo.get(id);
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

