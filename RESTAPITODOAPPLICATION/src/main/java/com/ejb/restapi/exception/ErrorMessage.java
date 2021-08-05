package com.ejb.restapi.exception;

import javax.ws.rs.core.Response.ResponseBuilder;

public class ErrorMessage {
    private String error;
 
    public ErrorMessage(String error) {
        this.error = error;
    }
 
    public String getError() {
        return error;
    }

}