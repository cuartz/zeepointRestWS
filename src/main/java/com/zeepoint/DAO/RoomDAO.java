/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Room;

/**
 *
 * @author cuartz
 */
public interface RoomDAO extends DAO<Room, Long> { 
    
        public Long countListeners(Long zeepointId);
        
        public Long countUsers(Long zeepointId);

}
