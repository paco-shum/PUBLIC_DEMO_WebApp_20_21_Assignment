/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import com.student_186368.assignment1.ejb.ExchangeRate;
import com.student_186368.assignment1.ejb.PaymentService;
import com.student_186368.assignment1.ejb.UserService;
import com.student_186368.assignment1.entity.SystemUser;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class MakePaymentBean {
    
    @EJB
    UserService usrSrv;
    
    @EJB
    PaymentService ps;
    
    @EJB
    ExchangeRate exchangeRate;
    
    Long id;
    String sendUsername;
    String sendCurrency;
    Double sendCash;
    String receiveUsername;
    String receiveCurrency;
    Boolean approved;
    HttpServletRequest request;
    
    public MakePaymentBean() {
    }

    public String toPay(){
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        Double exchangedRate;
        Double receiverWillGetCash;
        //checkboox to approve
        if (approved){
            //check payee exist
            if ( (usrSrv.checkUserExist(receiveUsername)) && !(sender.getUsername().equals(receiveUsername))){
                //currency exchange for fund check
                if (sender.getCurrency().equals(sendCurrency)){
                    exchangedRate = sendCash;
                } else {
                    exchangedRate = exchangeRate.getExchange(sendCurrency, sendCash, sender.getCurrency());
                }
                //check sufficient fund
                if (usrSrv.checkUserBalance(sender.getUsername(), exchangedRate)){
                    //convert to payee currency
                    String payeeCurrency = usrSrv.getUserCurency(receiveUsername);
                    if (payeeCurrency.equals(sendCurrency)){
                        receiverWillGetCash = sendCash;
                    } else {
                        receiverWillGetCash = exchangeRate.getExchange(sendCurrency, sendCash, payeeCurrency);
                    }
                    try {
                        ps.createPayment(sender.getUsername(), sender.getCurrency(), exchangedRate, exchangeRate.getExchange(sender.getCurrency(), 1d, payeeCurrency), receiveUsername, payeeCurrency, receiverWillGetCash);
                        return "success";
                    } catch (Exception e){
                        System.out.println(e);
                    }
                    
                }else {
                    FacesContext.getCurrentInstance().addMessage("paymentForm:paymentAmount", new FacesMessage("Error: You have insufficient fund!"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("paymentForm:payeeUsername", new FacesMessage("Error: Payee username doesn't exist/ Payee username is same as sender!"));
            }
        }else {
            FacesContext.getCurrentInstance().addMessage("paymentForm:paymentApprove", new FacesMessage("Please approve the payment first!"));
        }
        // invalid
        return null;
    }

    public String toRequest(){
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        Double exchangedRate;
        Double receiverWillGetCash;
        //checkboox to approve
        if (approved){
            //check payee exist
            if ( (usrSrv.checkUserExist(receiveUsername)) && (!(sender.getUsername().equals(receiveUsername)))){
                //currency exchange for fund check
                if (sender.getCurrency().equals(sendCurrency)){
                    exchangedRate = sendCash;
                } else {
                    exchangedRate = exchangeRate.getExchange(sendCurrency, sendCash, sender.getCurrency());
                }
                //convert to payee currency
                String payeeCurrency = usrSrv.getUserCurency(receiveUsername);
                if (payeeCurrency.equals(sendCurrency)){
                    receiverWillGetCash = sendCash;
                } else {
                    receiverWillGetCash = exchangeRate.getExchange(sendCurrency, sendCash, payeeCurrency);
                }
                try {
                    ps.requestPayment(receiveUsername, payeeCurrency, receiverWillGetCash, exchangeRate.getExchange(sender.getCurrency(), 1d, payeeCurrency), sender.getUsername(), sender.getCurrency(), exchangedRate);
                    return "success";
                } catch (Exception e){
                    System.out.println(e);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("paymentForm:payeeUsername", new FacesMessage("Error: Payee/Payer username doesn't exist!/ Payee username is same as sender!"));
            }
        }else {
            FacesContext.getCurrentInstance().addMessage("paymentForm:paymentApprove", new FacesMessage("Please approve the payment first!"));
        }
        return null;
    }
        
    public String getSendUsername() {
        return sendUsername;
    }

    public void setSendUsername(String sendUsername) {
        this.sendUsername = sendUsername;
    }

    public String getSendCurrency() {
        return sendCurrency;
    }

    public void setSendCurrency(String sendCurrency) {
        this.sendCurrency = sendCurrency;
    }

    public Double getSendCash() {
        return sendCash;
    }

    public void setSendCash(Double sendCash) {
        this.sendCash = sendCash;
    }

    public String getReceiveUsername() {
        return receiveUsername;
    }

    public void setReceiveUsername(String receiveUsername) {
        this.receiveUsername = receiveUsername;
    }

    public String getReceiveCurrency() {
        return receiveCurrency;
    }

    public void setReceiveCurrency(String receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
