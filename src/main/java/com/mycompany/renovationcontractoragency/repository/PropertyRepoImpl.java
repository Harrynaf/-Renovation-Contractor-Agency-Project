/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Property;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Ioannis Psathas
 */
public class PropertyRepoImpl implements PropertyRepo {
    private final EntityManager entityManager;

    public PropertyRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Property get(long id) {
        return entityManager.find(Property.class, id);
    }

    @Override
    public List<Property> getByVat(String vat) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.owner.vat = :vat", Property.class).setParameter("vat", vat).getResultList();
    }

    @Override
    public Property getByECode(Property property) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.eCode = :ecode", Property.class).setParameter("ecode", property.geteCode()).getSingleResult();
    }

    @Override
    public void save(Property property) {
        ManageEntity.save(entityManager, property);
    }

    @Override
    public void delete(Property property) {
        ManageEntity.remove(entityManager, property);
    }

    @Override
    public List<Property> getAll() {
        return entityManager.createQuery("SELECT p FROM Property p", Property.class).getResultList();
    }
}
