package com.ejb.restapi.models.dao;

import java.time.LocalDate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.restapi.exception.TodoServiceException;
import com.ejb.restapi.models.User;

@Stateless
public class UserDAO {

	@PersistenceContext(unitName="todoapplication-pu")
	private EntityManager entityManager;
	
	public User addUser(User user) {
		System.out.println(user);
		if(user.getEmail().equals("vakudemukesh@gmail.com")) {
			user.setRole("admin");
			user.setActivationStatus(true);
		}else {
			user.setRole("user");
			user.setActivationStatus(false);
		}
		entityManager.persist(user);
		return user;
	}
	public User getUser(int  userId) throws TodoServiceException{
		User user = entityManager.find(User.class, userId);
		if(user == null) {
			throw new  TodoServiceException("user Not found");
		}else
		return user;
	}
	public List<User> getAllUser(){
		return entityManager.createNamedQuery("User.findAll",User.class).getResultList();
	}
	
	public User getUserByEmailId(User user) {
		User isUser = null;
		boolean flag = false;
		List<User> users = getAllUser();
		for(User userObj:users) {
			if(userObj.getEmail().equalsIgnoreCase(user.getEmail())) {
				isUser = userObj;
				flag = true;
				break;
			}
		}
		
		
		return flag ?isUser:null;
	}
	public User authentication(User user) {
		User isUser = null;
		boolean flag = false;
		List<User> users = getAllUser();
		for(User userObj:users) {
			if(userObj.getEmail().equals(user.getEmail()) && userObj.getPassword().equals(user.getPassword())) {
				isUser = userObj;
				flag = true;
				break;
			}
		}
		return flag ?isUser:null;
	}
	public User updateUserActivationStatus(User user) {
		
//		User userObj =  entityManager.find(User.class,user.getUser_id());
//		System.out.println(user);
//		if(user.isActivationStatus())
//			userObj.setActivationStatus(false);
//		else
//			userObj.setActivationStatus(true);
		if(user.isActivationStatus())
			user.setActivationDate(LocalDate.now());
		entityManager.merge(user);
		return user;
	}
	
}
