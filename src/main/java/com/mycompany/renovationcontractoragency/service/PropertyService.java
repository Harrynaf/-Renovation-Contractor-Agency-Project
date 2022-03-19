/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.renovationcontractoragency.service;

import com.mycompany.renovationcontractoragency.entity.Property;

/**
 *
 * @author Ioannis Psathas
 */
public interface PropertyService extends Service<Property>{
    Property getByVat(String vat);
    Property get(Long id);
}
