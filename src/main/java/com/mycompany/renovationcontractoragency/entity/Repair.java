/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.RepairStatus;
import com.mycompany.renovationcontractoragency.enums.RepairType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Agkoutsou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    private String ownerId;
    private String propertyId;
    private LocalDateTime date;
    private String description;
    private RepairType type;
    private RepairStatus status = RepairStatus.STANDBY_MODE;
    private BigDecimal cost;
    private String toDoDesc;
}