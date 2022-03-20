/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Owner;
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

    @Override
    public void save(Repair repair) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(repair);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            System.out.println(exception.getClass());
            System.out.println(exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Repair get(long id) {
        return entityManager.find(Repair.class, id);
    }

    @Override
    public boolean checkExists(Repair repair) {
        return false;
    }

    public Repair getRepairByDate(LocalDateTime date) {
        return entityManager.find(Repair.class, date);
    }
    public Owner getRepairByOwnerId(long VAT) {
        return entityManager.find(Owner.class, VAT);
    }

    @Override
    public void delete(Repair repair) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(repair);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            System.out.println(exception.getClass());
            System.out.println(exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Repair> getAll() {
        return entityManager.createQuery("SELECT a FROM Repair a", Repair.class).getResultList();
    }
}
