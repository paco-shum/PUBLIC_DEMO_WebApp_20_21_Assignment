/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.UserService;
import com.student_186368.assignment1.entity.SystemUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author PacoShum
 */
@Named
@RequestScoped
public class UserBean {

    @EJB
    UserService usrSrv;
    
    Long id;
    String username;
    String userpassword;
    String name;
    String surname;
    String currency;
    Float cash;

    public UserBean() {
    }

    public Long getId(String username) {
        return usrSrv.getUser(username).getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername(String username) {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName(String username) {
        return usrSrv.getUser(username).getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(String username) {
        return usrSrv.getUser(username).getSurname();
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCurrency(String username) {
        return usrSrv.getUser(username).getCurrency();
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getCash(String username) {
        return usrSrv.getUser(username).getCash();
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }
 
}
