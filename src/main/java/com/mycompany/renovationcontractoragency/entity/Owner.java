/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import lombok.ToString;
/**
 * @author hnafp
 */

@Entity
@NoArgsConstructor
@ToString
public class Owner extends User {

    @Column(unique=true)
    private String vat;
    private String name;
    private String surname;
    private String address;
    @Column(unique=true)
    private String phone_number;
    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Property> properties;

    public Owner(String vat, String name, String surname, String address, String phone_number, String email, String username, String password) {
        super(username, password);
        this.vat = vat;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }
}