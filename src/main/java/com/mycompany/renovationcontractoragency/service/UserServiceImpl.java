package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.User;
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
        return userRepo.checkExists(user);
    }

    @Override
    public User searchByVat(String vat) {
        return userRepo.searchByVat(vat);
    }

    @Override
    public User searchByEmail(String email) {
        return userRepo.searchByEmail(email);
    }
}
