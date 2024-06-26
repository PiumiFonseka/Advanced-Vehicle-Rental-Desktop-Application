package Hiruni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	// JDBC URL, username, and password for MySQL server
	private static final String URL = "jdbc:mysql://localhost:3306/vassenterprises"; // JDBC URL for the database
	private static final String USER = "root"; // Database username
	private static final String PASSWORD = ""; // Database password

	// Method to establish a database connection
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD); // Attempt to connect to the database
		} catch (SQLException e) {
			e.printStackTrace(); // Print any database connection errors
			return null; // Return null in case of connection failure
		}
	}

	// Method to close a database connection
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close(); // Close the database connection if it's not null
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print any errors that occur during the connection closing process
		}
	}

}
