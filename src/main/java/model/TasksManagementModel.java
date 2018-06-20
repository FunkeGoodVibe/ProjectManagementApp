package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class TasksManagementModel {
	Connection conection;



	public TasksManagementModel() {
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

	public POJOTask getTaskDetails(String name) throws SQLException {
		String query = "SELECT * from tasks WHERE nameOfTask = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		POJOTask task = null;
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, name);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				task = new POJOTask(resultSet.getString("nameOfTask"),
									resultSet.getString("priority"),
						            resultSet.getString("namesOfEmployees"),
								    resultSet.getDate("startDate"),
									resultSet.getDate("endDate"),
									resultSet.getInt("stage"),
									resultSet.getInt("effortEstimate"));
			}
			
		} catch (Exception ex) {
			System.out.println("not working here  ");
			ex.printStackTrace();

		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return task;
	}
	
	public ObservableList<String> populateListView(int stage) throws SQLException {

			
		ObservableList<String> listToDo = FXCollections.observableArrayList();
		ObservableList<String> listInProgress = FXCollections.observableArrayList();
		ObservableList<String> listDone = FXCollections.observableArrayList();


		String query = "SELECT nameOfTask from tasks WHERE stage = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setInt(1, stage);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				switch (stage)
				{
					case 1: 
						listToDo.addAll(resultSet.getString("nameOfTask"));
						break;
					case 2: 
						listInProgress.addAll(resultSet.getString("nameOfTask"));
						break;
					case 3: 
						listDone.addAll(resultSet.getString("nameOfTask"));
						break;
				} 
				
			}
			
		} catch (Exception ex) {
			System.out.println("not working here");
			ex.printStackTrace();

		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		
		switch(stage)
		{
			case 1: 
				return listToDo;
			case 2: 
				return listInProgress;
			case 3: 
			 	return listDone;
		} 

		return listToDo;
				

	}


	
}
