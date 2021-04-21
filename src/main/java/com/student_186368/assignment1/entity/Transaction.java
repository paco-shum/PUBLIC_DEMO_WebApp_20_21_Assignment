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
 * @author PacoShum
 */
@NamedQuery(name="findAllTransaction", query="SELECT c FROM Transaction c ")
@Entity
public class Transaction implements Serializable {
    
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @NotNull
    String sendUsername;
    
    @NotNull
    String sendCurrency;
    
    @NotNull
    Float sendCash;
    
    @NotNull
    Float exchangeRate;
    
    @NotNull
    String receiveUsername;
    
    @NotNull
    String receiveCurrency;
    
    @NotNull
    Float receiveCash;
    
    @NotNull
    Boolean pending;

    public Transaction() {
    }

    public Transaction(String sendUsername, String sendCurrency, Float sendCash, Float exchangeRate, String receiveUsername, String receiveCurrency, Float receiveCash, Boolean pending) {
        this.sendUsername = sendUsername;
        this.sendCurrency = sendCurrency;
        this.sendCash = sendCash;
        this.exchangeRate = exchangeRate;
        this.receiveUsername = receiveUsername;
        this.receiveCurrency = receiveCurrency;
        this.receiveCash = receiveCash;
        this.pending = pending;
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

    public Float getSendCash() {
        return sendCash;
    }

    public void setSendCash(Float sendCash) {
        this.sendCash = sendCash;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Float exchangeRate) {
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

    public Float getReceiveCash() {
        return receiveCash;
    }

    public void setReceiveCash(Float receiveCash) {
        this.receiveCash = receiveCash;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.sendUsername);
        hash = 59 * hash + Objects.hashCode(this.sendCurrency);
        hash = 59 * hash + Objects.hashCode(this.sendCash);
        hash = 59 * hash + Objects.hashCode(this.exchangeRate);
        hash = 59 * hash + Objects.hashCode(this.receiveUsername);
        hash = 59 * hash + Objects.hashCode(this.receiveCurrency);
        hash = 59 * hash + Objects.hashCode(this.receiveCash);
        hash = 59 * hash + Objects.hashCode(this.pending);
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
        final Transaction other = (Transaction) obj;
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
        return true;
    }

        
    
}
