/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Message;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class MessageDAOImpl extends DAOImpl<Message, Long>  
        implements MessageDAO {  
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Message> getLastMessages(Long zeepointId) {
        return getSession().createCriteria(Message.class)
                //.add(Restrictions.eq("status", ZeePointGroupService.LISTENER_STATUS))
                .add(Restrictions.eq("zeepoint.id", zeepointId))
                .setMaxResults(20).addOrder(Order.desc("id")).list();
                //.setProjection(Projections.rowCount()).uniqueResult();  
    }

    
        @SuppressWarnings("unchecked")
    @Override
    public List<Message> getPreviousMessages(Long zeepointId, Long lastMessageId) {
        return getSession().createCriteria(Message.class)
                //.add(Restrictions.eq("status", ZeePointGroupService.LISTENER_STATUS))
                .add(Restrictions.eq("zeepoint.id", zeepointId))
                .add(Restrictions.lt("id", lastMessageId))
                .setMaxResults(20).addOrder(Order.desc("id")).list();
                //.setProjection(Projections.rowCount()).uniqueResult();  
    }
    
}
