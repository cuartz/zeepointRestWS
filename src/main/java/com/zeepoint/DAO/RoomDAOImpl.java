/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Room;
import com.zeepoint.service.ZeePointGroupService;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class RoomDAOImpl extends DAOImpl<Room, Long>
        implements RoomDAO {

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param zeepointId
     * @param reference
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Long countUsers(Long zeepointId) {
        return (Long) getSession().createCriteria(Room.class)
                .add(Restrictions.eq("status", ZeePointGroupService.USER_STATUS))
                .add(Restrictions.eq("zeepoint.id", zeepointId))
                .setProjection(Projections.rowCount()).uniqueResult();  
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Long countListeners(Long zeepointId) {
        return (Long) getSession().createCriteria(Room.class)
                .add(Restrictions.eq("status", ZeePointGroupService.LISTENER_STATUS))
                .add(Restrictions.eq("zeepoint.id", zeepointId))
                .setProjection(Projections.rowCount()).uniqueResult();  
    }

}
