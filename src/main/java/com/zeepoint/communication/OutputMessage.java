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
public class OutputMessage implements Serializable{
    
      private String message;
  private Long id;
  private String channel;
  private Long userId;
      private String userName;
    private Long fbId;
  
  public OutputMessage() {
    
  }
  
  public OutputMessage(Long id, String message, String channel, Long userId) {
    this.id = id;
    this.message = message;
    this.channel=channel;
    this.userId=userId;
  }

  public String getMessage() {
    return message;
  }
    public void setMessage(String message) {
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
       /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the fbId
     */
    public Long getFbId() {
        return fbId;
    }

    /**
     * @param fbId the fbId to set
     */
    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }
}
