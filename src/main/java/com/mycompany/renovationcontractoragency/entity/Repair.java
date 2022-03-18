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

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Agkoutsou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "repair")
public class Repair implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long repairId;
    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "vat")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "id")
    private Property property;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "repairDescription")
    private String description;
    @Column(name = "repairType")
    @Enumerated(value = EnumType.STRING)
    private RepairType type;
    @Column(name = "repairStatus")
    @Enumerated(value = EnumType.STRING)
    private RepairStatus status = RepairStatus.STANDBY_MODE;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "workToDoDescription")
    private String toDoDesc;
}
