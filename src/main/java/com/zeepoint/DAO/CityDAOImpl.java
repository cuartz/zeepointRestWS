/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.City;
import com.zeepoint.model.State;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class CityDAOImpl extends DAOImpl<City, Long>  
        implements CityDAO {  

    @Override
    public City findByName(State state, String cityName) {
        return (City) getSession().createCriteria(City.class)
                .add(Restrictions.eq("state", state))
                .add(Restrictions.eq("name", cityName)).uniqueResult(); 
    }

     
}
