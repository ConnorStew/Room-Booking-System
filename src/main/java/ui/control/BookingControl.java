package ui.control;

import data.Booking;
import database.BookingDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.BookingEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class handles control of the booking window.
 * @author Connor Stewart
 */
public class BookingControl {
	
	/** The UI element that holds the bookings. */
	@FXML private TableView<BookingEntry> tblBookings;
	
	/** Buttons to interact with bookings. */
	@FXML private Button btnModify, btnRemove;
	
	@FXML
	private void initialize() {
		//show usernames in the table if the user is an admin
		if (Utilities.userIsAdmin())
			addColumn("Username", "username");
		
		//add the default columns
		addColumn("Room Name", "roomName");
		addColumn("Start Date", "startDate");
		addColumn("Start Time", "startTime");
		addColumn("End Date", "endDate");
		addColumn("End Time", "endTime");
		
		//initialise the data in the table
	    ObservableList<BookingEntry> data = FXCollections.observableArrayList();
	    
	    //the bookings to be displayed in the table
	    ArrayList<Booking> toDisplay;
	    
	    //display all bookings if the user is an admin
	    if (Utilities.userIsAdmin())
	    	toDisplay = new BookingDAO().getAllBookings();
	    else //display just that users bookings if the user in not an admin
	    	toDisplay = new BookingDAO().getBookings(Utilities.getSignedInID());
	    
	    //add the bookings to tblBookings' data
		for (Booking b : toDisplay)
			data.add(new BookingEntry(b));

		//active the two buttons when something has been selected
		tblBookings.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnModify.setDisable(false);
			btnRemove.setDisable(false);
		});
		
		//add the data to the table
		tblBookings.setItems(data);
		
		//resize the columns to the width of the TableView
		tblBookings.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * Adds a column to the table.
	 * @param columnName the columns name
	 * @param fieldName the field the column should represent
	 */
	private void addColumn(String columnName, String fieldName) {
		TableColumn<BookingEntry, String> column = new TableColumn<>(columnName);

		column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
		
		tblBookings.getColumns().add(column);
	}
	
	@FXML
	private void addBooking() {
		Utilities.openWindow("add_booking.fxml", "add_booking.css", "New Booking");
	}

}
