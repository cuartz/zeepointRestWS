package com.zeepoint.controller.mobile.ws;

import com.zeepoint.controller.web.ws.*;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;
import com.util.ZipointsBLException;
import com.zeepoint.communication.UserOUT;
import com.zeepoint.communication.UsersOUT;
import com.zeepoint.communication.ZipointJoin;
import com.zeepoint.communication.ZeepointJoinedOUT;
import com.zeepoint.communication.ZeepointOUT;
import com.zeepoint.communication.ZeepointsOUT;
import com.zeepoint.communication.ZipointMessagesOUT;
import com.zeepoint.communication.ZipointPrivateMessagesOUT;
import com.zeepoint.model.Zeepoint;
import com.zeepoint.service.AsyncService;
import com.zeepoint.service.IZeePointGroupService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/zeepointgroups")
//@Secured("ROLE_ADMIN")
public class ZeePointGroupMobileController extends MobileAppRestController {

    @Autowired
    private IZeePointGroupService zpointService;

    @Autowired
    private AsyncService async;

        //@Autowired
    //RabbitTemplate rabbitTemplate;// = (RabbitTemplate)ctx.getBean("rabbitTemplate");
//        @Autowired
//        Receiver receiver;// = (MessageReceiver)ctx.getBean("receiver");
//                MessageCreator messageCreator;// = context.getBean(MessageCreator.class);
//        JmsTemplate jmsTemplate;// = context.getBean(JmsTemplate.class);
//        
    final static String queueName = "concretepage";

    @RequestMapping("/zeepointgroups/addzpoint")
    public ZeepointOUT addZeePoint(
            @RequestParam(value = "name", required = true, defaultValue = "my zeePoint") String name,
            @RequestParam(value = "lon", required = true) BigDecimal lon,
            @RequestParam(value = "lat", required = true) BigDecimal lat,
            @RequestParam(value = "fb_id", required = false) Long fb_id,
            @RequestParam(value = "country", required = true) String country,
            @RequestParam(value = "state", required = true) String state,
            @RequestParam(value = "city", required = true) String city) {
        ZeepointOUT zp =null;
    
        try{
            zp = zpointService.createZpoint(lat, lon, name, fb_id, country, state, city);
        return zp;
        }catch(ZipointsBLException zE){
                zp=new ZeepointOUT();
                zp.setErrorCode(1);
                zp.setErrorMessage("There is already a ZiPoint here");
        }catch(Exception e){
                zp=new ZeepointOUT();
                zp.setErrorCode(501);
                zp.setErrorMessage("go to www.zipoints.com and report the error, we will start fixing it as soon as posible.");
        }
             return zp;   
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @RequestMapping("/zeepointgroups/getzpoints")
    public ZeepointsOUT getAllZpoints(
            @RequestParam(value = "lon", required = true) BigDecimal lon,
            @RequestParam(value = "lat", required = true) BigDecimal lat,
            @RequestParam(value = "user_id", required = true) Long userId,
            @RequestParam(value = "from_row", required = true) Integer fromRow) {
        ZeepointsOUT zpsOUT = new ZeepointsOUT();
        try {
        List<ZeepointOUT> zps=zpointService.getAllZpoints(lat, lon, userId, fromRow, 15);
        
        zpsOUT.setZeePointsOut(zps);

        
            //jmsTemplate.convertAndSend("ios.notification.join", "mensaje");//"mailbox-destination", "mensaje");

//                Push.alert("Hello World!", "keystore.p12", "wasa0901852592C", false, "Your token");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ZeePointGroupMobileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return zpsOUT;
    }
    
        @RequestMapping("/zeepointgroups/getfavoritezpoints")
    public ZeepointsOUT getFavoriteZpoints(
            @RequestParam(value = "lon", required = true) BigDecimal lon,
            @RequestParam(value = "lat", required = true) BigDecimal lat,
            @RequestParam(value = "user_id", required = true) Long userId) {

ZeepointsOUT zpsOUT = new ZeepointsOUT();
        try {
                    List<ZeepointOUT> zps = zpointService.getFavoriteZpoints(lat, lon, userId);
        
        zpsOUT.setZeePointsOut(zps);
            //jmsTemplate.convertAndSend("ios.notification.join", "mensaje");//"mailbox-destination", "mensaje");

//                Push.alert("Hello World!", "keystore.p12", "wasa0901852592C", false, "Your token");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ZeePointGroupMobileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return zpsOUT;
    }

    @RequestMapping("/zeepointgroups/join")
    public ZeepointJoinedOUT joinZpoint(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "user_id", required = true) Long userId,
            @RequestParam(value = "lon", required = true) BigDecimal lon,
            @RequestParam(value = "lat", required = true) BigDecimal lat) {
        ZeepointJoinedOUT join = zpointService.joinZpoint(id, userId, lat, lon);
        
//        ZeepointJoinedOUT zpJout = new ZeepointJoinedOUT();
//        zpJout.setZeePointOut(new );
//        ZeepointsOUT zpsOUT = new ZeepointsOUT();
//        zpsOUT.setZeePointsOut(zps);
        jmsTemplate.convertAndSend("ios.notification.join", new ZipointJoin(id, userId));
        return join;
    }
    
        @RequestMapping("/zeepointgroups/exit")
    public Boolean exitZpoint(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "user_id", required = true) Long userId) {
        return zpointService.exitZpoint(id, userId);
    }
    
    
//        @RequestMapping("/zeepointgroups/join")
//    public ZeepointsOUT getZpointMessages(
//            @RequestParam(value = "id", required = true) Long id) {
//        Zeepoint join = zpointService.joinZpoint(id, userId, lat, lon);
//        List<ZeepointOUT> zps = zpointService.getAllZpoints(lat, lon, userId, 1, 15);
//        ZeepointsOUT zpsOUT = new ZeepointsOUT();
//        zpsOUT.setZeePointsOut(zps);
//        jmsTemplate.convertAndSend("ios.notification.join", new ZipointJoin(id, userId));
//        return zpsOUT;
//    }
    
            @RequestMapping("/zeepointgroups/getmessages")
    public ZipointMessagesOUT getMessages(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "user_id", required = true) Long userId,
            @RequestParam(value = "last_message", required = true) Long messageId) {
        ZipointMessagesOUT previousMessages = zpointService.getPreviousMessages(id, messageId);
//        List<ZeepointOUT> zps = zpointService.getAllZpoints(lat, lon, userId, 1,30);
//        ZeepointsOUT zpsOUT = new ZeepointsOUT();
//        zpsOUT.setZeePointsOut(zps);
        return previousMessages;
    }
    
