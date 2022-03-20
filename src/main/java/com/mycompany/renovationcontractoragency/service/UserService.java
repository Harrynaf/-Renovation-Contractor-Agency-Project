package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.User;

public interface UserService extends Service<User> {
    User searchByVat(String s);

    User searchByEmail(String s);
}
