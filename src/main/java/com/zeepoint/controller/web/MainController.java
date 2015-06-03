/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.controller.web;

import com.zeepoint.communication.ZipointMessage;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
/**
 *
 * @author cuartz
 */
@Controller
public class MainController {
    
            @Autowired
    JmsTemplate jmsTemplate;
    
    @RequestMapping("/")
    public String allUnknownURLS() {
        return "index";
    }
    
    @RequestMapping("/web")
    public String home() {
        return "userindex";
    }
    
}
