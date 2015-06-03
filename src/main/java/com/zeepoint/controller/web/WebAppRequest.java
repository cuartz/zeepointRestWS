/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author cuartz
 */
@Controller
@RequestMapping("/web")
class WebAppRequest
{

    
    @RequestMapping("/angular")
    public String homeAngular() {
        return "angular";
    }
    
        @RequestMapping("/angularmaps")
    public String home2() {
        return "angularmaps";
    }
    
        @RequestMapping(value = "/prueba", method = RequestMethod.GET)
    public String viewApplication2() {
        return "prueba";
    }
    
//    @RequestMapping("/")
//    public String allLogin() {
//        return "login";
//    }
}
