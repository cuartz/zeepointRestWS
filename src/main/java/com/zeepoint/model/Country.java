package com.zeepoint.model;
// Generated Jun 21, 2015 7:01:22 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name="country"
    ,catalog="zipoints"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class Country  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<Zeepoint> zeepoints = new HashSet<Zeepoint>(0);
     private Set<State> states = new HashSet<State>(0);

    public Country() {
    }

	
    public Country(String name) {
        this.name = name;
    }
    public Country(String name, Set<Zeepoint> zeepoints, Set<State> states) {
       this.name = name;
       this.zeepoints = zeepoints;
       this.states = states;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name", unique=true, nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
    public Set<Zeepoint> getZeepoints() {
        return this.zeepoints;
    }
    
    public void setZeepoints(Set<Zeepoint> zeepoints) {
        this.zeepoints = zeepoints;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
    public Set<State> getStates() {
        return this.states;
    }
    
    public void setStates(Set<State> states) {
        this.states = states;
    }




}


