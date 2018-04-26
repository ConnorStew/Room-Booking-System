package ui.control;

import data.Account;
import data.AccountType;
import database.AccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class is used to provide functionality to the Login FXML file.
 * @author Connor Stewart
 */
public class LoginControl {

	/** Username input. */
	@FXML TextField txtUser;

	/** Password input. */
	@FXML PasswordField txtPassword;

	/** The login button. */
	@FXML Button btnLogin;
	
	@FXML
	private void initialize() {
		btnLogin.requestFocus();
	}
	
	/**
	 * Validates a user's login credentials.
	 */
	@FXML
	private void validateLogin() {
		Account validAccount = new AccountDAO().getAccount(txtUser.getText().trim(), txtPassword.getText().trim());
		
		if (validAccount != null) {
			if (validAccount.isLocked()) {
				new Alert(AlertType.ERROR, "This account is locked, please contact an admin.").show();
			} else {
				Utilities.setSignedInAccount(validAccount);
				
				if (validAccount.getType().equals(AccountType.User))
					Utilities.setScene("UserScreen.fxml", "user.css", "User");
				
				if (validAccount.getType().equals(AccountType.Admin))
					Utilities.setScene("UserScreen.fxml", "user.css", "User");
			}
		} else {
			new Alert(AlertType.ERROR, "Incorrect username or password.").show();
		}
	}
	
	/**
	 * Checks if the enter key has been pressed and attempts to log the user in.
	 * @param event the key press event
	 */
	@FXML
	private void checkKey(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER))
			validateLogin();
	}

}
