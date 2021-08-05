package com.ejb.restapi.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejb.restapi.exception.TodoServiceException;
import com.ejb.restapi.models.Tasks;
import com.ejb.restapi.models.User;
import com.ejb.restapi.models.dao.TasksDAO;
import com.ejb.restapi.models.dao.UserDAO;


@RequestScoped
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TasksResources {

	@Inject
	UserDAO userdao;
	
	@Inject
	TasksDAO taskdao;
	
	@POST
	@Path("{userId}/tasks")
	public Response addTask(@PathParam("userId") int userId, Tasks task) throws TodoServiceException {
		
		User user = userdao.getUser(userId);
		task.setUser(user);
		return Response.ok(taskdao.addTask(task)).build();
		
	}
	@GET
	@Path("{userId}/tasks")
	public Response getAllTasksOfUser(@PathParam("userId") int userId) throws TodoServiceException {
//		taskdao.getAllTasksOfUser((userId));
		System.out.println("coming into all tasks"+ userId);
		return Response.ok(taskdao.getAllTasksOfUser((int) userId)).build();
	}
	
	
	
	@DELETE
	@Path("{userId}/tasks/{taskId}")
	public Response deleteTask(@PathParam("userId") int userId,@PathParam("taskId") int taskId) {
		
		return Response.ok(taskdao.deleteTask(taskId, userId)).build();
		
	}
	@PUT
	@Path("{userId}/tasks/{taskId}")
	public Response updateTask(@PathParam("userId") int userId,@PathParam("taskId") int taskId,Tasks task) throws TodoServiceException {
		
		
		return Response.ok(taskdao.updateTask(userId, taskId, task)).build();
	}
	@PUT
	@Path("{userId}/tasks/{taskId}/changeStatus")
	public Response changeTaskStatus(@PathParam("userId") int userId,@PathParam("taskId") int taskId,Tasks task){
		return Response.ok(taskdao.changeTaskStatus(userId,taskId, task)).build();
	}
}
