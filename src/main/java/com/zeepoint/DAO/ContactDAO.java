/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.City;
import com.zeepoint.model.Contact;
import com.zeepoint.model.Room;
import com.zeepoint.model.State;
import java.util.List;

/**
 *
 * @author cuartz
 */
public interface ContactDAO extends DAO<Contact, Long> { 
    
public Contact findContact(Long userId, Long contactId);
}
