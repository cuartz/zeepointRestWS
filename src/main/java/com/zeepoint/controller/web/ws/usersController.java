package com.zeepoint.controller.web.ws;

import com.zeepoint.controller.mobile.ws.MobileAppRestController;
import com.zeepoint.communication.UserOUT;
import com.zeepoint.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



public class usersController extends MobileAppRestController{
	@Autowired
	private IUsersService usersService;
	
	@RequestMapping("/userlogin")
	public UserOUT user(
                @RequestParam(value = "device_id",required = true) String deviceId,
                @RequestParam(value = "fb_id",required = true) Long fb_id
//                @RequestParam(value = "gender",required = true) char gender,
//                @RequestParam(value = "age",required = true) Integer age,
//                @RequestParam(value = "email",required = true) String email
        ) {
		UserOUT user = usersService.createUser(fb_id, deviceId);
		return user;
	}
}