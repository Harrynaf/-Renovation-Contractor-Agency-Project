/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.renovationcontractoragency.enums;

/**
 *
 * @author Ioannis Psathas
 */
public enum PropertyType {
    DETACHED_HOUSE("Detached house"),MAISONETTE("Maisonette"),APARTMENT_BUILDING("Apartment building");
    
    private final String propertyType;

    private PropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    @Override
    public String toString() {
        return "PropertyType{" + "propertyType=" + propertyType + '}';
    }
}