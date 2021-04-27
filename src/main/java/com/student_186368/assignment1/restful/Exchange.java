/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.restful;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author 186368
 */
// the XmlRootElement annotation is required if you want to be able to send XML representations of Employee objects
@XmlRootElement(name = "conversion")
public class Exchange {
    
    String fromCurrency;
    Double exchangeRate;
    String toCurrency;
    Double exchangedAmount;

    // default constructor
    public Exchange() {
    }

    // the XmlAttribute annotation is required if you want to be able to send XML representations of Employee objects
    // note that the annotation MUST be set before the public getter method for each field
    @XmlAttribute
    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    // the XmlAttribute annotation is required if you want to be able to send XML representations of Employee objects
    // note that the annotation MUST be set before the public getter method for each field
    @XmlAttribute
    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setFromCash(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    // the XmlAttribute annotation is required if you want to be able to send XML representations of Employee objects
    // note that the annotation MUST be set before the public getter method for each field
    @XmlAttribute
    public String getsetToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    // the XmlAttribute annotation is required if you want to be able to send XML representations of Employee objects
    // note that the annotation MUST be set before the public getter method for each field
    @XmlAttribute
    public Double getExchangedAmount() {
        return exchangedAmount;
    }

    public void setExchangedAmount(Double exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    
}
