package com.ejb.restapi.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
@NamedQueries(
{
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),

})
public class Tasks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int task_id;
	private String taskName,taskDescription,priority;
	private LocalDate taskCreationDate,complitionDate;
	private boolean taskStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonbTransient
	private User user;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Tasks() {
		
	}
	public Tasks(int task_id, String taskName, String taskDescription, String priority, LocalDate taskCreationDate,
			LocalDate complitionDate, boolean taskStatus) {
		super();
		this.task_id = task_id;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.priority = priority;
		this.taskCreationDate = taskCreationDate;
		this.complitionDate = complitionDate;
		this.taskStatus = taskStatus;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public LocalDate getTaskCreationDate() {
		return taskCreationDate;
	}
	public void setTaskCreationDate(LocalDate taskCreationDate) {
		this.taskCreationDate = taskCreationDate;
	}
	public LocalDate getComplitionDate() {
		return complitionDate;
	}
	public void setComplitionDate(LocalDate complitionDate) {
		this.complitionDate = complitionDate;
	}
	public boolean isTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Tasks [task_id=" + task_id + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", priority=" + priority + ", taskCreationDate=" + taskCreationDate + ", complitionDate="
				+ complitionDate + ", taskStatus=" + taskStatus + "]";
	}
	
	
}
