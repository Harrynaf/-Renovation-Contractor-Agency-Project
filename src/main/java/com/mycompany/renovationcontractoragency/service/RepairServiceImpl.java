package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Repair;
import com.mycompany.renovationcontractoragency.repository.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public class RepairServiceImpl implements RepairService {
    private EntityManager entityManager;
    private UserRepo userRepo;
    private PropertyRepo propertyRepo;
    private RepairRepo repairRepo;

    public RepairServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.userRepo = new UserRepoImpl(entityManager);
        this.propertyRepo = new PropertyRepoImpl(entityManager);
        this.repairRepo = new RepairRepoImpl(entityManager);
    }

    @Override
    public Repair create(Repair repair) {
        if (!checkExists(repair)) {
            if (!userRepo.findByUsername(repair.getOwner())) {
                throw new EntityExistsException(); // TODO Custom Exception
            }
//            if (!propertyRepo.findByUsername(repair.getProperty())) {
//                throw new EntityExistsException(); // TODO Custom Exception
//            }
            repairRepo.save(repair);
            return repair;
        } else {
            throw new EntityExistsException();
        }
    }

    @Override
    public void delete(Repair repair) {
        if (checkExists(repair)) {
            repairRepo.delete(repair);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Repair update(Repair repair) {
        if (checkExists(repair)) {
            if (!userRepo.findByUsername(repair.getOwner())) {
                throw new EntityExistsException(); // TODO Custom Exception
            }
//            if (!propertyRepo.findByUsername(repair.getProperty())) {
//                throw new EntityExistsException(); // TODO Custom Exception
//            }
            repairRepo.save(repair);
            return repair;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Repair> getAll() {
        return repairRepo.getAll();
    }

    @Override
    public Repair get(long id) {
        return repairRepo.get(id);
    }

    @Override
    public List<Repair> getRepairByDate(LocalDateTime date) {
        return repairRepo.getRepairByDate(date);
    }

    @Override
    public List<Repair> getRepairByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return repairRepo.getRepairByDateRange(dateFrom, dateTo);
    }

    @Override
    public List<Repair> getRepairByOwnerId(long id) {
        return repairRepo.getRepairByOwnerId(id);
    }

    
    public boolean checkExists(Repair repair) {
        return false;//repairRepo.checkExists(repair);
    }
}
