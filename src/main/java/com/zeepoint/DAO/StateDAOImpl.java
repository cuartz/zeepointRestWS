/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Country;
import com.zeepoint.model.State;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class StateDAOImpl extends DAOImpl<State, Long>  
        implements StateDAO {  

    @Override
    public State findByName(Country country, String stateName) {
        return (State) getSession().createCriteria(State.class)
                .add(Restrictions.eq("country", country))
                .add(Restrictions.eq("name", stateName)).uniqueResult();  
    }

        
}
