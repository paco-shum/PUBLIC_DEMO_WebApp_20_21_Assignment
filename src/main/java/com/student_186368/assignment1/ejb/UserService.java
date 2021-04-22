package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.SystemUser;
import com.student_186368.assignment1.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author parisis
 */
@Stateless
public class UserService {

    @PersistenceContext(unitName = "PaymentUserPU")
    EntityManager em;

    public UserService() {
    }

    public void registerUser(String username, String userpassword, String name, String surname, String currency, Float balance) {
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
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ///should replace with miltple getter.
    public SystemUser getUser(String username) {
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        return results;
    }

    public Boolean checkUserExist(String username){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        List<SystemUser> systemUser = em.createQuery(sql).getResultList();
        return !systemUser.isEmpty();
    }
    
    public Float checkUserBalance(String username){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        return results.getBalance();
    }
    
    public Boolean checkUserBalance(String username, Float payment){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser results = (SystemUser) em.createQuery(sql).getSingleResult();
        return (results.getBalance() >= payment);
    }
    
    public void updateBalance(String username, Float cash){
        String sql = "SELECT c FROM SystemUser c WHERE c.username = '"+username+"'";
        SystemUser su = (SystemUser) em.createQuery(sql).getSingleResult();
        su.setbalance(cash);
        em.persist(su);
        em.flush();
//        SystemUser editedSU = em.find(SystemUser.class, su.getId());
//        em.getTransaction().begin();
//        editedSU.setbalance(cash);
//        em.getTransaction().commit();
    }
}
