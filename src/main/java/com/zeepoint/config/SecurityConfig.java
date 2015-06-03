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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//@EnableWebSecurity//@EnableWebMvcSecurity
//@EnableScheduling
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@Order(1)
//@ComponentScan(basePackages = "com.zeepoint.controller")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//	http
//		.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.and()
//		.httpBasic();
        
        
          http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .antMatchers("/web/**").access("hasRole('ROLE_USER')")
		.and().formLogin()
                .loginPage("/web/login").permitAll();

//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/sign-up").permitAll()
//                .antMatchers("/sign-in").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/web/login")
//                .permitAll()
//                .and()
//                .rememberMe()
//                .rememberMeServices(tokenBasedRememberMeService);
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsServiceImpl)
//                .passwordEncoder(bCryptPasswordEncoder());
//        auth.authenticationProvider(rememberMeAuthenticationProvider);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
