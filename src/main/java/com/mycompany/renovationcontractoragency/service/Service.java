package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.User;

import java.util.List;

public interface Service<T> {

    T create(T t);

    void delete(T t);

    T update(T t);

    List<T> getAll();
}
