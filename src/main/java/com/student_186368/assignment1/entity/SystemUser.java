package com.student_186368.assignment1.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author parisis
 */
@NamedQuery(name="findAllUser", query="SELECT c FROM SystemUser c ")
@Entity
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // here on could use Bean Validation annotations to enforce specific rules - this could be alternatively implemented when validating the form in the web tier
    // for now we check only for Null values
    @NotNull
    String username;

    // here on could use Bean Validation annotations to enforce specific rules - this could be alternatively implemented when validating the form in the web tier
    // for now we check only for Null values
    @NotNull
    String userpassword;

    @NotNull
    String name;

    @NotNull
    String surname;
    
    @NotNull
    String currency;
    
    @NotNull
    Float balance;

    public SystemUser() {
    }

//    public SystemUser(String username, String userpassword, String name, String surname) {
//        this.username = username;
//        this.userpassword = userpassword;
//        this.name = name;
//        this.surname = surname;
//    }

    public SystemUser(String username, String userpassword, String name, String surname, String currency, Float balance) {
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.surname = surname;
        this.currency = currency;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String Currency) {
        this.currency = Currency;
    }

    public Float getBalance() {
        return balance;
    }

    public void setbalance(Float Cash) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.userpassword);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.currency);
        hash = 97 * hash + Objects.hashCode(this.balance);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.userpassword, other.userpassword)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.balance, other.balance)) {
            return false;
        }
        return true;
    }

    

}
