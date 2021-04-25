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
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PacoShum
 */
@Named
@RequestScoped
public class ApprovePaymentBean {
    @EJB
    UserService usrSrv;
    
    @EJB
    PaymentService ps;
    
    Long paymentID;
    Boolean approved;
    String senderUsername;
    
    public void toConfirmPay(){
        if (approved){
            ps.approveTransaction(Long.parseLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("paymentForm:paymentID")));
        }
    }
    
    public void toRejectPay(){
        if (approved){
            ps.rejectTransaction(Long.parseLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("paymentForm:paymentID")));
        }
    }
    
    public List<PaymentTransaction> getUserPendingTransactionsID(String username) {
        return ps.getUserPendingTransactionsID(username);
    }
    
    public List<PaymentTransaction> getUserPendingTransactionsListID() {
        try {
            SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
            return getUserPendingTransactionsID(sender.getUsername());  
        } catch (Exception e){
            return null;
        }
    }
    
    public List<PaymentTransaction> getUserPendingTransactions(String username) {
        return ps.getUserPendingTransactions(username);
    }
    
    public List<PaymentTransaction> getUserPendingTransactionsList() {
        try {
            SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
            return getUserPendingTransactions(sender.getUsername());  
        } catch (Exception e){
            return null;
        }
    }

    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
    
}
