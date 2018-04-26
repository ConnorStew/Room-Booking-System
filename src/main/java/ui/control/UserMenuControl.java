package ui.control;

import java.util.Optional;

import database.AccountDAO;
import database.BookingDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class UserMenuControl {
	
	/** Username label. */
	@FXML private Label lblUsername;
	
	/** Amount of active bookings label. */
	@FXML private Label lblActive;
	
	@FXML
	/**
	 * Initialise the label text.
	 */
	private void initialize() {
		lblUsername.setText(Utilities.getSingedInAccount().getUsername());
		updateBookingLabel();
	}
	
	@FXML
	/**
	 * Opens a new password dialog to allow the user to set a new password.
	 */
	private void setPassword() {
		TextInputDialog a = new TextInputDialog();
		a.setTitle("Set password");
		a.setContentText("Choose a new password.");
		
		Optional<String> input = a.showAndWait();
		
		if (input.isPresent())
			new AccountDAO().setPassword(Utilities.getSingedInAccount().getID(), input.get());
	}
	
	@FXML
	private void viewBookings() {
		Utilities.openWindow("booking.fxml", "booking.css", "Bookings");
	}
	
	/**
	 * Updates the booking label.
	 */
	private void updateBookingLabel() {
		lblActive.setText("Active bookings: " + new BookingDAO().getNumOfBookings(Utilities.getSingedInAccount().getID()));
	}
	
}
