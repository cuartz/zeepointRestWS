/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.config;


import com.zeepoint.service.CustomTokenBasedRememberMeService;
import com.zeepoint.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author cuartz
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class MobileSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String tokenKey = "g6iia89ojg5s5oa00sj60apikys4";
	
	@Autowired 
        private UserDetailsServiceImpl userDetailsService;
 
	@Override protected void configure(HttpSecurity http) throws Exception {
        http
        	.antMatcher("/mobile/**")
        	.csrf()
        		.disable()
            .authorizeRequests().anyRequest().authenticated().and()
        	.addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class )
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        		.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
	 
	 /**
	  * Remember me config
     * @param auth
     * @throws java.lang.Exception
	  */
	 @Override 
         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(rememberMeAuthenticationProvider());
	 }
	 @Bean 
         public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception{
		 return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
	 }
	 @Bean 
         public CustomTokenBasedRememberMeService tokenBasedRememberMeService(){
		 CustomTokenBasedRememberMeService service = new CustomTokenBasedRememberMeService(tokenKey, userDetailsService);
		 service.setAlwaysRemember(true);
		 service.setCookieName("at");
		 return service;
	 }
	 @Bean RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
		 return new RememberMeAuthenticationProvider(tokenKey);
	 }
}
