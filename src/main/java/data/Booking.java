package data;

import java.util.Date;

/**
 * This class is used to store information about a booking.
 * @author Connor Stewart
 */
public class Booking {
	
	/** The id of this booking. */
	private int bookingID;
	
	/** The date/time this booking is for. */
	private Date startDate;
	
	/** The date/time the booking ends on. */
	private Date endDate;

	public Booking(int bookingID, Date startDate, Date endDate) {
		this.bookingID = bookingID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * @return the bookingID
	 */
	public int getBookingID() {
		return bookingID;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
}
