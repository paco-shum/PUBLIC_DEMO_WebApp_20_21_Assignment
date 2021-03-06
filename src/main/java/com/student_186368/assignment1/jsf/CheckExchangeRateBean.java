/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.ExchangeRate;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import com.student_186368.assignment1.ejb.UserServiceDAO;

/**
 *CheckExchangeRateBean is for ExchangeRate page
 * @author 186368
 */
@Named
@RequestScoped
@DeclareRoles("users")
public class CheckExchangeRateBean {
    
    @EJB
    UserServiceDAO usrSrv;
    @EJB
    ExchangeRate er;
    Double GBP;
    Double USD;
    Double EUR;

    public CheckExchangeRateBean() {
    }
    
    public String getCurrency() {
        return usrSrv.getUserCurency(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
    }
    //need to change arg to pass Currency
    public Double getGBP() {
        String currency = usrSrv.getUserCurency(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        //return er.getGBP(currency);
        return er.getExchange(currency, 1d, "GBP");
    }
    
    public Double getUSD() {
        String currency = usrSrv.getUserCurency(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        //return er.getUSD(currency);
        return er.getExchange(currency, 1d, "USD");
    }
    
    public Double getEUR() {
        String currency = usrSrv.getUserCurency(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        //return er.getEUR(currency);
        return er.getExchange(currency, 1d, "EUR");
    }
    
}
