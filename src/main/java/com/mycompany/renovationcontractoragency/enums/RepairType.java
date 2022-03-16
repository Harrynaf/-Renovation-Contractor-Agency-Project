/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.renovationcontractoragency.enums;

/**
 *
 * @author Agkoutsou
 */
public enum RepairType {
    PAINTING("Painting"), INSULATION("Insulation"), FRAMES("Frames"), PLUMPING("Plumbing"), ELECTRICAL_WORK("Electrical work");

    private final String repairType;

    RepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRepairType() {
        return repairType;
    }

    @Override
    public String toString() {return "RepairType{" + "repairType=" + repairType + '}';}
}