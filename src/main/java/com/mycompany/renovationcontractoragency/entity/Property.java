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
@Table(name = "property")
public class Property implements Serializable {
    @Id
    @Column(name = "propertyId",unique = true,nullable = false)
    private String propertyId;
    @Column(name = "address")
    private String address;
    @Column(name = "yearOfConstruction")
    private LocalDate constructionYear;
    @Column(name = "propertyType")
    private PropertyType type;
    @ManyToOne
    private Owner owner;
}