/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.communication;

import java.util.List;

/**
 *
 * @author cuartz
 */
public class UsersOUT extends BaseOUT{
    private List<UserOUT> users;

    /**
     * @return the users
     */
    public List<UserOUT> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserOUT> users) {
        this.users = users;
    }
}
