package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.enums.PropertyType;
import com.mycompany.renovationcontractoragency.repository.PropertyRepo;
import com.mycompany.renovationcontractoragency.repository.PropertyRepoImpl;
import com.mycompany.renovationcontractoragency.repository.UserRepo;
import com.mycompany.renovationcontractoragency.repository.UserRepoImpl;
import com.mycompany.renovationcontractoragency.service.PropertyService;
import com.mycompany.renovationcontractoragency.service.PropertyServiceImpl;
import com.mycompany.renovationcontractoragency.service.UserService;
import com.mycompany.renovationcontractoragency.service.UserServiceImpl;
import java.time.LocalDate;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        PropertyRepo propertyRepo = new PropertyRepoImpl();

        UserRepo userRepo = new UserRepoImpl(entityManager);

        System.out.println("--------------------USER--------------------");

        User user1 = new Owner("123456789", "John", "Psathas", "Athens", "6991234567", "john@mail.com", "John", "11111");
        User user2 = new Owner("123412789", "harry", "Naf", "Athens", "699123423423", "harry@mail.com", "harry", "11111");

        UserService userService = new UserServiceImpl(entityManager);

        try {
            System.out.println("Create user");
            userService.create(user1);
            userService.create(user2);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
        }

        User user3 = new Owner("123412789", "harry", "Naf", "Athens", "699123423423", "harry@mail.com", "harry", "22222");

        try {
            System.out.println("Update user");
            userService.update(user3);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }

        userService.getAll();

        try {
            System.out.println("Delete user");
            userService.delete(user2);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------PROPERTY--------------------");

        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        System.out.println("---Test Create property---");
        Property property1 = new Property("E9_1", "Athens", LocalDate.of(2021, 1, 1), PropertyType.APARTMENT_BUILDING, (Owner) userRepo.get(1L));
        Property property2 = new Property("E9_2", "Athens", LocalDate.of(2021, 1, 1), PropertyType.MAISONETTE, (Owner) userRepo.get(2L));
        Property property3 = new Property("E9_3", "Athens", LocalDate.of(2021, 1, 1), PropertyType.DETACHED_HOUSE, (Owner) userRepo.get(2L));
        propertyService.create(property1);
        propertyService.create(property2);
        propertyService.create(property3);

        System.out.println("---Test GetAll property---");
        System.out.println(propertyService.getAll());

        System.out.println("---Test Update and Get property---");
        Property property4 = propertyService.get(3L);
        property4.setAddress("Thessaloniki");
        propertyService.update(property4);

        System.out.println("---Test Delete and GetByVat property---");
        Property property5 = propertyService.get(4L);
        propertyService.delete(property5);

        System.out.println("---2nd Test Delete property---");
        Property property6 = new Property();
        property6.setId(11L);
        propertyService.delete(property6);

        System.out.println("---2nd Update property---");
        Property property7 = new Property();
        property7.setECode("E9_1");
        propertyService.update(property7);

        System.out.println("--------------------REPAIR--------------------");

    }
}