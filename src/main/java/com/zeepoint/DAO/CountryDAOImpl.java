/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Country;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class CountryDAOImpl extends DAOImpl<Country, Long>  
        implements CountryDAO {  

        /** 
     * Use this inside subclasses as a convenience method. 
     */  
    @SuppressWarnings("unchecked")  
    public List<Country> findByName(String name) {  
        Criterion crit = Restrictions.eq("name", name); 
        return findByCriteria(crit);
   }  
}
