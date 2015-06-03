/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;
import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author cuartz
 */
@Service
@Scope("singleton")
public class IosNotificationService {

    public static final String CERTIFICATE_PATH = "/Users/cuartz/Desktop/certsAPNS/zipoints.p12";
    public static final String CERTIFICATE_PASSWORD = "wasa0901852592C";
    public static ApnsService service = null;


    @PostConstruct
    public void init() {
        service
                = APNS.newService()
                .withCert(CERTIFICATE_PATH, CERTIFICATE_PASSWORD)
                .withSandboxDestination()
                .build();
    }

    public boolean sendNotification(String message, Integer badge, String deviceToken) {

        try {
            PayloadBuilder payloadBuilder = APNS.newPayload().alertBody(message);

            payloadBuilder.actionKey("OK");
            payloadBuilder.badge(badge);
            String playload = payloadBuilder.build();
            service.push(deviceToken, playload);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
