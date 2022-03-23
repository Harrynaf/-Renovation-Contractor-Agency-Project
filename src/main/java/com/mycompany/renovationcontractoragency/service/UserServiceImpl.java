package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.repository.UserRepo;
import javax.persistence.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo UserRepo) {
        this.userRepo = UserRepo;
    }

    @Override
    public User create(User user) throws EntityExistsException{
        if (!findByUsername(user)) {
            userRepo.save(user);
            return user;
        } else {
            logger.error("Something went wrong. EntityExistsException");
            throw new EntityExistsException();
        }
    }

    @Override
    public void delete(User user) throws EntityNotFoundException{
        if (get(user.getId())!=null) {
            userRepo.delete(user);
        } else {
            logger.error("Something went wrong. EntityNotFoundException");
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User update(User user) throws EntityNotFoundException{
        if (get(user.getId())!=null) {
            userRepo.save(user);
            return user;
        } else {
            logger.error("Something went wrong. EntityNotFoundException");
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
    public boolean findByUsername(User user) {
        return userRepo.findByUsername(user);
    }

    @Override
    public User getByVat(String vat) {
        return userRepo.getByVat(vat);
    }

    @Override
    public User getByEmail(String email) {
        return userRepo.getByEmail(email);
    }
}