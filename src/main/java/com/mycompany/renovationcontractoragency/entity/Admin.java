/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.renovationcontractoragency.entity;

import lombok.Data;

/**
 *
 * @author hnafp
 */
@Data
public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    
}