                @RequestMapping("/zeepointgroups/getpmessages")
    public ZipointPrivateMessagesOUT getPmessages(
            @RequestParam(value = "fromId", required = true) Long fromId,
            @RequestParam(value = "tpId", required = true) Long toId,
            @RequestParam(value = "last_message", required = true) Long messageId) {
        ZipointPrivateMessagesOUT previousMessages = zpointService.getPreviousPrivateMessages(fromId, toId, messageId);
//        List<ZeepointOUT> zps = zpointService.getAllZpoints(lat, lon, userId, 1,30);
//        ZeepointsOUT zpsOUT = new ZeepointsOUT();
//        zpsOUT.setZeePointsOut(zps);
        return previousMessages;
    }
    
                @RequestMapping("/zeepointgroups/getusers")
    public UsersOUT getUsers(
            @RequestParam(value = "id", required = true) Long id) {
        UsersOUT users = new UsersOUT();
        users.setUsers(zpointService.getUsers(id));
        return users;
    }
    
    @RequestMapping("/zeepointgroups/getcontacts")
    public UsersOUT getContacts(
            @RequestParam(value = "id", required = true) Long id) {
        UsersOUT users = new UsersOUT();
        users.setUsers(zpointService.getContacts(id));
        return users;
    }
    
    @RequestMapping("/zeepointgroups/addcontacts")
    public UsersOUT addContacts(
            @RequestParam(value = "user_id", required = true) Long userId,
            @RequestParam(value = "contact_id", required = true) Long contactId) {
        UsersOUT users = new UsersOUT();
        users.setUsers(zpointService.addContact(userId,contactId));
        return users;
    }
    
    @RequestMapping("/zeepointgroups/removecontacts")
    public UsersOUT removeContacts(
            @RequestParam(value = "user_id", required = true) Long userId,
            @RequestParam(value = "contact_id", required = true) Long contactId) {
        UsersOUT users = new UsersOUT();
        users.setUsers(zpointService.removeContact(userId, contactId));
        return users;
    }
}
