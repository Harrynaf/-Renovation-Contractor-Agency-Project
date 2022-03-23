/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Repair;
import com.mycompany.renovationcontractoragency.repository.RepairRepo;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of RepairService
 *
 * @author Agkoutsou
 */
public class RepairServiceImpl implements RepairService {
    
    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final RepairRepo repairRepo;

    public RepairServiceImpl(RepairRepo repairRepo) {
        this.repairRepo = repairRepo;
    }

    /**
     * Creates a new repair
     *
     * @param repair
     * @return repair
     */
    @Override
    public Repair create(Repair repair) {
        repairRepo.save(repair);
        return repair;
    }

    /**
     * Checks if repair exists and if it does throw
     * EntityNotFoundException else delete repair
     *
     * @param repair
     */
    @Override
    public void delete(Repair repair) throws EntityNotFoundException {
        if (get(repair.getRepairId()) != null) {
            repairRepo.delete(repair);
        } else {
            logger.error("Something went wrong. EntityNotFoundException");
            throw new EntityNotFoundException();
        }
    }

    /**
     * Checks if id of repair exists and if it does not throw
     * EntityNotFoundException else update repair
     *
     * @param repair
     * @return repair
     */
    @Override
    public Repair update(Repair repair) throws EntityNotFoundException {
        if (get(repair.getRepairId()) != null) {
            repairRepo.save(repair);
            return repair;
        } else {
            logger.error("Something went wrong. EntityNotFoundException");
            throw new EntityNotFoundException();
        }
    }

    /**
     * Returns a list with all repairs
     *
     * @return List of repairs
     */
    @Override
    public List<Repair> getAll() {
        return repairRepo.getAll();
    }

    /**
     * Returns a repair with given id
     *
     * @param repair id as long
     * @return repair
     */
    @Override
    public Repair get(long id) {
        return repairRepo.get(id);
    }

    /**
     * Returns a list of repairs with given date
     *
     * @param repair date as LocalDateTime
     * @return List of repairs
     */
    @Override
    public List<Repair> getRepairByDate(LocalDateTime date) {
        return repairRepo.getRepairByDate(date);
    }

    /**
     * Returns a list of repairs within a date range
     *
     * @param repair dateFrom & dateTo as LocalDateTime
     * @return List of repairs
     */
    @Override
    public List<Repair> getRepairByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return repairRepo.getRepairByDateRange(dateFrom, dateTo);
    }

    /**
     * Returns a list of repairs with given owner id
     *
     * @param repair owner id as long
     * @return List of repairs
     */
    @Override
    public List<Repair> getRepairByOwnerId(long id) {
        return repairRepo.getRepairByOwnerId(id);
    }

    /**
     * Returns a list of repairs with given property id
     *
     * @param repair property id as long
     * @return List of repairs
     */
    @Override
    public List<Repair> getRepairByPropertyId(long id) {
        return repairRepo.getRepairByPropertyId(id);
    }
}