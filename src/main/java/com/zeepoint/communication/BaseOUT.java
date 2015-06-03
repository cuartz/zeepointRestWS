/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zeepoint.communication;

/**
 *
 * @author cuartz
 */
public class BaseOUT implements java.io.Serializable{
    private Integer errorCode;
    private String errorMessage;

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
