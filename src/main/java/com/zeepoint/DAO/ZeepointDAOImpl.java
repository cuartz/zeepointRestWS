/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.DAO;

import com.zeepoint.model.Zeepoint;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuartz
 */
@Component
public class ZeepointDAOImpl extends DAOImpl<Zeepoint, Long>
        implements ZeepointDAO {

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param reference
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Zeepoint> findByReference(String reference) {
        Criterion crit = Restrictions.eq("referenceId", reference);
        return findByCriteria(crit);
    }

//    @SuppressWarnings("unchecked")
//    public List<Zeepoint> findZeePointsOnDistance(Long lat, Long lon) {
//        double earthRadius = 6371000; //meters
//        int radius = 100;
//        double maxLat = lat + rad2deg(radius / earthRadius);
//        double minLat = lat - rad2deg(radius / earthRadius);
//
////$minLat = $lat - rad2deg($rad/earthRadius);
//// compensate for degrees longitude getting smaller with increasing latitude
//        double maxLon = lon + rad2deg(radius / earthRadius / Math.cos(deg2rad(lat)));
//        double minLon = lon - rad2deg(radius / earthRadius / Math.cos(deg2rad(lat)));
//
//        //Criterion critFb = Restrictions.eq("fbId", fb_id); 
//        Criterion critLat = Restrictions.between("lat", minLat, maxLat);
//        Criterion critLon = Restrictions.between("lon", minLon, maxLon);
//        return findByCriteria(critLat, critLon);
//    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Zeepoint> getNearestZeePoints(BigDecimal lat, BigDecimal lon, Integer fromRow, Integer toRow) {
        Query query = getSession().createSQLQuery(
                "CALL get_zpoints(:latitude,:longitude,:fromRow,:toRow)")
                .addEntity(Zeepoint.class)
                .setParameter("latitude", lat)
                .setParameter("longitude", lon)
                .setParameter("fromRow", fromRow)
                .setParameter("toRow", toRow);

        List<Zeepoint> result = query.list();
        return result;
    }
    
        @SuppressWarnings("unchecked")
    @Override
    public List<Zeepoint> getNearestZeePointsWeb(BigDecimal lat, BigDecimal lon) {
        Query query = getSession().createSQLQuery(
                "CALL get_zpoints_web(:latitude,:longitude)")
                .addEntity(Zeepoint.class)
                .setParameter("latitude", lat)
                .setParameter("longitude", lon);

        List<Zeepoint> result = query.list();
        return result;
    }

    @Override
    public Zeepoint getZeePointInHere(BigDecimal lat, BigDecimal lon) {
                Query query = getSession().createSQLQuery(
                "CALL get_zpoint(:latitude,:longitude)")
                .addEntity(Zeepoint.class)
                .setParameter("latitude", lat)
                .setParameter("longitude", lon);

        Zeepoint result = (Zeepoint) query.uniqueResult();
        return result;
    }
}
