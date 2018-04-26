package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains methods to interact with rooms in the database.
 * @author Connor Stewart
 */
public class RoomDAO {

	public String getName(int bookingID) {
		ResultSet results = DatabaseConnectionManager.executeSQLQuery("SELECT rooms.name FROM account_bookings "
				+ "INNER JOIN rooms "
				+ "ON account_bookings.room_id = rooms.id "
				+ "WHERE booking_id = '" + bookingID + "';");
		
		String name = "Unknown";
		
		try {
			results.next();
			name = results.getString("rooms.name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DatabaseConnectionManager.disconnect();
		
		return name;
	}

}
