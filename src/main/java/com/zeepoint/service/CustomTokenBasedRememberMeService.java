/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
/**
 *
 * @author cuartz
 */
//@Service
public class CustomTokenBasedRememberMeService extends TokenBasedRememberMeServices{
	
    public CustomTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }
    
	
	private final String HEADER_SECURITY_TOKEN = "exact_binary_date";
	
    /**
     * Locates the Spring Security remember me token in the request and returns its value.
     *
     * @param request the submitted request which is to be authenticated
     * @return the value of the request header (which was originally provided by the cookie - API expects it in header)
     */
    @Override 
    protected String extractRememberMeCookie(HttpServletRequest request) {
    	String token = request.getHeader(HEADER_SECURITY_TOKEN);
        if ((token == null) || (token.length() == 0)) {
            return null;
        }
        
        return token;
    }
}