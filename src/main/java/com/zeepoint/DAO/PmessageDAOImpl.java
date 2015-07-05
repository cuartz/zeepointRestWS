/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Message;
import com.zeepoint.model.Pmessage;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class PmessageDAOImpl extends DAOImpl<Pmessage, Long>
        implements PmessageDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Pmessage> getLastMessages(Long fromUserId, Long toUserId) {
        Long user1;
        Long user2;
        if (fromUserId > toUserId) {
            user1 = toUserId;
            user2 = fromUserId;
        } else {
            user2 = toUserId;
            user1 = fromUserId;
        }
        return getSession().createCriteria(Message.class)
                //.add(Restrictions.eq("status", ZeePointGroupService.LISTENER_STATUS))
                .add(Restrictions.or(Restrictions.eq("user1", user1), Restrictions.eq("user2", user2)))
                .setMaxResults(30).addOrder(Order.desc("id")).list();
        //.setProjection(Projections.rowCount()).uniqueResult();  
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pmessage> getPreviousMessages(Long fromUserId, Long toUserId, Long lastMessageId) {
//        Long user1;
//        Long user2;
//        if (fromUserId > toUserId) {
//            user1 = toUserId;
//            user2 = fromUserId;
//        } else {
//            user2 = toUserId;
//            user1 = fromUserId;
//        }
        return getSession().createCriteria(Message.class)
                //.add(Restrictions.eq("status", ZeePointGroupService.LISTENER_STATUS))
                .add(Restrictions.and(Restrictions.eq("fromUserId", fromUserId), Restrictions.eq("toUserId", toUserId)))
                .add(Restrictions.lt("id", lastMessageId))
                .setMaxResults(30).addOrder(Order.desc("id")).list();
        //.setProjection(Projections.rowCount()).uniqueResult();  
    }

}
