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
public class PrivateOutputMessage implements Serializable{
    
  private String message;
  private Long id;
  private Long userId;
  private String userName;
  private Long fbId;
    
    private Integer messageType;
  
    public static final Integer TEXT_MESSAGE = 0;
        public static final Integer PHOTO_MESSAGE = 1;
  public PrivateOutputMessage() {
    
  }
  
  public PrivateOutputMessage(Long id, String message, Long userId, String userName, Long fbId, Integer messageType) {
    this.id = id;
    this.message = message;
    this.userId=userId;
    this.userName=userName;
    this.fbId=fbId;
    this.messageType=messageType;
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

    /**
     * @return the messageType
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }
}
