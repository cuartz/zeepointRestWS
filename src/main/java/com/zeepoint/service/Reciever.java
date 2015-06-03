/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import java.util.concurrent.CountDownLatch;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author cuartz
 */
public class Reciever {

    protected Topic queue;

    protected String queueName = "jms/topic/MyTopic";

    protected String url = "tcp://localhost:61616";

    protected int ackMode = Session.AUTO_ACKNOWLEDGE;

    public static void main(String[] args) {
        Reciever rec = new Reciever();
        try {
            rec.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        TopicConnection connection = (TopicConnection) connectionFactory.createTopicConnection();

        connection.start();
        MessageConsumer consumer = null;
        Session session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = session.createTopic(queueName);
        consumer = session.createConsumer(queue);

        System.out.println(" Waiting for message (max 5) ");

        for (int i = 0; i < 5; i++) {
            Message message = consumer.receive();
            processMessage(message);

        }

        System.out.println("Closing connection");
        consumer.close();
        session.close();
        connection.close();

    }

    public void processMessage(Message message) {

        try {

            TextMessage txtMsg = (TextMessage) message;

            System.out.println("Received a message: " + txtMsg.getText());

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
