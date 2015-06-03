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
public class ZipointMessagesOUT  extends BaseOUT{

    private List<ZipointMessage> zMessages;

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
 
}