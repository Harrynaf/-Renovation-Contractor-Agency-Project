/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Property;

import javax.persistence.EntityManager;

/**
 * @author Ioannis Psathas
 */
public class PropertyRepoImpl implements PropertyRepo {

    private EntityManager entityManager;

    public PropertyRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Property property) {
        entityManager.getTransaction().begin();
        entityManager.persist(property);
        entityManager.getTransaction().commit();
    }

    @Override
    public Property get(long id) {
        return entityManager.find(Property.class, id);
    }

    @Override
    public void delete(Property property) {

        entityManager.getTransaction().begin();
        entityManager.remove(property);
        entityManager.getTransaction().commit();

    }
}