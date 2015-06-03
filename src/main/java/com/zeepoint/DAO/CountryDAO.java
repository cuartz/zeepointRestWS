/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Country;
import com.zeepoint.model.Room;
import java.util.List;

/**
 *
 * @author cuartz
 */
public interface CountryDAO extends DAO<Country, Long> { 
    
    public List<Country> findByName(String name);

}
