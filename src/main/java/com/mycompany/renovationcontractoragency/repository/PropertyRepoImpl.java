/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Property;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * An ipmplementation of PropertyRepo
 *
 * @author Ioannis Psathas
 */
public class PropertyRepoImpl extends ManageEntity implements PropertyRepo {
    private final EntityManager entityManager;
    
    public PropertyRepoImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }
    

    public void save(Property property) {
        super.saveEntity(property);
    }


    public void delete(Property property) {
        super.deleteEntity(property);
    }
    
    /**
     * Returns a property with given id
     *
     * @param  id as long
     * @return property
     */
    @Override
    public Property get(long id) {
        return entityManager.find(Property.class, id);
    }

    /**
     * Returns a list of properties with given vat
     * @param vat
     * @return
     */
    @Override
    public List<Property> getByVat(String vat) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.owner.vat = :vat", Property.class).setParameter("vat", vat).getResultList();
    }

    /**
     * Returns a list of properties with given eCode
     *
     * @param eCode
     * @return List of properties
     */
    @Override
    public List<Property> getByECode(String eCode) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.eCode = :ecode", Property.class).setParameter("ecode", eCode).getResultList();
    }

    /**
     * Retuns a list with all properties
     *
     * @return List of propeties
     */
    @Override
    public List<Property> getAll() {
        return entityManager.createQuery("SELECT p FROM Property p", Property.class).getResultList();
    }
}