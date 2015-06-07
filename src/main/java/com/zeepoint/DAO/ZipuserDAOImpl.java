/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Zipuser;
import com.zeepoint.service.ZeePointGroupService;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class ZipuserDAOImpl extends DAOImpl<Zipuser, Long>
        implements ZipuserDAO {

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    public List<Zipuser> findByFBID(Long fb_id) {
        Criterion crit = Restrictions.eq("fbId", fb_id);
        return findByCriteria(crit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Zipuser> getUsers(Long zeepointId) {
        Criteria criteria = getSession().createCriteria(Zipuser.class);
        criteria.createCriteria("rooms", "Room", JoinType.INNER_JOIN, Restrictions.and(
                Restrictions.eq("zeepoint.id", zeepointId), 
                Restrictions.eq("status", ZeePointGroupService.USER_STATUS)));
        return criteria.list();
    }   
    

}
