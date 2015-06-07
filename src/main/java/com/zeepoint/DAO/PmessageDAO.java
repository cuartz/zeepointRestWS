/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Pmessage;
import java.util.List;

/**
 *
 * @author cuartz
 */

public interface PmessageDAO extends DAO<Pmessage, Long> {
    
       public List<Pmessage> getLastMessages(Long fromUserId,Long toUserId);

    public List<Pmessage> getPreviousMessages(Long fromUserId,Long toUserId, Long lastMessageId);
            
}
