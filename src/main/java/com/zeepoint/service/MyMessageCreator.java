package com.zeepoint.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuartz
 */
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;
public class MyMessageCreator implements MessageCreator {
	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage("Hello World!");
	}
}
