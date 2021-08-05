package com.ejb.restapi.resources;

import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejb.restapi.exception.TodoServiceException;
import com.ejb.restapi.models.User;
import com.ejb.restapi.models.dao.UserDAO;

@RequestScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {

	@Inject
	UserDAO userdao;
	
	@GET
	public Response getAllUsers() {
		return Response.ok(userdao.getAllUser()).build();
	}
	@POST
	public  Response addUser(User user) {
		return Response.ok(userdao.addUser(user)).build();
	}
	@PUT
	public  Response updateUserActivationStatus(User user) {
		return Response.ok(userdao.updateUserActivationStatus(user)).build();
	}
	@GET
	@Path("{userId}")
	public Response getUser(@PathParam("userId") int userId) throws TodoServiceException {
		
		return Response.ok(userdao.getUser(userId)).build();
	}
	@POST
	@Path("login")
	public Response authentication(User user) {
		
		User user1 = userdao.authentication(user);
		if(Objects.isNull(user1)) {
			return Response.status(Response.Status.FOUND).entity("Invalid Credentials...!").build();
			
		}else {
			return Response.ok(user1).build();
		}
	}
	@POST
	@Path("register")
	public Response getUserByEmailId(User user){
		System.out.println("hii"+"\n"+user);
		User user1 = userdao.getUserByEmailId(user);
	
		System.out.println("user  " + user1);
		if(Objects.isNull(user1)) {
			return Response.ok(userdao.addUser(user)).build();
		}else {
			 return Response.status(Response.Status.FOUND).entity("User Aleready Exits").build();
		}
		
		
		
	}
}
