/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.communication;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cuartz
 */
public class ZipointPrivateMessagesOUT  extends BaseOUT{

    private List<ZipointPrivateMessage> zMessages;

    /**
     * @return the zMessages
     */
    public List<ZipointPrivateMessage> getzMessages() {
        return zMessages;
    }

    /**
     * @param zMessages the zMessages to set
     */
    public void setzMessages(List<ZipointPrivateMessage> zMessages) {
        this.zMessages = zMessages;
    }
 
}