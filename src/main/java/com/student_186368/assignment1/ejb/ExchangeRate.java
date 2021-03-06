/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.restful.Exchange;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * ExchangeRate for getting ExchangeRate over RESTful
 * @author 186368
 */
@Stateless
public class ExchangeRate {

    String url = "http://localhost:10000/186368/conversion";
    String fromCurrency;
    String toCurrency;
    Double fromCash;

    //get exchange rate or get amount of exchanged money by given currency
    public Double getExchange (String fromCurrency, Double fromCash, String toCurrency){
        Client client = ClientBuilder.newClient();
        Double exchangedAmt = null;
        String newURL = (url + "/" + fromCurrency + "/" + toCurrency + "/" + fromCash);
        try {
            Exchange ex = client.target(newURL)
            .request(MediaType.APPLICATION_JSON)
            .get(Exchange.class);
            exchangedAmt = ex.getExchangedAmount();
        }catch (Exception e){
            System.out.println("Access to RESTful failed, fallback to backup");
            System.out.println(newURL);
            System.out.println(e);
        }

        if (exchangedAmt == null){
            return getExchangeBackup(fromCurrency, fromCash, toCurrency);
        }else {
            return exchangedAmt;
        }
    } 
    
    //legacy method in case rest-api not working NOTE in real world, it should return error to user showing service is offline
    public Double getExchangeBackup (String fromCurrency, Double fromCash, String toCurrency){
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
