/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.PaymentServiceDAO;
import com.student_186368.assignment1.ejb.TimeServiceEJB;
import com.student_186368.assignment1.entity.PaymentTransaction;
import com.student_186368.assignment1.entity.SystemUser;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import com.student_186368.assignment1.ejb.UserServiceDAO;

/**
 * anything about user to get from here
 * @author 186368
 */
@Named
@RequestScoped
@DeclareRoles("users")
public class UserBean {

    @EJB
    UserServiceDAO usrSrv;
    @EJB
    PaymentServiceDAO ps;
    @EJB
    TimeServiceEJB ts;
    

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
    
    public long getTime() {
        //uncomment for time service
        //return ts.getTime();
        return 0;
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
