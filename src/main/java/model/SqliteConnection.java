package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
	public static Connection Connector() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:ProjectDB.sqlite");
			return conn;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
