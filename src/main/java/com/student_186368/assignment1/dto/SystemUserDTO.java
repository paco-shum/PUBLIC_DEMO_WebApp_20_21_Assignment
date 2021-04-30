/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.dto;

import java.io.Serializable;


/**
 *
 * @author 186368
 */
public class SystemUserDTO implements Serializable {
    public Long id;
    public String username;
    public String userpassword;
    public String name;
    public String surname;
    public String currency;
    public Double balance;
    public String groupname;
    
}
