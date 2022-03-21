package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.User;

public interface UserService extends Service<User> {

    User getByVat(String s);

    User getByEmail(String s);

    boolean findByUsername(User user);
}
