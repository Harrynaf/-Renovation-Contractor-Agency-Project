/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import javax.persistence.EntityManager;

/**
 *
 * @author hnafp
 */
public abstract class ManageEntity {
    public static void save(EntityManager entityManager,Object o){
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
    }
    public static void remove(EntityManager entityManager,Object o){
        entityManager.getTransaction().begin();
        entityManager.remove(o);
        entityManager.getTransaction().commit();
    }
}