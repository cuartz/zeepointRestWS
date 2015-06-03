/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import static com.util.Reversed.reversed;
import com.util.ZipointsBLException;
import com.zeepoint.DAO.CityDAO;
import com.zeepoint.DAO.CountryDAO;
import com.zeepoint.communication.ZipointMessage;
import com.zeepoint.DAO.MessageDAO;
import com.zeepoint.DAO.RoomDAO;
import com.zeepoint.DAO.StateDAO;
import com.zeepoint.DAO.UserDAO;
import com.zeepoint.DAO.ZeepointDAO;
import com.zeepoint.communication.UserOUT;
import com.zeepoint.communication.ZeepointJoinedOUT;
import com.zeepoint.communication.ZeepointOUT;
import com.zeepoint.communication.ZipointMessagesOUT;
import com.zeepoint.model.City;
import com.zeepoint.model.Country;
import com.zeepoint.model.Message;
import com.zeepoint.model.Room;
import com.zeepoint.model.State;
import com.zeepoint.model.User;
import com.zeepoint.model.Zeepoint;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cuartz
 */
@Component
public class ZeePointGroupService implements IZeePointGroupService {

    public static final Integer USER_STATUS = 1;
    public static final Integer LISTENER_STATUS = 2;
    private static final Matcher matcher = Pattern.compile(
            "^\\("
            //Latitude
            + "[-+]?(?:90(?:\\.0+)?|[1-8]?[0-9](?:\\.[0-9]+)?)"
            + ","
            //Longitude
            + "[-+]?(?:180(?:\\.0+)?|(?:(?:[0-9]?[0-9]|1[0-7][0-9])(?:\\.[0-9]+)?))"
            + "\\)$"
    ).matcher("");

    @Autowired
    private ZeepointDAO zeepointDao;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private RoomDAO roomDao;
    @Autowired
    private CountryDAO countryDao;
    @Autowired
    private StateDAO stateDao;
    @Autowired
    private CityDAO cityDao;
    @Autowired
    private MessageDAO messageDao;

    /**
     *
     * @param lat
     * @param lon
     * @param name
     * @param fb_id
     * @param country
     * @param state
     * @param city
     * @return
     * @throws ZipointsBLException
     */
    @Override
    @Transactional
    public ZeepointOUT createZpoint(BigDecimal lat, BigDecimal lon, String name, Long fb_id, String countryName, String stateName, String cityName) throws ZipointsBLException {

//        boolean validCoordinates = false;
//        
//        matcher.reset("+"+lat.toString() + ", " + lon.toString());
//        validCoordinates = matcher.matches();
//        
//        if (!validCoordinates) {
//            throw new ZipointsBLException("The coordinates you provide are invalid!");
//        }
        Zeepoint zp = zeepointDao.getZeePointInHere(lat, lon);

        if (zp != null) {
            throw new ZipointsBLException("There is a point already created here!");
        }

        List<Country> countries = countryDao.findByName(countryName);
        Country country = null;
        if (countries != null && !countries.isEmpty()) {
            country = countries.get(0);
        } else {
            country = new Country();
            country.setName(countryName);
            countryDao.makePersistent(country);
        }

        //List<State> states = stateDao.findByName(country,stateName);
        State state = stateDao.findByName(country, stateName);
        if (state != null) {// && !state.isEmpty()) {
            //state=states.get(0);
        } else {
            state = new State();
            state.setCountry(country);
            state.setName(stateName);
            stateDao.makePersistent(state);
        }

        //List<City> cities = cityDao.findByName(state,cityName);
        City city = cityDao.findByName(state, cityName);
        if (city != null) {// && !cities.isEmpty()) {
            //city=cities.get(0);
        } else {
            city = new City();
            city.setState(state);
            city.setName(cityName);
            cityDao.makePersistent(city);
        }

        Zeepoint zeepoint = new Zeepoint();
        zeepoint.setLatitud(lat);
        zeepoint.setLongitud(lon);
        zeepoint.setName(name);
        zeepoint.setCity(city);
        zeepoint.setCountry(country);
        zeepoint.setState(state);
        List<User> users = userDao.findByFBID(fb_id);
        if (!users.isEmpty()) {
            zeepoint.setUser(users.get(0));
        }
        Random ran = new Random();
        int random = ran.nextInt();
        while (!zeepointDao.findByReference("zp" + random).isEmpty()) {
            random = ran.nextInt();
        }
        zeepoint.setReferenceId("zp" + random);
        zp = zeepointDao.makePersistent(zeepoint);

        ZeepointOUT zpOUT = new ZeepointOUT();
        zpOUT.setJoined(true);
        zpOUT.setDistance(zp.getDistance());
        zpOUT.setLatitud(zp.getLatitud());
        zpOUT.setLongitud(zp.getLongitud());
        zpOUT.setName(zp.getName());
        zpOUT.setReferenceId(zp.getReferenceId());
        zpOUT.setUsers(zp.getRooms().size());
        return zpOUT;
    }

