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

    public RepairRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Repair repair) {
        entityManager.getTransaction().begin();
        entityManager.persist(repair);
        entityManager.getTransaction().commit();
    }

    @Override
    public Repair get(long id) {
        return entityManager.find(Repair.class, id);
    }

    public List<Repair> getRepairByDate(LocalDateTime date) {
        return entityManager.createQuery("SELECT r FROM Repair r WHERE r.date = :date", Repair.class)
                            .setParameter("date", date)
                            .getResultList();
    }

    public List<Repair> getRepairByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return entityManager.createQuery("SELECT r FROM Repair r WHERE r.date >= :dateFrom and r.date <= :dateTo", Repair.class)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("dateTo", dateTo)
                            .getResultList();
    }

    public List<Repair> getRepairByOwnerId(long id) {
        return entityManager.createQuery("SELECT r FROM Repair r WHERE r.ownerId = :id", Repair.class)
                            .setParameter("id", id)
                            .getResultList();
    }

    @Override
    public void delete(Repair repair) {
        entityManager.getTransaction().begin();
        entityManager.remove(repair);
        entityManager.getTransaction().commit();
    }
}
