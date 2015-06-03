/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.controller.web;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cuartz
 */
public    class Todo {
        
        public Todo(){}
        public Todo(Integer id){
            this.id=id;
        }
        
        public Todo(Integer id, String description, Date date){
            this.id=id;
            this.description=description;
            this.createdOn=date;
        }
	//@Id //@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String description;
	//@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn = new Date();
        
        @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId().intValue();
        //result = prime * result + ((referenceId == null) ? 0 : referenceId.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Todo))
            return false;
        Todo other = (Todo) obj;
        if (getId() != other.getId())
            return false;
    
        return true;
    }
	
	//setters and getters

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
