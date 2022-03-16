package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.entity.Property;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RenovationPU");
        EntityManager entityManager = emf.createEntityManager();
        //entityManager.find(Department.class, 1L);

        User owner1 = new Owner("","","","","","","","");
        //Property property1 = new Property("ds","","","","");
        Property property1 = new Property();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner1);
            entityManager.persist(property1);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception){
            entityManager.getTransaction().rollback();
        }

    }
}
