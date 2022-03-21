package com.mycompany.renovationcontractoragency.main;

import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.Repair;
import com.mycompany.renovationcontractoragency.entity.User;
import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.enums.PropertyType;
import com.mycompany.renovationcontractoragency.enums.RepairStatus;
import com.mycompany.renovationcontractoragency.enums.RepairType;
import com.mycompany.renovationcontractoragency.repository.*;
import com.mycompany.renovationcontractoragency.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("--------------------USER--------------------");
        UserRepo userRepo = new UserRepoImpl(entityManager);
        UserService userService = new UserServiceImpl(userRepo);

        User owner1 = new Owner("123456789", "John", "Psathas", "Athens", "6991234567", "john@mail.com", "john", "11111");
        User owner2 = new Owner("123412789", "Harry", "Naf", "Athens", "699123423423", "harry@mail.com", "harry", "11111");
        User owner3 = new Owner("123457459", "Aggelos", "Koutsou", "Athens", "6935523423", "aggelos@mail.com", "aggelos", "11111");

        try {
            userService.create(owner1);
            userService.create(owner2);
            userService.create(owner3);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------PROPERTY--------------------");
        PropertyRepo propertyRepo = new PropertyRepoImpl(entityManager);
        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        System.out.println("---Test Create property---");
        Property property1 = new Property("E9_1", "Athens", LocalDate.of(2021, 1, 1), PropertyType.APARTMENT_BUILDING, (Owner) owner1);
        Property property2 = new Property("E9_2", "Athens", LocalDate.of(2021, 1, 1), PropertyType.MAISONETTE, (Owner) owner3);
        Property property3 = new Property("E9_3", "Athens", LocalDate.of(2021, 1, 1), PropertyType.DETACHED_HOUSE, (Owner) owner1);

        try {
            propertyService.create(property1);
            propertyService.create(property2);
            propertyService.create(property3);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------REPAIR--------------------");
        RepairRepo repairRepo = new RepairRepoImpl(entityManager);
        RepairService repairService = new RepairServiceImpl(repairRepo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("---Test Create repair---");
        Repair repair1 = new Repair(property1.getOwner(), property1, LocalDateTime.parse("2022-02-01 15:30", formatter), "repairDescription1", RepairType.PAINTING, RepairStatus.IN_PROGRESS, new BigDecimal("200.0"), "workToDoDescription1");
        Repair repair2 = new Repair(property2.getOwner(), property2, LocalDateTime.parse("2022-02-15 22:30", formatter), "repairDescription2", RepairType.ELECTRICAL_WORK, RepairStatus.PENDING, new BigDecimal("100.0"), "workToDoDescription2");

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
            System.out.println(repairService.getRepairByOwnerAndProperty(owner3.getId(), property2.getId()));
            System.out.println(repairService.getRepairByOwnerAndProperty(owner3.getId(), property1.getId())); // Expecting an empty list
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---Test Update repair---");
        try {
            repair1.setCost(new BigDecimal("150.0"));
            repairService.update(repair1);

            repair2.setDescription("dummy description");
            repairService.update(repair2);
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
