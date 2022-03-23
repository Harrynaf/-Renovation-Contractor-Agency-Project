package com.mycompany.renovationcontractoragency.repository;

import javax.persistence.EntityManager;


public abstract class ManageEntity {
    private final EntityManager entityManager;

    public ManageEntity(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public void saveEntity(Object t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }

    public void deleteEntity(Object t) {
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }
}