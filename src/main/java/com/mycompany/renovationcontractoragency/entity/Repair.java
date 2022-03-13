/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Agkoutsou
 */
@Data
public class Repair {

    private String ownerId;
    private String propertyId;
    private LocalDateTime date;
    private String description;
    private String type;
    private String status;
    private BigDecimal cost;
    private String toDoDesc;

    public Repair(String ownerId, String propertyId, LocalDateTime date, String description, String type, String status, BigDecimal cost, String toDoDesc) {
        this.ownerId = ownerId;
        this.propertyId = propertyId;
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.cost = cost;
        this.toDoDesc = toDoDesc;
    }
}
