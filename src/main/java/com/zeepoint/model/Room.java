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
 * Room generated by hbm2java
 */
@Entity
@Table(name="room"
    ,catalog="zipoints"
    , uniqueConstraints = @UniqueConstraint(columnNames="userid") 
)
public class Room  implements java.io.Serializable {


     private Long id;
     private Zeepoint zeepoint;
     private Zipuser zipuser;
     private int status;

    public Room() {
    }

    public Room(Zeepoint zeepoint, Zipuser zipuser, int status) {
       this.zeepoint = zeepoint;
       this.zipuser = zipuser;
       this.status = status;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="zeepointId", nullable=false)
    public Zeepoint getZeepoint() {
        return this.zeepoint;
    }
    
    public void setZeepoint(Zeepoint zeepoint) {
        this.zeepoint = zeepoint;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid", unique=true, nullable=false)
    public Zipuser getZipuser() {
        return this.zipuser;
    }
    
    public void setZipuser(Zipuser zipuser) {
        this.zipuser = zipuser;
    }

    
    @Column(name="status", nullable=false)
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }




}


