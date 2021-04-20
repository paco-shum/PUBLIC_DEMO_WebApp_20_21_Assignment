/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

/**
 *
 * @author PacoShum
 */
@Stateless
@TransactionAttribute
public class PaymentExecutor {
//    @PersistenceContext(unitName = "TransactionExercisePU")
//    EntityManager em;
//    @EJB
//    TransactionEJB tb;
//
//    public String execute(List<Operation> operations) {
//        try{
//        String s = "";
//        for (Operation o : operations) {
//            s += o.execute(tb) + "\n";
//        }
//        return s;
//        } catch (EJBTransactionRolledbackException trb) {
//            return "EJBTransactionRolledbackException Was Caught and Transaction was Rolled Back..In Real life I should have done something here for the user (e.g. cancel a payment or reset and tell user to try again).!!!!";
//        }
//    }
}
