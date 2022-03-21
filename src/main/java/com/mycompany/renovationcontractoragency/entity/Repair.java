/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.RepairStatus;
import com.mycompany.renovationcontractoragency.enums.RepairType;
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
@Entity
@Table(name = "repair", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ownerId", "propertyId"})})
public class Repair implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long repairId;
    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "id", unique = true)
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

    public Repair(Owner owner, Property property, LocalDateTime date, String description, RepairType type, RepairStatus status, BigDecimal cost, String toDoDesc) {
        this.owner = owner;
        this.property = property;
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.cost = cost;
        this.toDoDesc = toDoDesc;
    }
}
