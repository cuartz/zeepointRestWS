package com.zeepoint.config;


import com.zeepoint.service.Reciever;
import com.zeepoint.service.MyMessageCreator;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuartz
 */
@Configuration
@EnableJms
@ComponentScan(basePackages = "com.zeepoint.jms")
public class JmsConfig {
    
	
//    @Bean
//    public DefaultJmsListenerContainerFactory myContainerFactory() {
//        DefaultJmsListenerContainerFactory factory =
//                new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setDestinationResolver(destinationResolver());
//        factory.setConcurrency("3-10");
//        return factory;
//    }
    
    
    
        @Bean // Strictly speaking this bean is not necessary as boot creates a default
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
    
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //jmsTemplate.setDefaultDestination(new ActiveMQQueue("ios.notifications"));
        jmsTemplate.setConnectionFactory(connectionFactory());
        return jmsTemplate;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("vm://localhost");//("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }
    
//    @Bean
//public DefaultMessageListenerContainer jmsListenerContainer() {
//    DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
//    dmlc.setConnectionFactory(connectionFactory());
//    dmlc.setDestination(new ActiveMQQueue("orders.queue"));
//
//    // To schedule our concurrent listening tasks
//    dmlc.setTaskExecutor(taskExecutor());
//
//    // To perform actual message processing
//    dmlc.setMessageListener(messageListener());
//
//    dmlc.setConcurrentConsumers(10);
//
//    // ... more parameters that you might want to inject ...
//    return dmlc;
//}
//    @Bean
//    Reciever msgReceiver() {
//        return new Reciever();
//    }
//    @Bean
//    MessageCreator myMessageCreator() {
//        return new MyMessageCreator();
//    }
//    @Bean
//    MessageListenerAdapter messageListenerAdp(Reciever receiver) {
//        MessageListenerAdapter messageListenerAdp = new MessageListenerAdapter(receiver);
//        messageListenerAdp.setDefaultListenerMethod("receiveMessage");
//        return messageListenerAdp;
//    }
//    @Bean
//    SimpleMessageListenerContainer simpleMsgLisContainer(MessageListenerAdapter messageListenerAdp,
//                                             ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer simpleMsgLisContainer = new SimpleMessageListenerContainer();
//        simpleMsgLisContainer.setMessageListener(messageListenerAdp);
//        simpleMsgLisContainer.setConnectionFactory(connectionFactory);
//        simpleMsgLisContainer.setDestinationName("admin@concretepage.com");
//        simpleMsgLisContainer.setConcurrency("2-5");
//        return simpleMsgLisContainer;
//    }    
}
