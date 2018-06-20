package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

	Connection conection;

	public LoginModel() {
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

	public boolean isLogin(String user, String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM manager_login WHERE username = ? AND password = ?";
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}

	public void isRegister(String user, String password) throws SQLException {
		String query = "INSERT INTO manager_login(username, password) VALUES(?,?)";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			preparedStatement.close();
		}
	}
}
