/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *
 * @author cuartz
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "com.zeepoint.web.websocket")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer// implements WebSocketConfigurer 
{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");//"/queue"
        config.setApplicationDestinationPrefixes("/app");//,"/channels");
       // config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(echoHandler(), "/chat");
//    }
//
//    @Bean
//    public EchoHandler echoHandler() {
//        return new EchoHandler();
//    }
}
