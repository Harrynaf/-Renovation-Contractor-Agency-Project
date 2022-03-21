/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Repair;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Agkoutsou
 */
public class RepairRepoImpl implements RepairRepo{

    private EntityManager entityManager;

    private UserRepo userRepo;
    private PropertyRepo propertyRepo;

    public RepairRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Repair repair) {
        ManageEntity.save(entityManager, repair);
    }

    @Override
    public void delete(Repair repair) {
        ManageEntity.remove(entityManager, repair);
    }
    @Override
    public List<Repair> getAll() {
        return entityManager.createQuery("SELECT r FROM Repair r", Repair.class).getResultList();
    }

    @Override
    public Repair get(long id) {
        return entityManager.find(Repair.class, id);
    }

    @Override
    public List<Repair> getRepairByDate(LocalDateTime date) {
        return entityManager.createQuery("SELECT r FROM Repair r WHERE r.date = :date", Repair.class)
                            .setParameter("date", date)
                            .getResultList();
    }

    @Override
    public List<Repair> getRepairByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return entityManager.createQuery("SELECT r FROM Repair r WHERE r.date >= :dateFrom and r.date <= :dateTo", Repair.class)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("dateTo", dateTo)
                            .getResultList();
    }

    @Override
    public List<Repair> getRepairByOwnerId(long id) {
        return entityManager.createQuery("SELECT r FROM Repair r WHERE r.owner.id = :id", Repair.class)
                            .setParameter("id", id)
                            .getResultList();
    }

   
    public boolean checkExists(Repair repair) {
        if (repair.getRepairId() != null) {
            return get(repair.getRepairId()) != null;
        }
        return false;
    }
}