/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Message;
import com.zeepoint.model.User;
import com.zeepoint.model.Zeepoint;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author cuartz
 */

public interface MessageDAO extends DAO<Message, Long> {
    
    public List<Message> getLastMessages(Long zeepointId);
    
    public List<Message> getPreviousMessages(Long zeepointId, Long lastMessageId);
            
}
