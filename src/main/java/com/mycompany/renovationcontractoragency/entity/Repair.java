/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.RepairStatus;
import com.mycompany.renovationcontractoragency.enums.RepairType;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Agkoutsou
 */
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

    public Repair() {
    }

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

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RepairType getType() {
        return type;
    }

    public void setType(RepairType type) {
        this.type = type;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getToDoDesc() {
        return toDoDesc;
    }

    public void setToDoDesc(String toDoDesc) {
        this.toDoDesc = toDoDesc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.repairId);
        hash = 43 * hash + Objects.hashCode(this.owner);
        hash = 43 * hash + Objects.hashCode(this.property);
        hash = 43 * hash + Objects.hashCode(this.date);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + Objects.hashCode(this.type);
        hash = 43 * hash + Objects.hashCode(this.status);
        hash = 43 * hash + Objects.hashCode(this.cost);
        hash = 43 * hash + Objects.hashCode(this.toDoDesc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Repair other = (Repair) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.toDoDesc, other.toDoDesc)) {
            return false;
        }
        if (!Objects.equals(this.repairId, other.repairId)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        if (!Objects.equals(this.property, other.property)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return "Repair{" + "repairId=" + repairId + ", owner=" + owner + ", property=" + property + ", date=" + date + ", description=" + description + ", type=" + type + ", status=" + status + ", cost=" + cost + ", toDoDesc=" + toDoDesc + '}';
    }
}
