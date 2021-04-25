/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author PacoShum
 */
@Named
@RequestScoped
public class CheckExchangeRateBean {
    
    @EJB
    UserService usrSrv;
    ExchangeRate er;
    Double GBP;
    Double USD;
    Double EUR;

    public CheckExchangeRateBean() {
    }
    
    public String getCurrency(String username) {
        return usrSrv.getUser(username).getCurrency();
    }
    //need to change arg to pass Currency
    public Double getGBP(String username) {
        String currency = usrSrv.getUser(username).getCurrency();
        //return er.getGBP(currency);
        return er.getExchange(currency, 1d, "GBP");
    }
    
    public Double getUSD(String username) {
        String currency = usrSrv.getUser(username).getCurrency();
        //return er.getUSD(currency);
        return er.getExchange(currency, 1d, "USD");
    }
    
    public Double getEUR(String username) {
        String currency = usrSrv.getUser(username).getCurrency();
        //return er.getEUR(currency);
        return er.getExchange(currency, 1d, "EUR");
    }
    
}
