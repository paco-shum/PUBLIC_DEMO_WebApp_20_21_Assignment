/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 186368
 */
@NamedQuery(name="findAllTransaction", query="SELECT c FROM PaymentTransaction c ")
@Entity
public class PaymentTransaction implements Serializable {
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @NotNull
    String sendUsername;
    
    @NotNull
    String sendCurrency;
    
    @NotNull
    Double sendCash;
    
    @NotNull
    Double exchangeRate;
    
    @NotNull
    String receiveUsername;
    
    @NotNull
    String receiveCurrency;
    
    @NotNull
    Double receiveCash;
    
    @NotNull
    Boolean pending;
    
    @NotNull
    Boolean approved;

    public PaymentTransaction() {
    }

    public PaymentTransaction(String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash, Boolean pending, Boolean approved) {
        this.sendUsername = sendUsername;
        this.sendCurrency = sendCurrency;
        this.sendCash = sendCash;
        this.exchangeRate = exchangeRate;
        this.receiveUsername = receiveUsername;
        this.receiveCurrency = receiveCurrency;
        this.receiveCash = receiveCash;
        this.pending = pending;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public Double getReceiveCash() {
        return receiveCash;
    }

    public void setReceiveCash(Double receiveCash) {
        this.receiveCash = receiveCash;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.sendUsername);
        hash = 29 * hash + Objects.hashCode(this.sendCurrency);
        hash = 29 * hash + Objects.hashCode(this.sendCash);
        hash = 29 * hash + Objects.hashCode(this.exchangeRate);
        hash = 29 * hash + Objects.hashCode(this.receiveUsername);
        hash = 29 * hash + Objects.hashCode(this.receiveCurrency);
        hash = 29 * hash + Objects.hashCode(this.receiveCash);
        hash = 29 * hash + Objects.hashCode(this.pending);
        hash = 29 * hash + Objects.hashCode(this.approved);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentTransaction other = (PaymentTransaction) obj;
        if (!Objects.equals(this.sendUsername, other.sendUsername)) {
            return false;
        }
        if (!Objects.equals(this.sendCurrency, other.sendCurrency)) {
            return false;
        }
        if (!Objects.equals(this.receiveUsername, other.receiveUsername)) {
            return false;
        }
        if (!Objects.equals(this.receiveCurrency, other.receiveCurrency)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sendCash, other.sendCash)) {
            return false;
        }
        if (!Objects.equals(this.exchangeRate, other.exchangeRate)) {
            return false;
        }
        if (!Objects.equals(this.receiveCash, other.receiveCash)) {
            return false;
        }
        if (!Objects.equals(this.pending, other.pending)) {
            return false;
        }
        return Objects.equals(this.approved, other.approved);
    }

    
}
