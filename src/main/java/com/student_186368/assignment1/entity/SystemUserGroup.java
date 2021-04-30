package com.student_186368.assignment1.entity;

//import com.student_186368.assignment1.dto.SystemUserDTO;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author 186368
 */
@NamedQuery(name="findAllUserGroup", query="SELECT c FROM SystemUserGroup c ")
@Entity
public class SystemUserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;
    private String groupname;

    public SystemUserGroup() {
    }

    public SystemUserGroup(String username, String groupName) {
        this.username = username;
        this.groupname = groupName;
    }

//    // Method to get Transfer Object for Project data
//    public SystemUserDTO getSystemUserGPDTO() {
//      return createSystemUserDTO();
//    }
//     
//    // method to create a new Transfer Object and 
//    // copy data from entity bean into the value 
//    // object
//    private SystemUserDTO createSystemUserDTO(){
//        SystemUserDTO dto = new SystemUserDTO();
//        dto.id = id;
//        dto.username = username;
//        dto.groupname = groupname;
//        return dto;
//    }
//    
//    // method to set entity values with a Transfer Object
//    public void setSystemUserGPDTO(SystemUserDTO updatedDTO) {
//        mergeSystemUserDTO(updatedDTO);
//    }
//
//    // method to merge values from the Transfer Object into
//    // the entity bean attributes
//    private void mergeSystemUserDTO(SystemUserDTO updatedDTO) {
//        this.id = updatedDTO.id;
//        this.username = updatedDTO.username;
//        this.groupname = updatedDTO.groupname;
//    }
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

    public String getGroupName() {
        return groupname;
    }

    public void setGroupName(String groupName) {
        this.groupname = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.groupname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserGroup other = (SystemUserGroup) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.groupname, other.groupname)) {
            return false;
        }
        return true;
    }

}
