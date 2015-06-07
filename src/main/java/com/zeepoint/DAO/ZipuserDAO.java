/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Zipuser;
import java.util.List;

/**
 *
 * @author cuartz
 */

public interface ZipuserDAO extends DAO<Zipuser, Long> {  
        public List<Zipuser> findByFBID(Long fb_id);
        
        public List<Zipuser> getUsers(Long zeepointId);
}
