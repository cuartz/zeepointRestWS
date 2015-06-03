/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import com.util.ZipointsBLException;
import com.zeepoint.communication.UserOUT;
import com.zeepoint.communication.ZeepointJoinedOUT;
import com.zeepoint.communication.ZeepointOUT;
import com.zeepoint.communication.ZipointMessagesOUT;
import com.zeepoint.model.Zeepoint;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author cuartz
 */
public interface IZeePointGroupService {
    public ZeepointOUT createZpoint(BigDecimal lat, BigDecimal lon, String name, Long fb_id, String country, String state, String city) throws ZipointsBLException;

    public List<ZeepointOUT> getAllZpoints(BigDecimal lat, BigDecimal lon, Long userId, Integer fromRow, Integer toRow);
    
    public ZeepointJoinedOUT joinZpoint(Long id, Long userId, BigDecimal lat, BigDecimal lon);
    
    public ZipointMessagesOUT getPreviousMessages(Long id, Long lastMessageId);
    
    public List<UserOUT> getUsers(Long ziPointId);
}
