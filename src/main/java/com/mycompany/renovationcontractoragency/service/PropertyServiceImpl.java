/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.repository.PropertyRepo;
import java.util.List;

/**
 *
 * @author Ioannis Psathas
 */
public class PropertyServiceImpl implements PropertyService{
    
    private final PropertyRepo propertyRepo;

    public PropertyServiceImpl(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }
    
    @Override
    public Property create(Property property) {
        propertyRepo.save(property);
    return property;
    }

    @Override
    public void delete(Property property) {
        propertyRepo.delete(property);
    }

    @Override
    public Property update(Property property) {
            propertyRepo.save(property);
    return property;
    }
    
    @Override
    public List<Property> getAll() {
        return propertyRepo.getAll();
    }

    @Override
    public Property getByVat(String vat) {
        return propertyRepo.getByVat(vat);
    }

    @Override
    public Property get(Long id) {
        return propertyRepo.get(id);
    }
}