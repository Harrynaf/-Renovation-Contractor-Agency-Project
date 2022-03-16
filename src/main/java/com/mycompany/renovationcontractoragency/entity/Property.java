/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author Ioannis Psathas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private LocalDate constructionYear;
    private PropertyType type;
    private String ownerId;

@ManyToOne
    private Owner owner;
}