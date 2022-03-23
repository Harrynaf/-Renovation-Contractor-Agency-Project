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
import java.util.List;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TechnikonPU");
        EntityManager entityManager = emf.createEntityManager();

        UserRepo userRepo = new UserRepoImpl(entityManager);
        UserService userService = new UserServiceImpl(userRepo);

        PropertyRepo propertyRepo = new PropertyRepoImpl(entityManager);
        PropertyService propertyService = new PropertyServiceImpl(propertyRepo);

        RepairRepo repairRepo = new RepairRepoImpl(entityManager);
        RepairService repairService = new RepairServiceImpl(repairRepo);

//        //---DATA CREATION TEST---
        createData(userService, propertyService, repairService);

//        //---REPAIR TEST---
//        getAllRepair(repairService);
//        getRepairByDate(repairService);
//        getRepairByDateRange(repairService);
//        getRepairByOwnerId(repairService);
//        getRepairByPropertyId(repairService);
//        updateRepair(repairService);
//        deleteRepair(repairService);

//        //---PROPERTY TEST---
//        getAllProperty(propertyService);
//        updateProperty(propertyService);
//        getPropertyById(propertyService);
//        getPropertiesByVat(propertyService);
//        deleteProperty(propertyService);
//        getAllProperty(propertyService);
//        createPropertyWithExistingECode(userService, propertyService);

