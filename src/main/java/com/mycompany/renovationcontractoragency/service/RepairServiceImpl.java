package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Repair;
import com.mycompany.renovationcontractoragency.repository.RepairRepo;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public class RepairServiceImpl implements RepairService {

    private final RepairRepo repairRepo;

    public RepairServiceImpl(RepairRepo repairRepo) {
        this.repairRepo = repairRepo;
    }

    @Override
    public Repair create(Repair repair) {
        repairRepo.save(repair);
        return repair;
    }

    @Override
    public void delete(Repair repair) throws EntityNotFoundException {
        if (get(repair.getRepairId()) != null) {
            repairRepo.delete(repair);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Repair update(Repair repair) throws EntityNotFoundException {
        if (get(repair.getRepairId()) != null) {
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

    @Override
    public List<Repair> getRepairByOwnerAndProperty(long ownerId, long propertyId) {
        return repairRepo.getRepairByOwnerAndProperty(ownerId, propertyId);
    }
}