    @Override
    @Transactional
    public List<ZeepointOUT> getAllZpoints(BigDecimal lat, BigDecimal lon, Long userId, Integer fromRow, Integer toRow) {
        List<ZeepointOUT> zpsOUT = new ArrayList<>();
        //Rooms room=roomsDao.findByUserId(userId);
        //Long joinedZpoint = null;
        if (userId != null) {
            Room room = null;
            User user = userDao.findById(userId, false);
            if (!user.getRooms().isEmpty()) {
                room = user.getRooms().iterator().next();
            }
            if (room != null) {
                //joinedZpoint = room.getZeepoint().getId();

                ZeepointOUT zpOUT = new ZeepointOUT();
                Zeepoint zp = room.getZeepoint();
                Double distance = distance(zp.getLatitud().doubleValue(), zp.getLongitud().doubleValue(), lat.doubleValue(), lon.doubleValue(), 'M');
                if (Objects.equals(room.getStatus(), USER_STATUS)) {
                    if (!isInRange(distance)) {
                        room.setStatus(LISTENER_STATUS);
                        roomDao.makePersistent(room);
                        zpOUT.setDistance(101);
                    }
                }
                zpOUT.setId(zp.getId());
//                if (zp.getDistance() == null) {
//                    zp.setDistance(0);
//                    //System.err.println("Unreacheable point :" + zp.getId() + " from lat,lon:" + lat + "," + lon);
//                } else {
                // if (zp.getId().equals(joinedZpoint)) {
                zpOUT.setJoined(true);
                //}
                zpOUT.setDistance(distance.intValue());
                zpOUT.setLatitud(zp.getLatitud());
                zpOUT.setLongitud(zp.getLongitud());
                zpOUT.setName(zp.getName());
                zpOUT.setReferenceId(zp.getReferenceId());
                zpOUT.setUsers(roomDao.countUsers(zp.getId()).intValue());//zp.getRooms().size());
                zpOUT.setListeners(roomDao.countListeners(zp.getId()).intValue());
                zpsOUT.add(zpOUT);
//                }
            }
        }
        for (Zeepoint zp : zeepointDao.getNearestZeePoints(lat, lon, fromRow, toRow)) {
            ZeepointOUT zpOUT = new ZeepointOUT();
            zpOUT.setId(zp.getId());
            if (zp.getDistance() == null) {
                zp.setDistance(0);
                //System.err.println("Unreacheable point :" + zp.getId() + " from lat,lon:" + lat + "," + lon);
            }
            //           else {
//                if (zp.getId().equals(joinedZpoint)) {
//                    zpOUT.setJoined(true);
//                }
            zpOUT.setDistance(zp.getDistance());
            zpOUT.setLatitud(zp.getLatitud());
            zpOUT.setLongitud(zp.getLongitud());
            zpOUT.setName(zp.getName());
            zpOUT.setReferenceId(zp.getReferenceId());
            zpOUT.setUsers(roomDao.countUsers(zp.getId()).intValue());//zp.getRooms().size());
            zpOUT.setListeners(roomDao.countListeners(zp.getId()).intValue());
            zpsOUT.add(zpOUT);
//            }
        }
        return zpsOUT;
    }

