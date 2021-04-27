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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    
    ExchangeRate exchangeRate;

    public PaymentService() {
    }

    public List<PaymentTransaction> getAllTransactions(){
        List<PaymentTransaction> transactions = em.createNamedQuery("findAllTransaction").getResultList();
        return transactions;
    }
    
    public List<PaymentTransaction> getUserTransactions(String username){
        String sql = "SELECT c FROM PaymentTransaction c WHERE c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"'";
        List<PaymentTransaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public List<PaymentTransaction> getPendingTransactions(String username){
        String sql = "SELECT c FROM PaymentTransaction c WHERE c.pending = 'true' AND ( c.sendUsername = '"+username+"' OR c.receiveUsername = '"+username+"')";
        List<PaymentTransaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public List<PaymentTransaction> getUserPendingTransactions(String username){
        String sql = "SELECT c FROM PaymentTransaction c WHERE c.pending = 'true' AND c.sendUsername = '"+username+"'";
        List<PaymentTransaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public List<PaymentTransaction> getUserPendingTransactionsID(String username){
        String sql = "SELECT c.id FROM PaymentTransaction c WHERE c.pending = 'true' AND c.sendUsername = '"+username+"'";
        List<PaymentTransaction> transactions = em.createQuery(sql).getResultList();
        return transactions;
    }
    
    public PaymentTransaction getTransaction(Long id){
        PaymentTransaction results = em.find(PaymentTransaction.class, id);
        return results;
    }
    
    public void approveTransaction(Long id){
        PaymentTransaction results = em.find(PaymentTransaction.class, id);
        results.setPending(false);
        results.setApproved(true);
        //deduct money
        //CHECK BALANCE       
        if (us.checkUserBalance(results.getSendUsername(), results.getSendCash())){
            us.deductBalance(results.getSendUsername(), results.getSendCash());
            us.addBalance(results.getReceiveUsername(), results.getReceiveCash());
            em.persist(results);
            em.flush();
        }else {
            FacesContext.getCurrentInstance().addMessage("paymentForm:paymentAmount", new FacesMessage("Error: You have insufficient fund!"));
        }
    }
    
    public void rejectTransaction(Long id){
        PaymentTransaction results = em.find(PaymentTransaction.class, id);
        results.setPending(false);
        results.setApproved(false);
        em.persist(results);
        em.flush();
    }
    
    private void createTransaction (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash, Boolean pending, Boolean approved){
        PaymentTransaction newTransaction = new PaymentTransaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, pending, approved);
        em.persist(newTransaction);
        em.flush();
    }
    
    public void createPayment (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash){
        createTransaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, false, true);
        us.deductBalance(sendUsername, sendCash);
        us.addBalance(receiveUsername, receiveCash);
    }
    
    public void requestPayment (String sendUsername, String sendCurrency, Double sendCash, Double exchangeRate, String receiveUsername, String receiveCurrency, Double receiveCash){
        createTransaction(sendUsername, sendCurrency, sendCash, exchangeRate, receiveUsername, receiveCurrency, receiveCash, true, false);
    }

}
