/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.PaymentService;
import com.student_186368.assignment1.ejb.UserService;
import com.student_186368.assignment1.entity.PaymentTransaction;
import com.student_186368.assignment1.entity.SystemUser;
import com.student_186368.assignment1.entity.SystemUserGroup;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PacoShum
 */
@Named
@RequestScoped
public class AdminBean {
    
    @EJB
    UserService usrSrv;
    @EJB
    PaymentService ps;
    ExchangeRate er;
    String username;
    String userpassword;
    String name;
    String surname;
    String currency;
    Double balance;
    String userGroup;
    Boolean toConfirm;

    public AdminBean() {
    }
    
    
    
    public List<PaymentTransaction> getAllTransactions() {
        return ps.getAllTransactions();
    }
    
    public String getLoginName() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return usrSrv.getUser(sender.getUsername()).getName();
    }
    
    public String register() {
        //curreny exchange, change to RESTful call in the future
        //balance = er.getExchange(currency, balance);
        //check user exist
        if (!usrSrv.checkUserExist(username)){
            usrSrv.registerUser(username, userpassword, name, surname, currency, er.getExchange("GBP", balance, currency), userGroup);
            return "success";
        }
        // invalid
        FacesContext.getCurrentInstance().addMessage("loginForm:username", new FacesMessage("Error: username exist!"));
        return null;
    }

    public String deleteUser(){
        if (toConfirm){
            usrSrv.deleteUser(Long.parseLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("usersForm:userID")));
            return "success";
        }
        return null;
    }
    public List<SystemUser> getUsersList(){
        try {
            return usrSrv.getAllUsers();
        } catch (Exception e){
            return null;
        }
    }
    
    public List<SystemUserGroup> getUsersAtGroupList(){
        try {
            return usrSrv.getAllUsersAtGroup();
        } catch (Exception e){
            return null;
        }
    }
    
    public List<SystemUser> getUsersIDList(){
        try {
            return usrSrv.getAllUsersID();
        } catch (Exception e){
            return null;
        }
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getToConfirm() {
        return toConfirm;
    }

    public void setToConfirm(Boolean toConfirm) {
        this.toConfirm = toConfirm;
    }
    
    
}
