/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Property;
import com.mycompany.renovationcontractoragency.repository.PropertyRepo;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Ioannis Psathas
 */
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepo propertyRepo;

    public PropertyServiceImpl(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    @Override
    public Property create(Property property) {
        if (!checkExists(property)) {
            propertyRepo.save(property);
            return property;
        } else {
            throw new EntityExistsException();
        }
    }

    @Override
    public void delete(Property property) {
        if (checkExists(property)) {
            propertyRepo.delete(property);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Property update(Property property) {
        if (checkExists(property)) {
            propertyRepo.save(property);
            return property;
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    @Override
    public Property get(long id) {
        return propertyRepo.get(id);
    }
    
    @Override
    public List<Property> getAll() {
        return propertyRepo.getAll();
    }

    @Override
    public List<Property> getByVat(String vat) {
        return propertyRepo.getByVat(vat);
    }

    @Override
    public boolean checkExists(Property property) {
        return propertyRepo.checkExists(property);
    }
}