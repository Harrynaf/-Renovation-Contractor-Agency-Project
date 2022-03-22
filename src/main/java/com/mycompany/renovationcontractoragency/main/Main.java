package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.Repair;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.enums.PropertyType;
import com.mycompany.renovationcontractoragency.enums.RepairStatus;
import com.mycompany.renovationcontractoragency.enums.RepairType;
import com.mycompany.renovationcontractoragency.enums.User_Type;
import com.mycompany.renovationcontractoragency.repository.*;
import com.mycompany.renovationcontractoragency.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        createData();
       // updateUsers();
        //deleteUsers();

    }

    public static void createData() {
        UserRepo userRepo = new UserRepoImpl();
        UserService userService = new UserServiceImpl(userRepo);

        RepairRepo repairRepo = new RepairRepoImpl();
        RepairService repairService = new RepairServiceImpl(repairRepo);

        PropertyRepo propertyRepo = new PropertyRepoImpl();
        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        User owner1 = new User("123456789", "John", "Psathas", "Athens", "6991234567", "john@mail.com", "john", "11111", User_Type.OWNER);
        User owner2 = new User("123412789", "Harry", "Naf", "Athens", "6991234234", "harry@mail.com", "harry", "11111", User_Type.OWNER);
        User owner3 = new User("123457459", "Aggelos", "Koutsou", "Athens", "6935523423", "aggelos@mail.com", "aggelos", "11111", User_Type.OWNER);

        logger.info("This is a sample log!");
        logger.info("This is a sample log input date {}", LocalDate.now());

        try {
            userService.create(owner1);
            userService.create(owner2);
            userService.create(owner3);
            logger.info("All good with creating owner data");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }


        Property property1 = new Property("E9_1", "Athens", LocalDate.of(2021, 1, 1), PropertyType.APARTMENT_BUILDING, userService.get(1L));
        Property property2 = new Property("E9_2", "Athens", LocalDate.of(2021, 1, 1), PropertyType.MAISONETTE, userService.get(2L));
        Property property3 = new Property("E9_3", "Athens", LocalDate.of(2021, 1, 1), PropertyType.DETACHED_HOUSE, userService.get(3L));

        try {
            propertyService.create(property1);
            propertyService.create(property2);
            propertyService.create(property3);
            logger.info("All good with creating property data");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Repair repair1 = new Repair(propertyService.get(1L), LocalDateTime.parse("2022-02-01 15:30", formatter), "repairDescription1", RepairType.PAINTING, RepairStatus.IN_PROGRESS, new BigDecimal("200.0"), "workToDoDescription1");
        Repair repair2 = new Repair(propertyService.get(2L), LocalDateTime.parse("2022-02-15 10:30", formatter), "repairDescription2", RepairType.FRAMES, RepairStatus.COMPLETE, new BigDecimal("100.0"), "workToDoDescription2");
        Repair repair3 = new Repair(propertyService.get(3L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription3", RepairType.PLUMPING, RepairStatus.PENDING, new BigDecimal("300.0"), "workToDoDescription3");

        try {
            repairService.create(repair1);
            repairService.create(repair2);
            repairService.create(repair3);
            logger.info("All good with creating repair data");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
        System.out.println(propertyService.get(1L).getRepairs().toString());
    }

    public static void updateUsers() {
        UserRepo userRepo = new UserRepoImpl();
        UserService userService = new UserServiceImpl(userRepo);

        userService.get(1).setUsername("Changed Username");
        userService.update(userService.get(1));
        userService.get(2).setAddress("Changed Address");
        userService.update(userService.get(2));
        userService.get(2).setUser_Type(User_Type.ADMIN);
        userService.update(userService.get(2));

    }

    public static void deleteUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        UserRepo userRepo = new UserRepoImpl();
        UserService userService = new UserServiceImpl(userRepo);

        RepairRepo repairRepo = new RepairRepoImpl();
        RepairService repairService = new RepairServiceImpl(repairRepo);

        PropertyRepo propertyRepo = new PropertyRepoImpl();
        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        //propertyService.get(1L).setRepairs(entityManager.find(Repair.class, );

       // propertyService.delete(propertyService.get(1L));
        //userService.delete(userService.get(1L));
        //userService.delete(userService.get(2));
       // userService.delete(userService.get(3));

    }
}