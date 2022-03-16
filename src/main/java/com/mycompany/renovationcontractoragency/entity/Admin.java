/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author hnafp
 */
@ToString

@Entity
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Admin(String username, String password) {
        super(username, password);
    }
}