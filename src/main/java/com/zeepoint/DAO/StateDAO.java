/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Country;
import com.zeepoint.model.Room;
import com.zeepoint.model.State;
import java.util.List;

/**
 *
 * @author cuartz
 */
public interface StateDAO extends DAO<State, Long> { 

    public State findByName(Country country, String stateName);
    

}
