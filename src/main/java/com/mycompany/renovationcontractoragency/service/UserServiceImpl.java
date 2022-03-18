package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.repository.Repository;
import com.mycompany.renovationcontractoragency.repository.UserRepoImpl;
import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class UserServiceImpl implements UserService {

    private EntityManager entityManager;
    private Repository userRepo;
    Session session;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.userRepo = new UserRepoImpl(entityManager);
        session = entityManager.unwrap(Session.class);
    }

    @Override
    public User create(User user) {
        if (session.get(User.class, user.getId()) != null) {
            userRepo.save(user);
            return user;
        } else {
            throw new EntityExistsException();
        }

    }

    @Override
    public void delete(User user) {
        if (session.get(User.class, user.getId()) == null) {
            userRepo.delete(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User update(User user) {
        if (session.get(User.class, user.getId()) != null) {
            userRepo.save(user);
            return user;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<User> getAll() {
        return session.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    boolean checkExists(User user) {
        Criteria criteria = session.createCriteria(User.class);
        User user1 = (User) criteria.add(Restrictions.eq("yourField", user.getUsername())).uniqueResult();
        if (user1 != null) {
            return true;
        } else {
            return false;
        }
    }
}
