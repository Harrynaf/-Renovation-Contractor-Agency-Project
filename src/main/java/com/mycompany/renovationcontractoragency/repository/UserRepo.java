package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.User;

public interface UserRepo extends Repository<User> {
    User getByVat(String s);
    User getByEmail(String s);
    boolean findByUsername(User user);
}