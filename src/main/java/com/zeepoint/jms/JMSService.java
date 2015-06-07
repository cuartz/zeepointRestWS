/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.jms;

//import javax.jms.JMSException;
import com.zeepoint.communication.ZipointJoin;
import com.zeepoint.communication.ZipointMessage;
import com.zeepoint.DAO.MessageDAO;
import com.zeepoint.DAO.ZeepointDAO;
import com.zeepoint.DAO.ZipuserDAO;
import com.zeepoint.model.Message;
import com.zeepoint.model.Room;
import com.zeepoint.model.Zipuser;
import com.zeepoint.model.Zeepoint;
import com.zeepoint.service.IosNotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

//import javax.jms.Message;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JMSService {

    @Autowired
    IosNotificationService iosNotification;
    
        @Autowired
    ZipuserDAO userDao;
        
                @Autowired
    MessageDAO messageDao;
        
                @Autowired
    ZeepointDAO zeepointDao;

    @JmsListener(containerFactory = "myJmsContainerFactory", destination = "ios.notification.join")//@JmsListener(containerFactory = "myJmsContainerFactory", destination = "myQueue")
    public void processOrderJoinCHannel(ZipointJoin zpointJoin) {
        //String token = "f4ed6403 76267a64 34d04a99 65bcc762 cdd8418b 0e64705d 102c4709 55a869db";
        Zipuser user = userDao.findById(zpointJoin.getUserId(), false);
        Zeepoint zPoint=zeepointDao.findById(zpointJoin.getChannel(), false);
        Zipuser notificationUser=zPoint.getZipuser();
        if (user!=notificationUser){
            iosNotification.sendNotification(user.getName()+" joined your point: "+zPoint.getName(), 1, notificationUser.getDeviceId());
        }
    }
    @Transactional
    @JmsListener(containerFactory = "myJmsContainerFactory", destination = "ios.notification.groupmessage")//@JmsListener(containerFactory = "myJmsContainerFactory", destination = "myQueue")
    public void processOrderGroupMessage(ZipointMessage message) {
        //String token = "f4ed6403 76267a64 34d04a99 65bcc762 cdd8418b 0e64705d 102c4709 55a869db";
        try{
        Zipuser user = userDao.findById(message.getUserId(), false);
        List<Zeepoint> zeePoints=zeepointDao.findByReference(message.getChannel());
        if (zeePoints!=null && !zeePoints.isEmpty()){
            Zeepoint zeePoint=zeePoints.get(0);
            Message ziPointMessage = new Message();
            ziPointMessage.setMessage(message.getMessage());
            ziPointMessage.setZipuser(user);
            ziPointMessage.setZeepoint(zeePoint);
            ziPointMessage.setDate(message.getTime());
            messageDao.makePersistent(ziPointMessage);
        for (Room room:zeePoint.getRooms()){
            Zipuser notificationUser=room.getZipuser();
            //user.setNotifications(user.getNotifications()+1)
            //userDao.makePersistent(user);
            if (user!=notificationUser){
                iosNotification.sendNotification(user.getName()+" send a message to the point: "+zeePoint.getName(), 1, notificationUser.getDeviceId());
            }
        }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        @Transactional
    @JmsListener(containerFactory = "myJmsContainerFactory", destination = "ios.notification.groupmessage")//@JmsListener(containerFactory = "myJmsContainerFactory", destination = "myQueue")
    public void processOrderPrivateMessage(ZipointMessage message) {
        //String token = "f4ed6403 76267a64 34d04a99 65bcc762 cdd8418b 0e64705d 102c4709 55a869db";
        try{
        Zipuser user = userDao.findById(message.getUserId(), false);
        List<Zeepoint> zeePoints=zeepointDao.findByReference(message.getChannel());
        if (zeePoints!=null && !zeePoints.isEmpty()){
            Zeepoint zeePoint=zeePoints.get(0);
            Message ziPointMessage = new Message();
            ziPointMessage.setMessage(message.getMessage());
            ziPointMessage.setZipuser(user);
            ziPointMessage.setZeepoint(zeePoint);
            ziPointMessage.setDate(message.getTime());
            messageDao.makePersistent(ziPointMessage);
        for (Room room:zeePoint.getRooms()){
            Zipuser notificationUser=room.getZipuser();
            //user.setNotifications(user.getNotifications()+1)
            //userDao.makePersistent(user);
            if (user!=notificationUser){
                iosNotification.sendNotification(user.getName()+" send a message to the point: "+zeePoint.getName(), 1, notificationUser.getDeviceId());
            }
        }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        @Transactional
    @JmsListener(containerFactory = "myJmsContainerFactory", destination = "save.group.message")//@JmsListener(containerFactory = "myJmsContainerFactory", destination = "myQueue")
    public void processOrderSaveMessage(ZipointMessage message) {
        //String token = "f4ed6403 76267a64 34d04a99 65bcc762 cdd8418b 0e64705d 102c4709 55a869db";
        try{
            
            Message groupMessage = new Message();
            groupMessage.setMessage(message.getMessage());
        Zipuser user = userDao.findById(message.getUserId(), false);
        List<Zeepoint> zeePoints=zeepointDao.findByReference(message.getChannel());
        if (zeePoints!=null && !zeePoints.isEmpty()){
            Zeepoint zeePoint=zeePoints.get(0);
        for (Room room:zeePoint.getRooms()){
            Zipuser notificationUser=room.getZipuser();
            if (user!=notificationUser){
                iosNotification.sendNotification(user.getName()+" send a message to your point: "+zeePoint.getName(), 1, notificationUser.getDeviceId());
            }
        }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//External jms queue:
    /*
     InitialContext initCtx = new InitialContext();
     Context envContext = (Context) initCtx.lookup("java:comp/env");
     ConnectionFactory connectionFactory = (ConnectionFactory) envContext.lookup("jms/ConnectionFactory");
     Connection connection = connectionFactory.createConnection();
     Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
     Destination destination = session.createTopic("jms/topic/MyTopic");
     MessageProducer producer = session.createProducer(destination);
     TextMessage msg=session.createTextMessage();
     msg.setText("Message sent");
     producer.send(msg);
     */
//    private JmsTemplate jmsTemplate;
//
//    public JMSService(JmsTemplate template) {
//        this.jmsTemplate = template;
//    }
//
//    public void sendMessage() {
//
//        jmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage("Hello");
//            }
//        }
//        );
//    }
//
//    public String receiveMessage() {
//        try {
//            TextMessage receivedMessage = (TextMessage) jmsTemplate.receive();
//            return (String) receivedMessage.getText();
//        } catch (JMSException jmsException) {
//            throw JmsUtils.convertJmsAccessException(jmsException);
//        }
//    }
}
