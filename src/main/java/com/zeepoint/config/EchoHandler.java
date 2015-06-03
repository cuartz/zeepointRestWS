/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.config;

/**
 *
 * @author cuartz
 */
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import org.springframework.web.socket.WebSocketMessage;

public class EchoHandler implements WebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        sessions.forEach(session -> {
//            try {
//                session.sendMessage(message);
//            } catch (IOException ignore) {
//            }
//        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession wss) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supportsPartialMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
