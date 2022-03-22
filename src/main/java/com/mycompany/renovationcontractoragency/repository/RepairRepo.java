/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;
import com.mycompany.renovationcontractoragency.entity.Repair;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Agkoutsou
 */
public interface RepairRepo extends Repository<Repair>{
    List<Repair> getRepairByDate(LocalDateTime date);
    List<Repair> getRepairByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo);
    List<Repair> getRepairByOwnerId(long id);
    List<Repair> getRepairByOwnerAndProperty(long ownerId, long propertyId);
}