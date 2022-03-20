package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Repair;
import com.mycompany.renovationcontractoragency.repository.*;
import org.hibernate.Session;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public class RepairServiceImpl implements RepairService {
    private EntityManager entityManager;
    private RepairRepo repairRepo;
    private UserRepo userRepo;
    private PropertyRepo propertyRepo;
    Session session;

    public RepairServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.repairRepo = new RepairRepoImpl(entityManager);
        session = entityManager.unwrap(Session.class);
    }

    @Override
    public Repair create(Repair repair) {
        if (!checkExists(repair)) {
            if (!checkOwner(repair)) {
                throw new EntityExistsException();
            }
            if (!checkProperty(repair)) {
                throw new EntityExistsException();
            }
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
            if (!checkOwner(repair)) {
                throw new EntityExistsException();
            }
            if (!checkProperty(repair)) {
                throw new EntityExistsException();
            }
            repairRepo.save(repair);
            return repair;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Repair> getAll() {
        return session.createQuery("SELECT r FROM Repair r", Repair.class).getResultList();
    }

    @Override
    public boolean checkExists(Repair repair) {
        return repairRepo.get(repair.getRepairId()) != null;
    }

    public boolean checkOwner(Repair repair) {
        return userRepo.get(repair.getOwner().getId()) != null;
    }

    public boolean checkProperty(Repair repair) {
        return propertyRepo.get(repair.getProperty().getId()) != null;
    }
}
