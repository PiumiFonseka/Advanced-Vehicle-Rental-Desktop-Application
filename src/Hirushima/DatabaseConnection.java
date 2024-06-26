package Hirushima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//JDBC URL,username and password of MYSQL server
	private static final String URL= "jdbc:mysql://localhost:3306/vassenterprises";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL,USER,PASSWORD);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}


