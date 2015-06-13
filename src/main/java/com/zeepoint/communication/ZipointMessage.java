/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.communication;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cuartz
 */
public class ZipointMessage  extends OutputMessage implements Serializable{

    private Long messageId;
    private Date time;
    
    public ZipointMessage(OutputMessage original, Date time) {
        //int id, String message, Long channel, Long userId
        super(original.getId(), original.getMessage(), original.getChannel(), original.getUserId(), original.getUserName(), original.getFbId(), original.getMessageType());
        this.time = time;
    }

    public ZipointMessage() {
        super();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    public OutputMessage(ZipointMessage original, Date time) {
//        super(original.getId(), original.getMessage(), original.getChannel(), original.getUserId());
//        this.time = time;
//    }
    
    public Date getTime() {
        return time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the messageId
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

 
}