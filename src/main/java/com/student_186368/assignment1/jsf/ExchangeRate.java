/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.jsf;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author PacoShum
 */
public class ExchangeRate {

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
