/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author cuartz
 */
@Controller
//@RequestMapping("/")
public class LoginController extends WebAppRequest{
    
    @RequestMapping(value="/login")//,method = RequestMethod.GET)
    public String login() {
        return "login";
    }
   
    
    
//    @RequestMapping(value="/",method = RequestMethod.GET)
//    public String viewApplication() {
//        return "index";
//    }
}
