/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;
import com.mycompany.renovationcontractoragency.entity.Owner;
import com.mycompany.renovationcontractoragency.entity.Repair;
import java.time.LocalDateTime;

/**
 *
 * @author Agkoutsou
 */
public interface RepairRepo extends Repository<Repair>{
    Repair getRepairByDate(LocalDateTime date);
    Owner getRepairByOwnerId(long VAT);
}
