/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

/**
 * @author hnafp
 */
@ToString

@Entity
@NoArgsConstructor
@Data
public class Owner extends User {

    private String vat;
    private String name;
    private String surname;
    private String address;
    private String phone_number;
    private String email;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

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