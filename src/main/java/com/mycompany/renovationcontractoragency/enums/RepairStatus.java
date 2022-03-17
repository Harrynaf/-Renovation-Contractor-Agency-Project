/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.renovationcontractoragency.enums;

/**
 *
 * @author Agkoutsou
 */
public enum RepairStatus {
    PENDING("Pending"),IN_PROGRESS("In progress"),COMPLETE("Complete"),STANDBY_MODE("Standby mode");

    private final String repairStatus;

    RepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    @Override
    public String toString() {return "RepairStatus{" + "repairStatus=" + repairStatus + '}';}
}