package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.enums.PropertyType;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        User owner1 = new Owner("123456789", "John","Psathas","Athens","6991234567","john@mail.com","John","11111");
        Property property1 = new Property("E9_1","Athens",LocalDate.of(2021,1,1),PropertyType.APARTMENT_BUILDING,(Owner)owner1);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner1);
            entityManager.persist(property1);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception){
            System.out.println("Exception catched." + exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }
}