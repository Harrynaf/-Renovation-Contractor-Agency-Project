/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

/**
 *
 * @author Ioannis Psathas
 */
public interface Repository<T> {
    //CRUD
//    void createOrUpdate(T t);
    void save(T t);
    T get(long id);
    void delete(T t);
    //List<T> getALL();
}