package main.java.model;

import java.sql.Date;

public class POJOTask {
	String taskName;
	String taskPriority;
	String nameOfEmployees;
	Date startingDate;
	Date endingDate;
	int stage;
	int effortEstimate;

	public POJOTask(String taskName, String taskPriority, String nameOfEmployees, Date startingDate, Date endingDate, int stage, int effortEstimate) {
		this.taskName = taskName;
		this.taskPriority = taskPriority;
		this.nameOfEmployees = nameOfEmployees;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.stage = stage;
		this.effortEstimate = effortEstimate;
	}

	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskPriority() {
		return taskPriority;
	}
	
	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	
	public String getNameOfEmployees() {
		return nameOfEmployees;
	}
	
	public void setNameOfEmployees(String nameOfEmployees) {
		this.nameOfEmployees = nameOfEmployees;
	}
	
	public Date getStartingDate() {
		return startingDate;
	}
	
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	
	public Date getEndingDate() {
		return endingDate;
	}
	
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	
	public int getStage() {
		return stage;
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getEffortEstimate() {
		return effortEstimate;
	}

	public void setEffortEstimate(int effortEstimate) {
		this.effortEstimate = effortEstimate;
	}
}
