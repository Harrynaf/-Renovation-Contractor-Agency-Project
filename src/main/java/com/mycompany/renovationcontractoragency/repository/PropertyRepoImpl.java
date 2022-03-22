/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.repository;

import com.mycompany.renovationcontractoragency.entity.Property;
import java.util.List;

/**
 * @author Ioannis Psathas
 */
public class PropertyRepoImpl implements PropertyRepo {

    @Override
    public Property get(long id) {
        return entityManager.find(Property.class, id);
    }

    @Override
    public List<Property> getByVat(String vat) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.owner.vat = :vat", Property.class).setParameter("vat", vat).getResultList();
    }

    @Override
    public List<Property> getByECode(String eCode) {
        return entityManager.createQuery("SELECT p FROM Property p WHERE p.eCode = :ecode", Property.class).setParameter("ecode", eCode).getResultList();
    }

    @Override
    public List<Property> getAll() {
        return entityManager.createQuery("SELECT p FROM Property p", Property.class).getResultList();
    }
}
