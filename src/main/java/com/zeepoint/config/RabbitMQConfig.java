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
//import com.zeepoint.service.Reciever;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Configuration
////@EnableAutoConfiguration
//public class RabbitMQConfig {
//	final static String queueName = "concretepage";
//	@Autowired
//	RabbitTemplate rabbitTemplate;
//	@Bean
//	Queue queue() {
//		return new Queue(queueName, false);
//	}
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange("concretepage-exchange");
//	}
//	@Bean
//	Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(queueName);
//	}
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
//		container.setMessageListener(listenerAdapter);
//		return container;
//	}
//        @Bean
//        Reciever receiver() {
//                return new Reciever();
//        }
//	@Bean
//	MessageListenerAdapter listenerAdapter(Reciever receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMsg");
//	}
//} 
