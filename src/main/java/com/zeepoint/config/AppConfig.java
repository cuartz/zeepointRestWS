package com.zeepoint.config;  
 
//import javax.jms.ConnectionFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration 
@ComponentScan(basePackages = {"com.zeepoint.service","com.zeepoint.DAO"}, excludeFilters = {
    @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
    @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION),
    @ComponentScan.Filter(value = RestController.class, type = FilterType.ANNOTATION)
}) 
//@EnableJms
@EnableWebMvc   
public class AppConfig {  
    
        @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    
//        @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory =
//                new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setDestinationResolver(destinationResolver());
//        factory.setConcurrency("3-10");
//        return factory;
//    }
    
//    @Bean // Strictly speaking this bean is not necessary as boot creates a default
//    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        return factory;
//    }

}  
