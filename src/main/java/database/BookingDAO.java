package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.Booking;

/**
 * This class contains methods for retrieving bookings from the database.
 * @author Connor Stewart
 */
public class BookingDAO {
	
	/** The format that dates are stored in within the database. */
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * Get the number of bookings for a user.
	 * @param accountID the account to check the number of bookings for
	 * @return the amount of bookings
	 */
	public int getNumOfBookings(int accountID) {

		ResultSet results = DatabaseConnectionManager.executeSQLQuery("SELECT COUNT(*) FROM account_bookings "
				+ "INNER JOIN bookings "
				+ "ON account_bookings.booking_id = bookings.id "
				+ "INNER JOIN accounts "
				+ "ON account_bookings.account_id = accounts.id "
				+ "WHERE accounts.id = '" + accountID + "';");
		
		//return zero if results cannot be accessed
		int bookingNum = 0;

		try {
			results.next();
			bookingNum = results.getInt("COUNT(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

		DatabaseConnectionManager.disconnect();
	
		return bookingNum;
	}
	
	/**
	 * Gets the bookings for an account.
	 * @param accountID the id of the account
	 * @return an ArrayList of the accounts bookings
	 */
	public ArrayList<Booking> getBookings(int accountID) {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		ResultSet results = DatabaseConnectionManager.executeSQLQuery(
				"SELECT booking_id, start_date_time, end_date_time FROM account_bookings "
				+ "INNER JOIN bookings "
				+ "ON account_bookings.booking_id = bookings.id "
				+ "INNER JOIN accounts "
				+ "ON account_bookings.account_id = accounts.id "
				+ "WHERE account_id = '" + accountID + "';");
		
		try {
			while (results.next()) {
				Date startDate = dateFormat.parse(results.getString("start_date_time"));
				Date endDate = dateFormat.parse(results.getString("end_date_time"));
				int bookingID = results.getInt("booking_id");
				
				bookings.add(new Booking(bookingID, startDate, endDate));
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		DatabaseConnectionManager.disconnect();
		
		return bookings;
	}

	/**
	 * Gets all the bookings in the database.
	 * @return an ArrayList of bookings
	 */
	public ArrayList<Booking> getAllBookings() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		ResultSet results = DatabaseConnectionManager.executeSQLQuery(
				"SELECT booking_id, start_date_time, end_date_time, room_id FROM account_bookings "
				+ "INNER JOIN bookings "
				+ "ON account_bookings.booking_id = bookings.id "
				+ "INNER JOIN accounts "
				+ "ON account_bookings.account_id = accounts.id "
				+ "INNER JOIN rooms "
				+ "ON account_bookings.room_id = rooms.id;");
		
		try {
			while (results.next()) {
				Date startDate = dateFormat.parse(results.getString("start_date_time"));
				Date endDate = dateFormat.parse(results.getString("end_date_time"));
				int bookingID = results.getInt("booking_id");
				
				bookings.add(new Booking(bookingID, startDate, endDate));
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		DatabaseConnectionManager.disconnect();
		
		return bookings;
	}
	
	public void addBooking(Date startDate, Date endDate, int roomID, int accountID) {
		String formattedStartDate = dateFormat.format(startDate);
		String formattedEndDate = dateFormat.format(endDate);
		
		//add the booking
		DatabaseConnectionManager.executeSQLUpdate("INSERT INTO bookings "
				+ "(start_date_time, end_date_time) "
				+ "VALUES "
				+ "('" + formattedStartDate + "', '" + formattedEndDate + "');");
		
		//get the bookings id
		ResultSet results = DatabaseConnectionManager.executeSQLQuery(
				"SELECT id, start_date_time, end_date_time "
				+ "FROM bookings "
				+ "WHERE start_date_time='" + formattedStartDate + "' AND end_date_time='" + formattedEndDate + "';");
		
		int bookingID = 0;

		try {
			results.next();
			bookingID = results.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement s = DatabaseConnectionManager.getStatement();

		try {
			s.addBatch("SET FOREIGN_KEY_CHECKS=0;");
			s.addBatch("INSERT INTO account_bookings "
					+ "(account_id, booking_id, room_id) "
					+ "VALUES "
					+ "(" + accountID + ", " + bookingID + ", " + roomID + ");");
			s.addBatch("SET FOREIGN_KEY_CHECKS=1;");
			s.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DatabaseConnectionManager.disconnect();
		
		
	}

}
