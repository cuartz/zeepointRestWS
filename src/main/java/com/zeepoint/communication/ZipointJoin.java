/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.communication;

import java.io.Serializable;

/**
 *
 * @author cuartz
 */
public class ZipointJoin implements Serializable{

  private Long channel;
  private Long userId;
  
  public ZipointJoin() {
    
  }
  
  public ZipointJoin(Long channel, Long userId) {
    this.channel=channel;
    this.userId=userId;
  }

    /**
     * @return the channel
     */
    public Long getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(Long channel) {
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
}