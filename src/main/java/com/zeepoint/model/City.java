package com.zeepoint.model;
// Generated Jun 29, 2015 2:10:42 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * City generated by hbm2java
 */
@Entity
@Table(name="city"
    ,catalog="zipoints"
)
public class City  implements java.io.Serializable {


     private Integer id;
     private State state;
     private String name;
     private Set<Zeepoint> zeepoints = new HashSet<Zeepoint>(0);

    public City() {
    }

	
    public City(State state, String name) {
        this.state = state;
        this.name = name;
    }
    public City(State state, String name, Set<Zeepoint> zeepoints) {
       this.state = state;
       this.name = name;
       this.zeepoints = zeepoints;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="state_id", nullable=false)
    public State getState() {
        return this.state;
    }
    
    public void setState(State state) {
        this.state = state;
    }

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="city")
    public Set<Zeepoint> getZeepoints() {
        return this.zeepoints;
    }
    
    public void setZeepoints(Set<Zeepoint> zeepoints) {
        this.zeepoints = zeepoints;
    }




}


