package main.java.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditTaskModel {
	Connection conection;

	public EditTaskModel() {
		conection = SqliteConnection.Connector();
		if (conection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}
	
	public boolean isDbConnected() {
		try {
			return !conection.isClosed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteTask(String taskName) throws SQLException {
		String query = "DELETE FROM tasks WHERE nameOfTask = ? " ;	
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, taskName);

			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
		} finally {
			preparedStatement.close();
		}
	}
	
	public void updateTask(String nameOfTask, String priority, String namesOfEmployees, Date startDate, Date endDate, String effortEstimate, String oldTaskName) throws SQLException {
		String query = "UPDATE tasks SET nameOfTask = ? , " 
									 + "priority = ? , " 
									 + "namesOfEmployees = ? , " 
									 + "startDate = ? , " 
									 + "endDate = ? , "
									 + "effortEstimate = ?"
									 + "WHERE nameOfTask = ? " ;	
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, nameOfTask);
			preparedStatement.setString(2, priority);
			preparedStatement.setString(3, namesOfEmployees);
			preparedStatement.setDate(4, startDate);
			preparedStatement.setDate(5, endDate);
			preparedStatement.setString(6, effortEstimate);
			preparedStatement.setString(7, oldTaskName);
			preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
		} finally {
			preparedStatement.close();
		}
	}
	
	public void goToNextStage(String taskName, int stage) throws SQLException {
		String query = "UPDATE tasks SET stage = ? WHERE nameOfTask = ?" ;	
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, stage);
			preparedStatement.setString(2, taskName);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			preparedStatement.close();
		}
	}
}
