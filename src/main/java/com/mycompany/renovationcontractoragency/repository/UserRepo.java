package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.User;

public interface UserRepo extends Repository<User>{
    User searchByVat(String s);
    User searchByEmail(String s);
}
