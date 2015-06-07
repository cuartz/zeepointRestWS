/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Contact;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class ContactDAOImpl extends DAOImpl<Contact, Long>  
        implements ContactDAO {  

        @Override
    public Contact findContact(Long userId, Long contactId) {
        return (Contact) getSession().createCriteria(Contact.class)
                .add(Restrictions.eq("user.id", userId))
                .add(Restrictions.eq("contactId.id", contactId)).uniqueResult(); 
    }
     
}
