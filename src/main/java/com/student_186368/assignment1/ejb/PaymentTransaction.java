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
public class PaymentTransaction {
//    @PersistenceContext(unitName = "TransactionExercisePU")
//    EntityManager em;
//
//    public int read(long id) {
//        ExerciseEntity e = em.find(ExerciseEntity.class, id);
//        return e.getRecordValue();
//    }
//
//    public void write(long id, int value) {
//        ExerciseEntity e = em.find(ExerciseEntity.class, id);
//        e.setRecordValue(value);
//        em.persist(e);
//        em.flush();
//    }
}
