/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Property;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ioannis Psathas
 */
public class PropertyRepoImpl implements PropertyRepo {

    EntityManagerFactory emf;
    EntityManager entityManager;
    
    public PropertyRepoImpl() {
        this.emf = Persistence.createEntityManagerFactory("TechnikonPU");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void save(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(property);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(property);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }
    
    @Override
    public Property get(long id) {
        return entityManager.find(Property.class, id);
    }
    
    @Override
    public List<Property> getAll() {
        return entityManager.createQuery("SELECT p FROM Property p").getResultList();
    }
    
    @Override
    public Property getByVat(String vat) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.owner.vat = :vat",Property.class).setParameter("vat", vat).getSingleResult();
    }
}