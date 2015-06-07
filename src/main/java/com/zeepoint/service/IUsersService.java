/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import com.zeepoint.communication.UserOUT;

/**
 *
 * @author cuartz
 */
public interface IUsersService {
    public UserOUT createUser(Long fb_id, String deviceId);

    public UserOUT saveUser(String name, Long fb_id, String gender, String email);
    
}
