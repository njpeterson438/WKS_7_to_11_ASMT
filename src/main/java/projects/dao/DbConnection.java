package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;


// Below are the constants for database connection details
public class DbConnection {
		private static final String HOST = "localhost";
		private static final String PASSWORD = "projects";
		private static final String SCHEMA = "projects";
		private static final String USER = "projects";
		private static final int PORT = 3306;

// Method to get a database connection
public static Connection getConnection() {
        
        // Construct the URI using your constants
        String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false",
        			HOST, PORT, SCHEMA, USER, PASSWORD);

        try {
            // Obtain the connection using the URI and credentials
            Connection connection = DriverManager.getConnection(uri);
            System.out.println("Connection successful!");

            return connection;
        } catch (SQLException e) {
            // Print an error message and throw a DbException if connection fails
            System.err.println("Connection failed: " + e.getMessage());
            throw new DbException("Unable to connect to the database.");
        }
    }
	
}
