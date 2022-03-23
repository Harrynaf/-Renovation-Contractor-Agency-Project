package com.mycompany.renovationcontractoragency.repository;

import javax.persistence.EntityManager;

public abstract class ManageEntity {

    public static void save(EntityManager entityManager, Object t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }
    public static void delete(EntityManager entityManager, Object t) {
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }
}