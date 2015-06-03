/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncService{

    //@Autowired NormalService normalService;

    @Async
    public void
            //Future<Boolean> 
        async(String message) {

        // Demonstrate that our beans are being injected
        //System.out.println("Managed bean injected: " + (normalService != null));


        System.out.println(message);

        //return new AsyncResult<>(true);
    }

}