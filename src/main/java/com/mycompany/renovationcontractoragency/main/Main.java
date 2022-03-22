package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.*;
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
import java.util.List;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        //  EntityManager entityManager = emf.createEntityManager();
        System.out.println("--------------------USER--------------------");

        User owner1 = new User("John", "Psathas", "123456789", "Athens", "6991234567", "john@mail.com", "John", "ddsds", User_Type.OWNER);
        User owner2 = new User("123412789", "harry", "Naf", "Athens", "699123423423", "harry@mail.com", "harry", "1111sdd1", User_Type.OWNER);
        User owner3 = new User("123457459", "Aggelos", "Koutsou", "Athens", "6935523423", "aggelos@mail.com", "aggelos", "11sd111", User_Type.OWNER);
        User admin = new User("412412412", "Admin", "admin", "Athens", "6935523423", "aggelos@mail.com", "xcxc", "xccxss", User_Type.ADMIN);

        UserRepo userRepo = new UserRepoImpl();
        UserService userService = new UserServiceImpl(userRepo);


        logger.info("This is a sample log!");
        logger.info("This is a sample log input date {}", LocalDate.now());


        try {
            userService.create(admin);
            userService.create(owner1);
            userService.create(owner2);
            userService.create(owner3);

            logger.info("All good with creating users");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        try {
            System.out.println(userService.getByEmail("harry@mail.com"));
            System.out.println(userService.getByVat("123456789"));
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
        owner2.setUsername("adsdasdasdasdas");
        try {
            userService.update(owner2);
            userService.update(owner1);
        } catch (EntityNotFoundException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
        userService.getAll();
        try {
            userService.delete(owner2);
            logger.info("All good with deleting users");
        } catch (EntityNotFoundException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        System.out.println("--------------------PROPERTY--------------------");
        PropertyRepo propertyRepo = new PropertyRepoImpl();
        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        System.out.println("---Test Create property---");
        Property property1 = new Property("E9_1", "Athens", LocalDate.of(2021, 1, 1), PropertyType.APARTMENT_BUILDING, userRepo.get(1L));
        Property property2 = new Property("E9_2", "Athens", LocalDate.of(2021, 1, 1), PropertyType.MAISONETTE, userRepo.get(2L));
        Property property3 = new Property("E9_3", "Athens", LocalDate.of(2021, 1, 1), PropertyType.DETACHED_HOUSE, userRepo.get(2L));
        try {
            propertyService.create(property1);
            propertyService.create(property2);
            propertyService.create(property3);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        System.out.println("---Test GetAll property---");
        try {
            System.out.println(propertyService.getAll());
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        System.out.println("---Test Update and Get property---");
        try {
            Property property4 = propertyService.get(4L);
            property4.setAddress("Thessaloniki");
            propertyService.update(property4);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        System.out.println("---Test Delete and Get property---");
        try {
            Property property5 = propertyService.get(5L);
            propertyService.delete(property5);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        System.out.println("---Test GetByVat property---");
        try {
            List<Property> properties = propertyService.getByVat("123412789");
            for (Property property : properties) {
                System.out.println(property);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }


        System.out.println("--------------------REPAIR--------------------");
        RepairRepo repairRepo = new RepairRepoImpl();
        RepairService repairService = new RepairServiceImpl(repairRepo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("---Test Create repair---");
        Repair repair1 = new Repair(propertyService.get(6), LocalDateTime.parse("2022-02-01 15:30", formatter), "repairDescription1", RepairType.PAINTING, RepairStatus.IN_PROGRESS, new BigDecimal("200.0"), "workToDoDescription1");
        Repair repair2 = new Repair(propertyService.get(7), LocalDateTime.parse("2022-02-15 22:30", formatter), "repairDescription2", RepairType.ELECTRICAL_WORK, RepairStatus.PENDING, new BigDecimal("100.0"), "workToDoDescription2");

        try {
            repairService.create(repair1);
            repairService.create(repair2);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test GetAll repairs---");
        try {
            System.out.println(repairService.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test Get repair---");
        try {
            System.out.println(repairService.get(repair1.getRepairId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test GetRepairByDate, GetRepairByDateRange, GetRepairByOwnerId---");
        try {
            System.out.println(repairService.getRepairByDate(LocalDateTime.parse("2022-02-15 22:30", formatter)));
            System.out.println(repairService.getRepairByDateRange(LocalDateTime.parse("2022-01-01 00:00", formatter), LocalDateTime.parse("2022-02-10 00:00", formatter)));
            System.out.println(repairService.getRepairByOwnerId(userService.getByEmail("aggelos@mail.com").getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test GetRepairByOwnerAndProperty---");
        try {
            System.out.println(repairService.getRepairByOwnerAndProperty(userService.get(3).getId(), propertyService.get(5).getId()));
            System.out.println(repairService.getRepairByOwnerAndProperty(userService.get(3).getId(), propertyService.get(4).getId())); // Expecting an empty list
            System.out.println(repairService.getRepairByOwnerAndProperty(propertyService.get(5).getOwner().getId(), propertyService.get(5).getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test Update repair---");
        try {
            repair1.setCost(new BigDecimal("150.0"));
            repairService.update(repairService.get(8));

             repair2.setDescription("dummy description");
              repairService.update(repairService.get(9));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test Delete repair---");
        try {
            repairService.delete(repair1);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
