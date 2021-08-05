package com.ejb.restapi.exception;

import java.io.Serializable;

public class TodoServiceException extends Exception implements
    Serializable {
 
    private static final long serialVersionUID = 1169426381288170661L;
 
    public TodoServiceException() {
        super();
    }
 
    public TodoServiceException(String msg) {
        super(msg);
    }
 
    public TodoServiceException(String msg, Exception e) {
        super(msg, e);
    }
}