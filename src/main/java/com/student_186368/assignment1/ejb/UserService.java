package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.SystemUser;
import com.student_186368.assignment1.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author parisis
 */
@Stateless
public class UserService {

    @PersistenceContext//(unitName = "PaymentUserPU")
    EntityManager em;

    public UserService() {
    }

    public void registerUser(String username, String userpassword, String name, String surname, String currency, Double balance) {
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
            sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, currency, balance);
            sys_user_group = new SystemUserGroup(username, "users");

            em.persist(sys_user);
            em.persist(sys_user_group);
            em.flush();
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerUser(String username, String userpassword, String name, String surname, String currency, Double balance, String userGroup) {
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
            sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, currency, balance);
            sys_user_group = new SystemUserGroup(username, userGroup);

            em.persist(sys_user);
            em.persist(sys_user_group);
            em.flush();
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUser(Long userID){
        SystemUser user = em.find(SystemUser.class, userID);
        em.remove(user);
        em.flush();
    }
    
    ///should replace with miltple getter.
    public SystemUser getUser(String username) {
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        //delete password hash for security purpose
        SystemUser newResults = new SystemUser(results.getUsername(), "XXXXX", results.getName(), results.getSurname(), results.getCurrency(), results.getBalance());
        return newResults;
    }

    public List<SystemUser> getAllUsers(){
        List<SystemUser> users = em.createNamedQuery("findAllUser").getResultList();
        List<SystemUser> newList = new ArrayList<SystemUser>();;
        for (SystemUser u: users){
            newList.add(u);
        }
        return newList;
    }
    
    public List<SystemUserGroup> getAllUsersAtGroup(){
        return em.createNamedQuery("findAllUserGroup").getResultList();
    }
    
    public List<SystemUser> getAllUsersID(){
        String sql = "SELECT c.id FROM SystemUser c";
        List<SystemUser> users = em.createQuery(sql).getResultList();
        return users;
    }
    public Long getUsersID(String username){
        String sql = "SELECT c.id FROM SystemUser c WHERE c.username = '"+username+"'";
        Long usr_id = (Long) em.createQuery(sql).getSingleResult();
        return usr_id;
    }
    
    public Boolean checkUserExist(String username){
        try {
            String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
            SystemUser systemUser = (SystemUser) em.createQuery(sql).getSingleResult();
            return true;
        } catch (NoResultException ex) {
            //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Double getUserBalance(String username){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        return results.getBalance();
    }
    
    public Boolean checkUserBalance(String username, Double payment){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        BigDecimal bd1 = new BigDecimal(results.getBalance());
        int res = bd1.compareTo(new BigDecimal(payment));
        switch (res) {
            case 0:
                return true;
            case 1:
                return true;
            case -1:
                return false;
            default:
                return false;
        }
    }
    
    public String getUserCurency(String username){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        return results.getCurrency();
    }
    public Boolean checkUserCurency(String username, String currency){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        return (results.getCurrency().equals(currency));
    }
    
    public void addBalance(String username, Double cash){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser su = (SystemUser) em.createQuery(sql).getSingleResult();
        BigDecimal bd1 = new BigDecimal(su.getBalance());
        Double newBalance = bd1.add(new BigDecimal(cash)).doubleValue();
        su.setbalance(newBalance);
        em.persist(su);
        em.flush();
    }
    
    public void deductBalance(String username, Double cash){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser su = (SystemUser) em.createQuery(sql).getSingleResult();
        BigDecimal bd1 = new BigDecimal(su.getBalance());
        Double newBalance = bd1.subtract(new BigDecimal(cash)).doubleValue();
        su.setbalance(newBalance);
        em.persist(su);
        em.flush();
    }
    
    
}
