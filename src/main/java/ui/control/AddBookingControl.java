package ui.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AddBookingControl {

    @FXML
    ComboBox<Integer> startHours;

    @FXML
    private void initialize() {
        ObservableList<Integer> hours = FXCollections.observableArrayList();

        for (int i = 1; i < 13; i++)
            hours.add(i);

        startHours.setItems(hours);
    }

    /**
     * 		try {
     * 			new BookingDAO().addBooking(BookingDAO.dateFormat.parse("2001-12-12 00:00:00"), BookingDAO.dateFormat.parse("2001-12-13 00:00:00"), 2, Utilities.getSignedInID());
     *                } catch (ParseException e) {
     * 			e.printStackTrace();
     *        }
     */
}
