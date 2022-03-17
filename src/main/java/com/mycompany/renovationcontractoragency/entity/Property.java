/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.PropertyType;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 *
 * @author Ioannis Psathas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Property implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ePropertyCode")
    private String eCode;
    @Column(name = "address")
    private String address;
    @Column(name = "yearOfConstruction")
    private LocalDate constructionYear;
    @Column(name = "propertyType")
    @Enumerated(value = EnumType.STRING)
    private PropertyType type;
    @ManyToOne
    private Owner owner;

    public Property(String eCode, String address, LocalDate constructionYear, PropertyType type, Owner owner) {
        this.eCode = eCode;
        this.address = address;
        this.constructionYear = constructionYear;
        this.type = type;
        this.owner = owner;
    }
}