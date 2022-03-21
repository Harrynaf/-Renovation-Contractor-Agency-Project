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
import java.util.List;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("--------------------USER--------------------");

        User owner1 = new Owner("123456789", "John", "Psathas", "Athens", "6991234567", "john@mail.com", "John", "11111");
        User owner2 = new Owner("123412789", "harry", "Naf", "Athens", "699123423423", "harry@mail.com", "harry", "11111");

        UserRepo userRepo = new UserRepoImpl(entityManager);
        UserService userService = new UserServiceImpl(userRepo);


        logger.info("This is a sample log!");
        logger.info("This is a sample log input date {}",LocalDate.now());



        try {
            userService.create(owner1);
            userService.create(owner2);
            logger.info("All good with creating users");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }
        try {
            System.out.println(userService.getByEmail("harry@mail.com"));
            System.out.println(userService.getByVat("123456789"));
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }
        owner2.setUsername("adsdasdasdasdas");
        try {
            userService.update(owner2);
            userService.update(owner1);
        } catch (EntityNotFoundException e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }
        userService.getAll();
        try {
            userService.delete(owner1);
            logger.info("All good with deleting users");
        } catch (EntityNotFoundException e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }
        
        System.out.println("--------------------PROPERTY--------------------");
        PropertyRepo propertyRepo = new PropertyRepoImpl(entityManager);
        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        System.out.println("---Test Create property---");
        Property property1 = new Property("E9_1", "Athens", LocalDate.of(2021, 1, 1), PropertyType.APARTMENT_BUILDING, (Owner) userRepo.get(2L));
        Property property2 = new Property("E9_2", "Athens", LocalDate.of(2021, 1, 1), PropertyType.MAISONETTE, (Owner) userRepo.get(2L));
        Property property3 = new Property("E9_3", "Athens", LocalDate.of(2021, 1, 1), PropertyType.DETACHED_HOUSE, (Owner) userRepo.get(2L));
        try {
            propertyService.create(property1);
            propertyService.create(property2);
            propertyService.create(property3);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }

        System.out.println("---Test GetAll property---");
        try {
            System.out.println(propertyService.getAll());
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }

        System.out.println("---Test Update and Get property---");
        try {
            Property property4 = propertyService.get(4L);
            property4.setAddress("Thessaloniki");
            propertyService.update(property4);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }

        System.out.println("---Test Delete and Get property---");
        try {
            Property property5 = propertyService.get(5L);
            propertyService.delete(property5);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }
        
        System.out.println("---Test GetByVat property---");
        try {
            List<Property> properties = propertyService.getByVat("123412789");
            for(Property property : properties){
                System.out.println(property);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}",e.getMessage());
        }
        
        //TODO Need more testing
     
        System.out.println("--------------------REPAIR--------------------");
    }
}
