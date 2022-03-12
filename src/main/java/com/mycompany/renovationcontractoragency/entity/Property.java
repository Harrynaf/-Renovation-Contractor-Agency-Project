/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import com.mycompany.renovationcontractoragency.enums.PropertyType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ioannis Psathas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    private String id;
    private String address;
    private LocalDate constructionYear;
    private PropertyType type;
    private String ownerId;
}