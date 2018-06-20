package main.java.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddTaskModel {

	Connection conection;

	public AddTaskModel() {
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

	public void postTaskInDatabase(String nameOfTask, String priority, String namesOfEmployees, Date startDate,
			Date endDate, int effortEstimate) throws SQLException {
		String query = "INSERT INTO tasks(nameOfTask, priority, namesOfEmployees, startDate, endDate, stage, effortEstimate) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, nameOfTask);
			preparedStatement.setString(2, priority);
			preparedStatement.setString(3, namesOfEmployees);
			preparedStatement.setDate(4, startDate);
			preparedStatement.setDate(5, endDate);
			preparedStatement.setInt(6, 1);
			preparedStatement.setInt(7, effortEstimate);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			preparedStatement.close();
		}
	}

}
