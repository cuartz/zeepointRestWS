package com.zeepoint.model;
// Generated Jun 7, 2015 6:25:26 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * State generated by hbm2java
 */
@Entity
@Table(name="state"
    ,catalog="zipoints"
)
public class State  implements java.io.Serializable {


     private int id;
     private Country country;
     private String name;
     private Set<City> cities = new HashSet<City>(0);
     private Set<Zeepoint> zeepoints = new HashSet<Zeepoint>(0);

    public State() {
    }

	
    public State(int id, Country country, String name) {
        this.id = id;
        this.country = country;
        this.name = name;
    }
    public State(int id, Country country, String name, Set<City> cities, Set<Zeepoint> zeepoints) {
       this.id = id;
       this.country = country;
       this.name = name;
       this.cities = cities;
       this.zeepoints = zeepoints;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=false)
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="state")
    public Set<City> getCities() {
        return this.cities;
    }
    
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="state")
    public Set<Zeepoint> getZeepoints() {
        return this.zeepoints;
    }
    
    public void setZeepoints(Set<Zeepoint> zeepoints) {
        this.zeepoints = zeepoints;
    }




}

