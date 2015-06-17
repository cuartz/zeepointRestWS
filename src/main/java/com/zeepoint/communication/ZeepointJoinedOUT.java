/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.communication;

import java.util.List;

/**
 *
 * @author cuartz
 */
public class ZeepointJoinedOUT extends BaseOUT{
    
    private ZeepointOUT zeePointOut;
    
    private List<ZipointMessage> zMessages;
    
    private List<UserOUT> users;

    /**
     * @return the zeePointsOut
     */
    public ZeepointOUT getZeePointOut() {
        return zeePointOut;
    }

    /**
     * @param zeePointsOut the zeePointsOut to set
     */
    public void setZeePointOut(ZeepointOUT zeePointOut) {
        this.zeePointOut = zeePointOut;
    }

    /**
     * @return the zMessages
     */
    public List<ZipointMessage> getzMessages() {
        return zMessages;
    }

    /**
     * @param zMessages the zMessages to set
     */
    public void setzMessages(List<ZipointMessage> zMessages) {
        this.zMessages = zMessages;
    }

    /**
     * @return the users
     */
    public List<UserOUT> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserOUT> users) {
        this.users = users;
    }


    
}
