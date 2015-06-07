package com.zeepoint.communication;
// Generated Apr 12, 2015 2:37:02 AM by Hibernate Tools 4.3.1

import com.util.EnviromentProperties;
import com.zeepoint.model.Zipuser;
import org.springframework.security.core.userdetails.User;



public class UserOUT extends BaseOUT{


     private Long id;
     private Long fbId;
     private String name;
     private Integer age;
     private String email;
     private String gender;
     private String imageurl;
     private String ziPointName;
     private final String host=EnviromentProperties.HOST;

    public UserOUT() {
    }

    public UserOUT(Long id, Long fbId, String name, Integer age, String email, String gender, String imageurl) {
       this.id=id;
       this.fbId = fbId;
       this.name = name;
       this.age = age;
       this.email = email;
       this.gender = gender;
       this.imageurl = imageurl;
    }
    
    public UserOUT(Zipuser user){
       this.id=user.getId();
       this.fbId = user.getFbId();
       this.name = user.getName();
       this.age = user.getAge();
       this.email = user.getEmail();
       this.gender = user.getGender();
       this.imageurl = user.getImageurl();
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getFbId() {
        return this.fbId;
    }
    
    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageurl() {
        return this.imageurl;
    }
    
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @return the ziPointName
     */
    public String getZiPointName() {
        return ziPointName;
    }

    /**
     * @param ziPointName the ziPointName to set
     */
    public void setZiPointName(String ziPointName) {
        this.ziPointName = ziPointName;
    }
}


