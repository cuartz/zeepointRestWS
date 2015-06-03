/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Zeepoint;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author cuartz
 */

public interface ZeepointDAO extends DAO<Zeepoint, Long> {  
    public List<Zeepoint> findByReference(String reference);
    
    public List<Zeepoint> getNearestZeePoints(BigDecimal lat, BigDecimal lon, Integer fromRow, Integer toRow);
    
    public List<Zeepoint> getNearestZeePointsWeb(BigDecimal lat, BigDecimal lon);
    
    public Zeepoint getZeePointInHere(BigDecimal lat, BigDecimal lon);
    
}
