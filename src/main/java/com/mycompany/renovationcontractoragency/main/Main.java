package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.enums.PropertyType;
import com.mycompany.renovationcontractoragency.service.UserService;
import com.mycompany.renovationcontractoragency.service.UserServiceImpl;

import java.time.LocalDate;
import javax.persistence.*;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        User owner1 = new Owner("123456789", "John", "Psathas", "Athens", "6991234567", "john@mail.com", "John", "11111");
        User owner2 = new Owner("123412789", "harry", "Naf", "Athens", "699123423423", "harry@mail.com", "harry", "11111");
        Property property1 = new Property("E9_1", "Athens", LocalDate.of(2021, 1, 1), PropertyType.APARTMENT_BUILDING, (Owner) owner1);
        UserService userService = new UserServiceImpl(entityManager);

        try {
            userService.create(owner1);
            userService.create(owner2);
        } catch (
                EntityExistsException e) {
            System.out.println(e.getMessage());
        }
        try {
            userService.update(owner1);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        userService.getAll();
        try {
            userService.delete(owner1);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}