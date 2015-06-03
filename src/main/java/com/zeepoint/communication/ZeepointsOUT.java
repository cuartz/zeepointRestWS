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
public class ZeepointsOUT extends BaseOUT{
    
    private List<ZeepointOUT> zeePointsOut;

    /**
     * @return the zeePointsOut
     */
    public List<ZeepointOUT> getZeePointsOut() {
        return zeePointsOut;
    }

    /**
     * @param zeePointsOut the zeePointsOut to set
     */
    public void setZeePointsOut(List<ZeepointOUT> zeePointsOut) {
        this.zeePointsOut = zeePointsOut;
    }
    
}
