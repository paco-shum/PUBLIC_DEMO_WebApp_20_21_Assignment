package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.ExchangeRate;
import com.student_186368.assignment1.ejb.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author parisis
 */
@Named
@RequestScoped
public class RegistrationBean {

    @EJB
    UserService usrSrv;
    @EJB
    ExchangeRate er;
    String username;
    String userpassword;
    String name;
    String surname;
    String currency;
    Double balance;
    String userGroup;

    public RegistrationBean() {
    }

    //call the injected EJB
    public String register() {
        //curreny exchange
        //er.getExchange(currency, balance);
        //check user exist
        if (!usrSrv.checkUserExist(username)){
            usrSrv.registerUser(username, userpassword, name, surname, currency, er.getExchange("GBP", 1000d, currency));
            return "index";
        }
        // invalid
        FacesContext.getCurrentInstance().addMessage("loginForm:username", new FacesMessage("Error: username exist!"));
        return null;
    }
    
    
    public UserService getUsrSrv() {
        return usrSrv;
    }

    public void setUsrSrv(UserService usrSrv) {
        this.usrSrv = usrSrv;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    
}
