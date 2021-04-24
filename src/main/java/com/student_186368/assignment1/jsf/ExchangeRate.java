/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author PacoShum
 */
public class ExchangeRate {
   
    public static Double getGBP(String currency) {
        if (null == currency){
            return null;
        } else //curreny exchange, change to RESTful call in the future
        switch (currency) {
            case "GBP":
                return 1d;
            case "USD":
                return 1.39d;
            case "EUR":
                return 1.15d;
            default:
                return null;
        }
    }
    
    public static Double getUSD(String currency) {
        if (null == currency){
            return null;
        } else //curreny exchange, change to RESTful call in the future
        switch (currency) {
            case "GBP":
                return 0.72d;
            case "USD":
                return 1d;
            case "EUR":
                return 0.83d;
            default:
                return null;
        }
    }
    
    public static Double getEUR(String currency) {
        if (null == currency){
            return null;
        } else //curreny exchange, change to RESTful call in the future
        switch (currency) {
            case "GBP":
                return 0.87d;
            case "USD":
                return 121d;
            case "EUR":
                return 1d;
            default:
                return null;
        }
    }
    
    public static Double getGBPExchange(String currency, Double cash) {
        if (null == currency){
            return null;
        } else //curreny exchange, change to RESTful call in the future
        switch (currency) {
            case "GBP":
                return cash;
            case "USD":
                return cash*1.39d;
            case "EUR":
                return cash*1.15d;
            default:
                return null;
        }
    }
    
    public static Double getUSDExchange(String currency, Double cash) {
        if (null == currency){
            return null;
        } else //curreny exchange, change to RESTful call in the future
        switch (currency) {
            case "GBP":
                return cash*0.72d;
            case "USD":
                return cash;
            case "EUR":
                return cash*0.83d;
            default:
                return null;
        }
    }
    
    public static Double getEURExchange(String currency, Double cash) {
        if (null == currency){
            return null;
        } else //curreny exchange, change to RESTful call in the future
        switch (currency) {
            case "GBP":
                return cash*0.87d;
            case "USD":
                return cash*121d;
            case "EUR":
                return cash;
            default:
                return null;
        }
    }
    
    public static Double getExchange (String fromCurrency, Double fromCash, String toCurrency){
        //Double exchangedRate = 0d;
        //DecimalFormat df = new DecimalFormat("#.##");
        //df.setRoundingMode(RoundingMode.CEILING);
        BigDecimal b1 = new BigDecimal(fromCash);
        
        switch (fromCurrency) {
            case "GBP":
                switch (toCurrency) {
                    case "GBP":
                        return fromCash;
                    case "USD":
                        return b1.multiply(new BigDecimal(1.39d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    case "EUR":
                        return b1.multiply(new BigDecimal(1.15d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    default:
                        return 1d;
                }
            case "USD":
                switch (toCurrency) {
                    case "GBP":
                        return b1.multiply(new BigDecimal(0.72d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    case "USD":
                        return fromCash;
                    case "EUR":
                        return b1.multiply(new BigDecimal(0.83d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    default:
                        return 1d;
                }
            case "EUR":
                switch (toCurrency) {
                    case "GBP":
                        return b1.multiply(new BigDecimal(0.87d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    case "USD":
                        return b1.multiply(new BigDecimal(1.21d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    case "EUR":
                        return fromCash;
                    default:
                        return 1d;
                }
            default:
                return 1d;
        }
    } 
        
}
