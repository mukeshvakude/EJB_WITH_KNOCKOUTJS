package com.ejb.restapi.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class TodoServiceExceptionHandler implements
    ExceptionMapper<TodoServiceException> {
 
	  
	@Override
	public Response toResponse(TodoServiceException exception) {
		// TODO Auto-generated method stub
		return Response.status(Status.BAD_REQUEST)
	            .entity(new ErrorMessage(exception.getMessage())
	            ).type(MediaType.APPLICATION_JSON).build();
	}   
}