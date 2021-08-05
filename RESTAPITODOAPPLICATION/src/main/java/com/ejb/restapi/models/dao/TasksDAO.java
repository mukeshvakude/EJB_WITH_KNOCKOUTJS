package com.ejb.restapi.models.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.restapi.exception.TodoServiceException;
import com.ejb.restapi.models.Tasks;
import com.ejb.restapi.models.User;


@Stateless
public class TasksDAO {

	@Inject
	UserDAO userdao;
	@PersistenceContext(unitName = "todoapplication-pu")
	private EntityManager entityManager;

	public Tasks getTask(int taskId) {
		return entityManager.find(Tasks.class, taskId);
	}

	public Tasks addTask(Tasks task) {
		entityManager.persist(task);
		return task;
	}

	public List<Tasks> getAllTasksOfUser(int user_id) throws TodoServiceException {
		User findUser = entityManager.find(User.class, user_id);
		List list = null;
		if (findUser == null) {
			throw new  TodoServiceException("user Not found");
		}

		list = entityManager.createQuery("select a from Tasks a  where a.user=:userid").setParameter("userid", findUser)
				.getResultList();
		return list;

	}

	public Tasks deleteTask(int taskId ,int userId ) {
		Tasks task = entityManager.find(Tasks.class, taskId);
		entityManager.remove(task);
		return task;
	}

	public Tasks updateTask(int  userId, int taskId, Tasks task) throws TodoServiceException {
		Tasks tasks = null;
		User findUser = entityManager.find(User.class, userId);
		Tasks findtask = entityManager.find(Tasks.class, taskId);
			tasks = entityManager.find(Tasks.class, taskId);
			tasks.setTaskName(task.getTaskName());
			tasks.setTaskDescription(task.getTaskDescription());
			tasks.setPriority(task.getPriority());
			tasks.setComplitionDate(task.getComplitionDate());
			
		
		
		return tasks;

	}

	public Tasks changeTaskStatus(int userId,int taskId, Tasks task) {
		Tasks findtask = entityManager.find(Tasks.class, taskId);
		findtask.setTaskStatus(task.isTaskStatus());
		entityManager.merge(findtask);
		return findtask;
	}

}
