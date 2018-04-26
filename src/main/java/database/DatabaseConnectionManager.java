package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class manages connecting to the highscores database.
 * @author Connor Stewart
 */
public class DatabaseConnectionManager {

	/** The connection to the database. */
	private static Connection conn;

	/** The mysqld process. */
	private static Process p;

	/** This class should be accessed statically. */
	private DatabaseConnectionManager() {}
	
	/**
	 * Connects to the mySQL server.
	 */
	private static void connect() {
		if (p == null) {
			ProcessBuilder pb = new ProcessBuilder("E:/Programs/MySQL/bin/mysqld.exe", "--console");
			
			try {
				p = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Started database.");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/room_booking?user=root&password=connor&useSSL=false");
			System.out.println("Established Database Connection!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Server offline.");
		}
	}

	/**
	 * Disconnect from the mySQL server.
	 */
	static void disconnect() {
		try {
			if (databaseOnline()) { //only disconnect if the database is online
				conn.close();
				System.out.println("Disconnected from Database.");
			} else {
				System.out.println("Error: Cannot disconnect from database.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if the database is reachable.
	 * @return whether the database can be reached
	 */
	private static boolean databaseOnline(){
		try {
			return (conn != null && conn.isValid(2));
		} catch (SQLException e) {
			System.out.println("Database offline.");
			return false;
		}
	}
	
	/**
	 * Executes an update on the SQL database.
	 * @param sqlCommand the SQL command to execute
	 * @return if the update executed successfully
	 */
	static boolean executeSQLUpdate(String sqlCommand) {
		try {
			connect();
			if (databaseOnline()) {
				Statement s = conn.createStatement();
				s.executeUpdate(sqlCommand);
				disconnect();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Executes a query on the SQL database.
	 * @param sqlCommand the SQL command to execute
	 * @return the result set from the query or null if an error occurred
	 */
	static ResultSet executeSQLQuery(String sqlCommand) {
		try {
			connect();
			if (databaseOnline()) {
				Statement s = conn.createStatement();
				ResultSet result = s.executeQuery(sqlCommand);
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Closes the database process.
	 */
	public static void closeDatabase() {
		p.destroy();
		System.out.println("Database shutdown.");
	}

	public static Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}