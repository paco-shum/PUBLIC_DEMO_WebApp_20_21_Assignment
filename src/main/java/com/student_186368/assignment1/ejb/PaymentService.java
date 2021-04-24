/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.PaymentTransaction;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PacoShum
 */
@Stateless
@TransactionAttribute
public class PaymentService {
    @PersistenceContext(unitName = "PaymentTransactionPU")
    EntityManager em;
    @EJB
    UserService us;

    public PaymentService() {
    }

    public List<PaymentTransaction> getAllTransactions(){
        List<PaymentTransaction> transactions = em.createNamedQuery("findAllTransaction").getResultList();
        //String sql = "SELECT c FROM Transaction c WHERE c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"'";
        //List<Transaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public List<PaymentTransaction> getUserTransactions(String username){
        String sql = "SELECT c FROM Transaction c WHERE c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"'";
        List<PaymentTransaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public List<PaymentTransaction> getPendingTransactions(String username){
        String sql = "SELECT c FROM Transaction c WHERE c.pending = 'true' AND ( c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"')";
        List<PaymentTransaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public PaymentTransaction getTransaction(Long id){
        PaymentTransaction results = em.find(PaymentTransaction.class, id);
//        String sql = "SELECT c FROM Transaction c WHERE c.id = "+id;
//        Transaction results = (Transaction) em.createQuery(sql).getSingleResult();
        return results;
    }
    
    public void approveTransaction(Long id){
        PaymentTransaction results = em.find(PaymentTransaction.class, id);
        results.setPending(false);
        results.setApproved(true);
        //deduct money
        //WONG METHOD CALLED
        us.deductBalance(results.getSendUsername(), results.getSendCash());
        us.addBalance(results.getReceiveUsername(), results.getReceiveCash());
        em.persist(results);
        em.flush();
    }
    
    public void rejectTransaction(Long id){
        PaymentTransaction results = em.find(PaymentTransaction.class, id);
        results.setPending(false);
        results.setApproved(false);
        em.persist(results);
        em.flush();
    }
    
    public void createTransaction (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash, Boolean pending, Boolean approved){
        PaymentTransaction newTransaction = new PaymentTransaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, pending, approved);
        em.persist(newTransaction);
        em.flush();
    }
    
    public void createPayment (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash, Boolean approved){
        createTransaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, false, approved);
        us.deductBalance(sendUsername, sendCash);
        us.addBalance(receiveUsername, receiveCash);
    }
    
    public void requestPayment (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash, Boolean approved){
        createTransaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, true, approved);
//        us.updateBalance(sendUsername, sendCash);
//        us.updateBalance(receiveUsername, receiveCash);
    }
//    public boolean prePaymentBalanceCheck(String sendUsername, String receiveUsername, Float payment){
//        return (us.checkUserBalance(sendUsername) >= payment);
//    }
    
    

//    public int read(long id) {
//        Transaction e = em.find(Transaction.class, id);
//        return e.getRecordValue();
//    }
//
//    public void writeToBalance(long id, int value) {
//        Transaction e = em.find(Transaction.class, id);
//        e.setRecordValue(value);
//        em.persist(e);
//        em.flush();
//    }
}
