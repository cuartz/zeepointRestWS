/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.User;
import com.zeepoint.model.Zeepoint;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author cuartz
 */

public interface UserDAO extends DAO<User, Long> {  
        public List<User> findByFBID(Long fb_id);
        
        public List<User> getUsers(Long zeepointId);
}
