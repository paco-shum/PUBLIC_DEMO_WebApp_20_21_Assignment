/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

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
    
    ExchangeRate exchangeRate;
    PaymentService ps;
    String sendUsername;
    String sendCurrency;
    Double sendCash;
    //Double exchangeRate;
    String receiveUsername;
    String receiveCurrency;
    Double receiveCash;
    //Boolean pending;
    Boolean approved;
    HttpServletRequest request;
    
    //temp var
    Double exchangedRate;
    Double receiverWillGetCash;

    public MakePaymentBean() {
    }

    public void toPay(){
    //public void toPay(String senderUsername, String receiveUsername, String sendCurrency, Double sendCash, Boolean approved){
        SystemUser sender = usrSrv.getUser(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser());
        //SystemUser sender = usrSrv.getUser(senderUsername);
        //Double exchangedRate = 0d;
        
        //checkboox to approve
        if (approved){
            //check payee exist
            if (usrSrv.checkUserExist(receiveUsername)){
                //currency exchange for fund check
                if (sender.getCurrency().equals(sendCurrency)){
                    exchangedRate = sendCash;
                } else {
                    exchangedRate = exchangeRate.getExchange(sendCurrency, sendCash, sender.getCurrency());
                }
                //check sufficient fund
                if (sender.getBalance()>= exchangedRate){
                    //MAY NEED TO MOVE TO PaymentService
                    //convert to payee currency
                    //Double receiverWillGetCash = 0d;
                    String payeeCurrency = usrSrv.getUser(receiveUsername).getCurrency();
                    if (payeeCurrency.equals(sendCurrency)){
                        receiverWillGetCash = sendCash;
                    } else {
                        receiverWillGetCash = exchangeRate.getExchange(sendCurrency, sendCash, payeeCurrency);
                    }
//                    System.out.println(sender.getUsername());
//                    System.out.println(sender.getCurrency());
//                    System.out.println(exchangedRate);
//                    System.out.println(exchangeRate.getExchange(sender.getCurrency(), 1d, payeeCurrency));
//                    System.out.println(receiveUsername);
//                    System.out.println(payeeCurrency);
//                    System.out.println(receiverWillGetCash);
//                    System.out.println(approved);
                    ps.createPayment(sender.getUsername(), sender.getCurrency(), exchangedRate, exchangeRate.getExchange(sender.getCurrency(), 1d, payeeCurrency), receiveUsername, payeeCurrency, receiveCash, approved);
                    
                }else {
                    FacesContext.getCurrentInstance().addMessage("paymentForm:paymentAmount", new FacesMessage("Error: Payee username doesn't exist!"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("paymentForm:payeeUsername", new FacesMessage("Error: Payee username doesn't exist!"));
            }
        }else {
            FacesContext.getCurrentInstance().addMessage("paymentForm:paymentApprove", new FacesMessage("Please approve the payment first!"));
        }
        
        

        // invalid
        //return null;
    }

//    public MakePaymentBean(String sendUsername, String sendCurrency, Float sendCash, Float exchangeRate, String receiveUsername, String receiveCurrency, Float receiveCash, Boolean pending, Boolean approved) {
//        this.sendUsername = sendUsername;
//        this.sendCurrency = sendCurrency;
//        this.sendCash = sendCash;
//        this.exchangeRate = exchangeRate;
//        this.receiveUsername = receiveUsername;
//        this.receiveCurrency = receiveCurrency;
//        this.receiveCash = receiveCash;
//        this.pending = pending;
//        this.approved = approved;
//    }
        
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

//    public Double getExchangeRate() {
//        return exchangeRate;
//    }
//
//    public void setExchangeRate(Double exchangeRate) {
//        this.exchangeRate = exchangeRate;
//    }

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

    public Double getReceiveCash() {
        return receiveCash;
    }

    public void setReceiveCash(Double receiveCash) {
        this.receiveCash = receiveCash;
    }

//    public Boolean getPending() {
//        return pending;
//    }
//
//    public void setPending(Boolean pending) {
//        this.pending = pending;
//    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    
    
//    public Double getExchange (String fromCurrency, Double fromCash, String toCurrency){
//        //Double exchangedRate = 0d;
//        //DecimalFormat df = new DecimalFormat("#.##");
//        //df.setRoundingMode(RoundingMode.CEILING);
//        BigDecimal b1 = new BigDecimal(fromCash);
//        
//        if (fromCurrency.equals("GBP")){
//            if (toCurrency.equals("GBP")){
//                return fromCash;
//            }else if (toCurrency.equals("USD")){
//                return b1.multiply(new BigDecimal(1.39d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
//            }else if (toCurrency.equals("EUR")){
//                return b1.multiply(new BigDecimal(1.15d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
//            }else {
//                return 0d;
//            }
//        }else if (fromCurrency.equals("USD")){
//            if (toCurrency.equals("GBP")){
//                return b1.multiply(new BigDecimal(0.72d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
//            }else if (toCurrency.equals("USD")){
//                return fromCash;
//            }else if (toCurrency.equals("EUR")){
//                return b1.multiply(new BigDecimal(0.83d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
//            }else {
//                return 0d;
//            }
//        }else if (fromCurrency.equals("EUR")){
//            if (toCurrency.equals("GBP")){
//                return b1.multiply(new BigDecimal(0.87d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
//            }else if (toCurrency.equals("USD")){
//                return b1.multiply(new BigDecimal(1.21d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
//            }else if (toCurrency.equals("EUR")){
//                return fromCash;
//            }else {
//                return 0d;
//            }
//        }else {
//            return 0d;
//        }
//    }
}
