package ui;

import java.text.SimpleDateFormat;
import data.Booking;
import database.AccountDAO;
import database.RoomDAO;
import javafx.beans.property.SimpleStringProperty;

/**
 * This represents a booking to be displayed in the booking table.
 * @author Connor Stewart
 */
public class BookingEntry {
	
	/** The username of the account that booked the room. */
	private SimpleStringProperty username;
	
	/** The name of the room that was booked. */
	private SimpleStringProperty roomName;
	
	/** The date that the room was booked for. */
	private SimpleStringProperty startDate;
	
	/** The time the room is booked for. */
	private SimpleStringProperty startTime;
	
	/** The date the room booking ends on. */
	private SimpleStringProperty endDate;
	
	/** The time the room booking ends on. */
	private SimpleStringProperty endTime;
	
	public BookingEntry(Booking b) {
		username = new SimpleStringProperty(new AccountDAO().getUsername(b.getBookingID()));
		roomName = new SimpleStringProperty(new RoomDAO().getName(b.getBookingID()));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hha");
		
		startDate = new SimpleStringProperty(dateFormat.format(b.getStartDate()));
		startTime = new SimpleStringProperty(timeFormat.format(b.getStartDate()));
		endDate = new SimpleStringProperty(dateFormat.format(b.getEndDate()));
		endTime = new SimpleStringProperty(timeFormat.format(b.getEndDate()));
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username.get();
	}

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName.get();
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate.get();
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime.get();
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate.get();
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime.get();
	}

}
