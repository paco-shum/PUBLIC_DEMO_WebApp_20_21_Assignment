/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.PaymentTransaction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 186368
 */
@Local
public interface PaymentServiceDAO {
    public List<PaymentTransaction> getAllTransactions();
    public List<PaymentTransaction> getUserTransactions(String username);
    public List<PaymentTransaction> getPendingTransactions(String username);
    public List<PaymentTransaction> getUserPendingTransactions(String username);
    public List<PaymentTransaction> getUserPendingTransactionsID(String username);
    public PaymentTransaction getTransaction(Long id);
    public Boolean approveTransaction(Long id);
    public void rejectTransaction(Long id);
    public void createPayment (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash);
    public void requestPayment (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash);
//    public void createPayment (PaymentTransactionDTO paymentTransactionDTO);
//    public void requestPayment (PaymentTransactionDTO paymentTransactionDTO);
    public Boolean checkUserBalance(String username, Double payment);
}
