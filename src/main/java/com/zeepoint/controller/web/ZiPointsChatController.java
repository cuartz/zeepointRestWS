package com.zeepoint.controller.web;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.zeepoint.controller;
//
//import com.util.OutputMessage;
//import com.util.SimpleMessage;
//import java.time.LocalDateTime;
//import java.util.Date;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.annotation.SubscribeMapping;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// *
// * @author cuartz
// */
//@Controller
//@RequestMapping("/")
//public class ZiPointsChatController {
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String viewApplication() {
//        return "index";
//    }
//
//    @RequestMapping(value = "/prueba", method = RequestMethod.GET)
//    public String viewApplication2() {
//        return "prueba";
//    }
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/message")
//    public OutputMessage sendMessage(SimpleMessage message) {
//        return new OutputMessage(message, new Date());
//    }
//    
//    @SubscribeMapping("/topic/message")
//public Simple simple(@DestinationVariable String referenceId, @DestinationVariable String driverId) {
//    return new Simple(fleetId, driverId);
//}
//
//    @RequestMapping(value = "/jsr310", params  = "localDateTime")
//    @ResponseBody
//    public LocalDateTime test(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime localDateTime) {
//        return localDateTime;
//    }
//}
