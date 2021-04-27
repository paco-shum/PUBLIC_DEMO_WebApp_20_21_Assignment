/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.SystemUser;
import com.student_186368.assignment1.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 186368
 */
/*This EJB will be instantiated only once when the applciation is deployed - @Startup and @Singleton respectively*/
@Startup
@Singleton
public class InitSingleton {
    
    @PersistenceContext//(unitName = "PaymentUserPU")
    EntityManager em;

    @PostConstruct
    public void adminInit() {
        System.out.println("At startup: checking if an admin account is in the datbase");
        if (!checkUserExist("paymentadmin")){
            System.out.println("Missing admin account...create one now");
            register("admin1", "admin1", "admin", "admin", "admin");
        }else {
            System.out.println("Admin account detected...skipping");
        }
        
        System.out.println("At startup: checking if an user1 account is in the datbase");
        if (!checkUserExist("user1")){
            System.out.println("Missing user1 account...create one now");
            register("user1", "p1", "Tom", "Addnerson", "users");
        }else {
            System.out.println("user1 account detected...skipping");
        }
        System.out.println("At startup: checking if an user2 account is in the datbase");
        if (!checkUserExist("user2")){
            System.out.println("Missing user2 account...create one now");
            register("user2", "p2", "Jesi", "Reynolds", "users");
        }else {
            System.out.println("user2 account detected...skipping");
        }
    }
    
    private void register(String username, String userpassword, String name, String surname, String perm) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);

            // apart from the default constructor which is required by JPA
            // you need to also implement a constructor that will make the following code succeed
            sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, "GBP", 1000d);
            sys_user_group = new SystemUserGroup(username, perm);

            em.persist(sys_user);
            em.persist(sys_user_group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error upon creation of account");
        }
    }
    
    private Boolean checkUserExist(String username){
        try {
            String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
            SystemUser systemUser = (SystemUser) em.createQuery(sql).getSingleResult();
            return true;
        } catch (NoResultException ex) {
            //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
