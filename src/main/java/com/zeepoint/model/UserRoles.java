package com.zeepoint.model;
// Generated Jun 6, 2015 10:03:31 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserRoles generated by hbm2java
 */
@Entity
@Table(name="user_roles"
    ,catalog="zipoints"
    , uniqueConstraints = @UniqueConstraint(columnNames={"ROLE", "user_id"}) 
)
public class UserRoles  implements java.io.Serializable {


     private Integer userRoleId;
     private Zipuser zipuser;
     private String role;

    public UserRoles() {
    }

    public UserRoles(Zipuser zipuser, String role) {
       this.zipuser = zipuser;
       this.role = role;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="user_role_id", unique=true, nullable=false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public Zipuser getZipuser() {
        return this.zipuser;
    }
    
    public void setZipuser(Zipuser zipuser) {
        this.zipuser = zipuser;
    }

    
    @Column(name="ROLE", nullable=false, length=45)
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }




}