//        //---USER TEST---
//        updateUser(userService);
//        getUserbyVat(userService);
//        getUserbyEmail(userService);
//        deleteUser(userService);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void createData(UserService userService, PropertyService propertyService, RepairService repairService) {

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
        Property property2 = new Property("E9_2", "Athens", LocalDate.of(1987, 1, 1), PropertyType.MAISONETTE, userService.get(2L));
        Property property3 = new Property("E9_3", "Athens", LocalDate.of(2005, 1, 1), PropertyType.DETACHED_HOUSE, userService.get(2L));
        Property property4 = new Property("E9_4", "Athens", LocalDate.of(2001, 1, 1), PropertyType.APARTMENT_BUILDING, userService.get(3L));

        try {
            propertyService.create(property1);
            propertyService.create(property2);
            propertyService.create(property3);
            propertyService.create(property4);
            logger.info("All good with creating property data");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }

        Repair repair1 = new Repair(propertyService.get(1L), LocalDateTime.parse("2022-02-01 15:30", formatter), "repairDescription1", RepairType.PAINTING, RepairStatus.IN_PROGRESS, new BigDecimal("200.0"), "workToDoDescription1");
        Repair repair2 = new Repair(propertyService.get(2L), LocalDateTime.parse("2022-02-15 10:30", formatter), "repairDescription2", RepairType.FRAMES, RepairStatus.COMPLETE, new BigDecimal("100.0"), "workToDoDescription2");
        Repair repair3 = new Repair(propertyService.get(3L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription3", RepairType.INSULATION, RepairStatus.PENDING, new BigDecimal("300.0"), "workToDoDescription3");
        Repair repair4 = new Repair(propertyService.get(4L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription4", RepairType.PLUMPING, RepairStatus.PENDING, new BigDecimal("350.0"), "workToDoDescription4");
        Repair repair5 = new Repair(propertyService.get(1L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription5", RepairType.PAINTING, RepairStatus.PENDING, new BigDecimal("450.0"), "workToDoDescription5");
        Repair repair6 = new Repair(propertyService.get(2L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription6", RepairType.ELECTRICAL_WORK, RepairStatus.PENDING, new BigDecimal("365.0"), "workToDoDescription6");
        Repair repair7 = new Repair(propertyService.get(3L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription7", RepairType.PLUMPING, RepairStatus.PENDING, new BigDecimal("700.0"), "workToDoDescription7");
        Repair repair8 = new Repair(propertyService.get(4L), LocalDateTime.parse("2022-03-20 10:30", formatter), "repairDescription8", RepairType.PLUMPING, RepairStatus.PENDING, new BigDecimal("130.0"), "workToDoDescription8");

        try {
            repairService.create(repair1);
            repairService.create(repair2);
            repairService.create(repair3);
            repairService.create(repair4);
            repairService.create(repair5);
            repairService.create(repair6);
            repairService.create(repair7);
            repairService.create(repair8);
            logger.info("All good with creating repair data");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void updateUser(UserService userService) {
        try {
            User user = userService.get(2L);
            user.setUsername("changed username");
            userService.update(user);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void deleteUser(UserService userService) {
        try {
            User user = userService.get(1L);
            userService.delete(user);
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void getUserbyVat(UserService userService) {
        try {
            System.out.println(userService.getByVat("123457459"));
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void getUserbyEmail(UserService userService) {
        try {
            System.out.println(userService.getByEmail("harry@mail.com"));
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
/////////////////////////////////////////////////////////////////////////////////////
    public static void getAllProperty(PropertyService propertyService) {
        try {
            List<Property> properties = propertyService.getAll();
            for (Property property : properties) {
                System.out.println(property);
            }
            logger.info("All good with getting all property data");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
    
    public static void updateProperty(PropertyService propertyService) {
        try {
            Property property = propertyService.get(2L);
            property.setAddress("Thessaloniki");
            propertyService.update(property);
            logger.info("All good with updating property data");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
    
    public static void getPropertyById(PropertyService propertyService) {
        try {
            Property property = propertyService.get(2L);
            System.out.println(property);
            logger.info("All good with getting property data by ID");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
    
    public static void getPropertiesByVat(PropertyService propertyService) {
        try {
            List<Property> propertiesByVat = propertyService.getByVat("123412789");
            for (Property property : propertiesByVat) {
                System.out.println(property);
            }
            logger.info("All good with getting property data by vat");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
    
    public static void deleteProperty(PropertyService propertyService) {
        try {
            Property property = propertyService.get(2L);
            propertyService.delete(property);
            logger.info("All good with deleting property data");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
    
    public static void foundByEcode(PropertyService propertyService) {
        try {
            Property property = propertyService.get(2L);
            boolean foundProperty = propertyService.foundByECode(property);
            System.out.println(foundProperty);
            logger.info("All good checking property by ecode");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
    
    public static void createPropertyWithExistingECode(UserService userService, PropertyService propertyService) {

        Property property1 = new Property("E9_4", "Athens", LocalDate.of(2001, 1, 1), PropertyType.APARTMENT_BUILDING, userService.get(3L));

        try {
            propertyService.create(property1);
            logger.info("All good with creating property data");
        } catch (EntityExistsException e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void getAllRepair(RepairService repairService) {
        try {
            List<Repair> repairs = repairService.getAll();
            for (Repair repair : repairs) {
                System.out.println(repair);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void getRepairByDate(RepairService repairService) {
        try {
            List<Repair> repairs = repairService.getRepairByDate(LocalDateTime.parse("2022-02-15 10:30", formatter));
            for (Repair repair : repairs) {
                System.out.println(repair);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void getRepairByDateRange(RepairService repairService) {
        try {
            List<Repair> repairs = repairService.getRepairByDateRange(LocalDateTime.parse("2020-03-20 10:30", formatter), LocalDateTime.parse("2023-03-01 00:00", formatter));
            for (Repair repair : repairs) {
                System.out.println(repair);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void getRepairByOwnerId(RepairService repairService) {
        try {
            List<Repair> repairs = repairService.getRepairByOwnerId(repairService.get(3L).getOwner().getId());
            for (Repair repair : repairs) {
                System.out.println(repair);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void getRepairByPropertyId(RepairService repairService) {
        try {
            List<Repair> repairs = repairService.getRepairByPropertyId(repairService.get(3L).getProperty().getId());
            for (Repair repair : repairs) {
                System.out.println(repair);
            }
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void updateRepair(RepairService repairService) {
        try {
            Repair repair2 = repairService.get(3L);
            repair2.setStatus(RepairStatus.STANDBY_MODE);
            repairService.update(repair2);
            logger.info("All good with updating repair data");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }

    public static void deleteRepair(RepairService repairService) {
        try {
            Repair repair1 = repairService.get(4L);
            repairService.delete(repair1);
            logger.info("All good with deleting repair data");
        } catch (Exception e) {
            logger.error("Something went wrong. Details: {}", e.getMessage());
        }
    }
}