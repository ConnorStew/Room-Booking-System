package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import data.Account;
import data.AccountType;

/**
 * Stores functions to access accounts in the database.
 * @author Connor Stewart
 */
public class AccountDAO {
	
	/**
	 * Gets an account with a matching username & password combination.
	 * @param username the accounts username
	 * @param password the accounts password
	 * @return the account if one is found
	 */
	public Account getAccount(String username, String password) {
		ResultSet results = DatabaseConnectionManager.executeSQLQuery("SELECT id, locked, account_type  FROM accounts "
				+ "WHERE accounts.username = '" + username + "' AND accounts.account_password = '" + password + "';");
		
		Account account = null;
		
		try {
			while (results.next()) {
				 account = new Account(
						 results.getInt("id"),
						 username, 
						 password, 
						 results.getBoolean("locked"), 
						 AccountType.parseTypeString(results.getString("account_type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DatabaseConnectionManager.disconnect();
		
		return account;
	}
	
	/**
	 * Gets the username of an account from a booking.
	 * @param bookingID the bookings id
	 * @return the username of the account the booking belongs to
	 */
	public String getUsername(int bookingID) {
		ResultSet results = DatabaseConnectionManager.executeSQLQuery("SELECT accounts.username FROM account_bookings "
				+ "INNER JOIN bookings "
				+ "ON account_bookings.booking_id = bookings.id "
				+ "INNER JOIN accounts "
				+ "ON account_bookings.account_id = accounts.id "
				+ "WHERE bookings.id = '" + bookingID + "';");
		
		String username = "Unknown";
		
		try {
			results.next();
			username = results.getString("accounts.username");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DatabaseConnectionManager.disconnect();
		
		return username;
	}
	
	/**
	 * Adds an account to the database.
	 * @param toAdd the account to add
	 */
	public void addAccount(String username, String password, AccountType type, boolean locked) {
		DatabaseConnectionManager.executeSQLUpdate("INSERT INTO accounts "
				+ "(username, account_password, account_type, locked) "
				+ "VALUES "
				+ "('" + username + "', '" + password + "', '" + type + "', " + locked + ");");
	}
	
	/**
	 * Removes an account from the database.
	 * @param accountID the accounts database ID
	 */
	public void removeAccount(int accountID) {
		DatabaseConnectionManager.executeSQLUpdate("DELETE FROM accounts WHERE id = '" + accountID + "';");
	}

	/**
	 * Sets the password for an account.
	 * @param accountID the accounts database ID
	 * @param newPassword the new password
	 */
	public void setPassword(int accountID, String newPassword) {
		DatabaseConnectionManager.executeSQLUpdate("UPDATE accounts "
				+ "SET account_password = '" + newPassword + "' "
				+ "WHERE id = '" + accountID + "';");
	}


}
