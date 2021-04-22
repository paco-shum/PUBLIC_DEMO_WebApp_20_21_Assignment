package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    
    String username;
    String userpassword;
    String name;
    String surname;
    String currency;
    Float balance;

    public RegistrationBean() {

    }

    //call the injected EJB
    public String register() {
        if (null != currency)switch (currency) {
            case "GBP":
                balance = 1000f;
                break;
            case "USD":
                balance = 1000f * 1.40f;
                break;
            case "EUR":
                balance = 1000f * 1.16f;
                break;
            default:
                break;
        }
        usrSrv.registerUser(username, userpassword, name, surname, currency, balance);
        return "index";
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

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float cash) {
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

    
}
