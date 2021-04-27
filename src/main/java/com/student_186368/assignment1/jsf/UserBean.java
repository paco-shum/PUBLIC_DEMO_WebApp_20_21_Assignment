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
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PacoShum
 */
@Named
@RequestScoped
public class UserBean {

    @EJB
    UserService usrSrv;
    @EJB
    PaymentService ps;
    

    public UserBean() {
    }

    public Long getId() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return usrSrv.getUser(sender.getUsername()).getId();
    }

    public String getUsername() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return sender.getUsername();
    }

    public String getName() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return usrSrv.getUser(sender.getUsername()).getName();
    }

    public String getSurname() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return usrSrv.getUser(sender.getUsername()).getSurname();
    }

    public String getCurrency() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return usrSrv.getUser(sender.getUsername()).getCurrency();
    }

    public Double getBalance() {
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        return usrSrv.getUser(sender.getUsername()).getBalance();
    }

    public List<PaymentTransaction> getUserTransactions(String username) {
        return ps.getUserTransactions(username);
    }
    
    public List<PaymentTransaction> getTransactionsList() {
        try {
            SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
            return getUserTransactions(sender.getUsername());  
        } catch (Exception e){
            return null;
        }
    }

    public List<PaymentTransaction> getPendingTransactionsList() {
        try {
            SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
            return ps.getPendingTransactions(sender.getUsername());
        } catch (Exception e){
            return null;
        }
    }
    
    public List<PaymentTransaction> getUserPendingTransactionsList() {
        try {
            SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
            return ps.getUserPendingTransactions(sender.getUsername()); 
        } catch (Exception e){
            return null;
        }
    }
    
    public List<PaymentTransaction> getUserPendingTransactionsListID() {
        try {
            SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
            return ps.getUserPendingTransactionsID(sender.getUsername());  
        } catch (Exception e){
            return null;
        }
    }
}