    @Override
    @Transactional
    public ZeepointJoinedOUT joinZpoint(Long id, Long userId, BigDecimal lat, BigDecimal lon) {

        Zeepoint zpoint = zeepointDao.findById(id, false);
        User user = userDao.findById(userId, false);
        Room room = null;
        if (!user.getRooms().isEmpty()) {
            room = user.getRooms().iterator().next();
        } else {
            room = new Room();
            room.setUser(user);
        }
        if (isInRange(zpoint, lat, lon)) {
            room.setStatus(USER_STATUS);
        } else {
            room.setStatus(LISTENER_STATUS);
        }
        room.setZeepoint(zpoint);
        roomDao.makePersistent(room);

        ZeepointJoinedOUT zpJOUT = new ZeepointJoinedOUT();
        ZeepointOUT zpOUT = new ZeepointOUT();
        zpOUT.setId(zpoint.getId());
        if (zpoint.getDistance() == null) {
            zpoint.setDistance(0);
        }
        zpOUT.setJoined(true);
        zpOUT.setDistance(zpoint.getDistance());
        zpOUT.setLatitud(zpoint.getLatitud());
        zpOUT.setLongitud(zpoint.getLongitud());
        zpOUT.setName(zpoint.getName());
        zpOUT.setReferenceId(zpoint.getReferenceId());
        zpOUT.setUsers(roomDao.countUsers(zpoint.getId()).intValue());//zp.getRooms().size());
        zpOUT.setListeners(roomDao.countListeners(zpoint.getId()).intValue());
        zpJOUT.setZeePointOut(zpOUT);

        List<Message> messages = messageDao.getLastMessages(id);
        List<ZipointMessage> zPMessages = new ArrayList<>();
        for (Message zMessage : reversed(messages)) {
            ZipointMessage zPMessage = new ZipointMessage();
            zPMessage.setChannel(zpoint.getReferenceId());
            zPMessage.setMessageId(zMessage.getId());
            zPMessage.setMessage(zMessage.getMessage());
            zPMessage.setTime(zMessage.getDate());
            zPMessage.setUserId(zMessage.getUser().getId());
            zPMessage.setFbId(zMessage.getUser().getFbId());
            zPMessage.setUserName(zMessage.getUser().getName());
            zPMessages.add(zPMessage);
        }
        zpJOUT.setzMessages(zPMessages);
        return zpJOUT;
    }

    private boolean isInRange(Zeepoint zpoint, BigDecimal lat, BigDecimal lon) {
        double distance = distance(zpoint.getLatitud().doubleValue(), zpoint.getLongitud().doubleValue(), lat.doubleValue(), lon.doubleValue(), 'M');
        System.out.println("Distance: " + distance);
        if (distance <= 100) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInRange(double distance) {

        if (distance <= 100) {
            return true;
        } else {
            return false;
        }
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'M') {
            dist = dist * 1609.344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    @Override
    @Transactional
    public ZipointMessagesOUT getPreviousMessages(Long id, Long lastMessageId) {
        ZipointMessagesOUT zpMessagesOut = new ZipointMessagesOUT();
        Zeepoint zpoint = zeepointDao.findById(id, false);
        List<Message> messages = messageDao.getPreviousMessages(id, lastMessageId);
        List<ZipointMessage> zPMessages = new ArrayList<>();
        for (Message zMessage : messages) {
            ZipointMessage zPMessage = new ZipointMessage();
            zPMessage.setChannel(zpoint.getReferenceId());
            zPMessage.setMessageId(zMessage.getId());
            zPMessage.setMessage(zMessage.getMessage());
            zPMessage.setTime(zMessage.getDate());
            zPMessage.setUserId(zMessage.getUser().getId());
            zPMessage.setFbId(zMessage.getUser().getFbId());
            zPMessage.setUserName(zMessage.getUser().getName());
            zPMessages.add(zPMessage);
        }
        zpMessagesOut.setzMessages(zPMessages);
        return zpMessagesOut;
    }

    @Override
    @Transactional
    public List<UserOUT> getUsers(Long ziPointId) {
        List<UserOUT> usersOUT = new ArrayList<>();
        List<User> users = userDao.getUsers(ziPointId);
        for (User user : users) {
            UserOUT userOUT = new UserOUT();
            userOUT.setAge(user.getAge());
            userOUT.setEmail(user.getEmail());
            userOUT.setFbId(user.getFbId());
            userOUT.setGender(user.getGender());
            userOUT.setId(user.getId());
            userOUT.setName(user.getName());
            usersOUT.add(userOUT);
        }

//        Zeepoint
//        ZipointMessagesOUT zpMessagesOut = new ZipointMessagesOUT();
//        Zeepoint zpoint = zeepointDao.findById(id, false);
//        List<Message> messages = messageDao.getPreviousMessages(id, lastMessageId);
//        List<ZipointMessage> zPMessages = new ArrayList<>();
//        for (Message zMessage : messages) {
//            ZipointMessage zPMessage = new ZipointMessage();
//            zPMessage.setChannel(zpoint.getReferenceId());
//            zPMessage.setMessageId(zMessage.getId());
//            zPMessage.setMessage(zMessage.getMessage());
//            zPMessage.setTime(zMessage.getDate());
//            zPMessage.setUserId(zMessage.getUser().getId());
//            zPMessage.setFbId(zMessage.getUser().getFbId());
//            zPMessage.setUserName(zMessage.getUser().getName());
//            zPMessages.add(zPMessage);
//        }
//        zpMessagesOut.setzMessages(zPMessages);
        return usersOUT;
    }

}
