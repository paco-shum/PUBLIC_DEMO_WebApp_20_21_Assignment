/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.SystemUser;
import com.student_186368.assignment1.entity.Transaction;
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
    @PersistenceContext//(unitName = "PaymentTransactionPU")
    EntityManager em;
    @EJB
    UserService us;

    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions = em.createNamedQuery("findAllTransaction").getResultList();
        return transactions;
    }
    
    public List<Transaction> getUserTransactions(String username){
        String sql = "SELECT c FROM Transaction c WHERE c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"'";
        List<Transaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public List<Transaction> getPendingTransactions(String username){
        String sql = "SELECT c FROM Transaction c WHERE c.pending = 'true' AND ( c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"')";
        List<Transaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public Transaction getTransaction(Long id){
        Transaction results = em.find(Transaction.class, id);
//        String sql = "SELECT c FROM Transaction c WHERE c.id = "+id;
//        Transaction results = (Transaction) em.createQuery(sql).getSingleResult();
        return results;
    }
    
    public void approveTransaction(Long id){
        Transaction results = em.find(Transaction.class, id);
        results.setPending(false);
        results.setApproved(true);
        em.persist(results);
        em.flush();
    }
    
    public void rejectTransaction(Long id){
        Transaction results = em.find(Transaction.class, id);
        results.setPending(false);
        results.setApproved(false);
        em.persist(results);
        em.flush();
    }
    
    public void creatTransaction (String sendUsername, String sendCurrency, Float sendCash, Float exchangeRate, String receiveUsername, String receiveCurrency, Float receiveCash, Boolean pending, Boolean approved){
        Transaction newTransaction = new Transaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, pending, approved);
        em.persist(newTransaction);
        em.flush();
    }
    
    public void creatPayment (){
        
    }
    
    public void requestPayment (){
        
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
