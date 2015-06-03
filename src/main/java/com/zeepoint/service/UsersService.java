/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.service;

import com.zeepoint.DAO.UserDAO;
import com.zeepoint.communication.UserOUT;
import com.zeepoint.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cuartz
 */
@Component
public class UsersService implements IUsersService{
        @Autowired
	private UserDAO usersDao;
	@Override
        @Transactional
	public UserOUT createUser(Long fb_id, String deviceId){

            List<User> users=usersDao.findByFBID(fb_id);
            User user=null;
            if (users.isEmpty()){
                user=new User();
                user.setFbId(fb_id);
                user.setDeviceId(deviceId);
                usersDao.makePersistent(user);
            }else{
                user=users.get(0);
                if (!deviceId.equals(user.getDeviceId())){
                    user.setDeviceId(deviceId);
                    usersDao.makePersistent(user);
                }
            }
            
            return new UserOUT(user);
	}

    @Override
    @Transactional
    public UserOUT saveUser(String name, Long fb_id, String gender, String email) {
            List<User> users=usersDao.findByFBID(fb_id);
            User user=null;
            if (users.isEmpty()){
                user=new User();
                user.setFbId(fb_id);
                user.setGender(gender);
                user.setName(name);
                user.setEmail(email);
                usersDao.makePersistent(user);
            }else{
                user=users.get(0);
                user.setGender(gender);
                user.setName(name);
                user.setEmail(email);
                usersDao.makePersistent(user);
            }
            
            return new UserOUT(user);
    }
}
