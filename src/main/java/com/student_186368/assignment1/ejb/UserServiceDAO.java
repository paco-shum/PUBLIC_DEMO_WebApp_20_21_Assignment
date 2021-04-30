/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.entity.SystemUser;
import com.student_186368.assignment1.entity.SystemUserGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 186368
 */
@Local
public interface UserServiceDAO {
    public void registerUser(String username, String userpassword, String name, String surname, String currency, Double balance);
    public void registerUser(String username, String userpassword, String name, String surname, String currency, Double balance, String userGroup);
    //public void registerUser(SystemUserDTO systemUserDTO);
    public void deleteUser(Long userID);
    public SystemUser getUser(String username);
    public List<SystemUser> getAllUsers();
    public List<SystemUserGroup> getAllUsersAtGroup();
    public List<SystemUser> getAllUsersID();
    public Long getUsersID(String username);
    public Boolean checkUserExist(String username);
    public Double getUserBalance(String username);
    public String getUserCurency(String username);
}
