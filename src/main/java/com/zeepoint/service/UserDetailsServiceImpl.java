/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import com.util.ApplicationUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/**
 *
 * @author cuartz
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        
        
        		username = "user";//username.toLowerCase();
		try {
			//Account account = accountService.loadUserAccountByEmail(username);
//			if (account == null) {
//				throw new UsernameNotFoundException("Could not find email: " + username + "in the DB.");
//			}

			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
//			for (Role r : account.getRoles()) {
				auths.add(new SimpleGrantedAuthority("USER"));
//			}
			ApplicationUser user = null;
			try {
				user = new ApplicationUser(new Long(1L), username, "password", true, true, true, true, auths);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username + "not found", e);
		}
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
