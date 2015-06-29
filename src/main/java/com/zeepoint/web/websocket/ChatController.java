/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.web.websocket;

import com.zeepoint.communication.OutputMessage;
import com.zeepoint.communication.PrivateOutputMessage;
import com.zeepoint.communication.ZipointMessage;
import com.zeepoint.communication.ZipointPrivateMessage;
import com.zeepoint.service.ZeePointGroupService;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cuartz
 */
@Controller
@RequestMapping("/")
//@Secured("USER")
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    JmsTemplate jmsTemplate;
//    @Autowired
//    JmsTemplate jmsTemplate;

    @RequestMapping(value = "/prueba", method = RequestMethod.GET)
    public String viewApplication2() {
        return "prueba";
    }

    @MessageMapping("/chat/{channel}")
    //@SendTo("/topic/channels/{channel}")
    public void sendMessage(@DestinationVariable String channel, OutputMessage message) {
        //jmsTemplate.convertAndSend("iios.notification.groupmessage", message);
        ZipointMessage zMessage=new ZipointMessage(message, new Date());
//+message.getChannel()
//        if (message.getMessageType()==ZeePointGroupService.PRIVATE_MESSAGE){
//            messagingTemplate.convertAndSend("/topic/channels/" + message.getChannel(), zMessage);
//            jmsTemplate.convertAndSend("ios.notification.privatemessage", zMessage);
//        }else{
             messagingTemplate.convertAndSend("/topic/channels/" + channel, zMessage);
            jmsTemplate.convertAndSend("ios.notification.groupmessage", zMessage);
            
            
           // return zMessage;
       // }
        //return new OutputMessage(message, new Date());
    }
    
    @MessageMapping("/chat/private/{userId}")
    //@SendTo("/topic/channels/{channel}")
    public void sendPrivateMessage(@DestinationVariable Long userId, PrivateOutputMessage message) {
        //jmsTemplate.convertAndSend("iios.notification.groupmessage", message);
        ZipointPrivateMessage zMessage=new ZipointPrivateMessage(message, new Date(), userId);
//+message.getChannel()
//        if (message.getMessageType()==ZeePointGroupService.PRIVATE_MESSAGE){
//            messagingTemplate.convertAndSend("/topic/channels/" + message.getChannel(), zMessage);
//            jmsTemplate.convertAndSend("ios.notification.privatemessage", zMessage);
//        }else{
             messagingTemplate.convertAndSend("/topic/private/" + userId, zMessage);
            jmsTemplate.convertAndSend("ios.notification.privatemessage", zMessage);
            
            
           // return zMessage;
       // }
        //return new OutputMessage(message, new Date());
    }

    @SubscribeMapping("/channels")
    public void simple(Object message) {
    //return 
        //new OutputMessage(message, new Date());
        String a = "paso";
        System.out.println("paso por aqui" + a);

    }

    @SubscribeMapping("/channels/zp-1883563241")
    public void simple2(Object message) {
    //return 
        //new OutputMessage(message, new Date());
        String a = "paso";
        System.out.println("paso por aqui" + a);

    }

    @SubscribeMapping("/channels/*")
    public void simple3(Object message) {
    //return 
        //new OutputMessage(message, new Date());
        String a = "paso";
        System.out.println("paso por aqui" + a);

    }

    @SubscribeMapping("/topic/channels")
    public void simple0(Object message) {
    //return 
        //new OutputMessage(message, new Date());
        String a = "paso";
        System.out.println("paso por aqui" + a);

    }

    @SubscribeMapping("/topic/channels/zp-1883563241")
    public void simple20(Object message) {
    //return 
        //new OutputMessage(message, new Date());
        String a = "paso";
        System.out.println("paso por aqui" + a);

    }

    @SubscribeMapping("/topic/channels/*")
    public void simple30(Object message) {
    //return 
        //new OutputMessage(message, new Date());
        String a = "paso";
        System.out.println("paso por aqui" + a);

    }
//
//    @RequestMapping(value = "/jsr310", params  = "localDateTime")
//    @ResponseBody
//    public LocalDateTime test(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime localDateTime) {
//        return localDateTime;
//    }
}
