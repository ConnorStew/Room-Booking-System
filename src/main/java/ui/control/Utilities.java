package ui.control;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import data.Account;
import data.AccountType;
import database.DatabaseConnectionManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class:
 * <ul>
 * 	<li>Launches the application.</li>
 * 	<li>Provides functions for other UI classes.</li>
 * 	<li>Stores the user who is signed in.</li>
 * </ul>
 * @author Connor Stewart
 */
public class Utilities extends Application {
	
	/** The stage for the application. */
	private static Stage currentStage;
	
	/** The account that is currently signed in. */
	private static Account signedIn;

	/**
	 * Launches the application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Utilities.currentStage = primaryStage;
		Utilities.currentStage.setResizable(false);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
            	DatabaseConnectionManager.closeDatabase();
            }
		});
		
		//show the login screen
		setScene("Login.fxml", "login.css", "Login");
	}

	/**
	 * Sets a new scene for the primary stage.
	 * @param fxmlFileLocation the FXML file to load
	 * @param cssFile the CSS file to load
	 * @param title the windows title
	 */
	static void setScene(String fxmlFileLocation, String cssFile, String title) {
		try {
			currentStage.setScene(FXMLLoader.load(Utilities.class.getResource("/fxml/" + fxmlFileLocation)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		currentStage.setTitle(title);
		currentStage.getScene().getStylesheets().add(Utilities.class.getResource("/css/" + cssFile).toExternalForm());
		currentStage.show();
	}
	
	/**
	 * Creates a new stage using the parameters.
	 * @param fxmlFileLocation the FXML file to load
	 * @param cssFile the CSS file to load
	 * @param title the windows title
	 */
	static void openWindow(String fxmlFileLocation, String cssFile, String title) {
		Stage stage = new Stage();
		try {
			stage.setScene(FXMLLoader.load(Utilities.class.getResource("/fxml/" + fxmlFileLocation)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stage.setTitle(title);
		stage.getScene().getStylesheets().add(Utilities.class.getResource("/css/" + cssFile).toExternalForm());
		stage.show();
	}
	
	/**
	 * @return the current signed in account
	 */
	static Account getSingedInAccount() {
		return signedIn;
	}

	/**
	 * Sets the current signed in account.
	 * @param account the account that has been signed in
	 */
	static void setSignedInAccount(Account account) {
		Utilities.signedIn = account;
	}

	/**
	 * Checks if the signed in user is an admin.
	 * @return whether the user is an admin
	 */
	static boolean userIsAdmin() {
		return signedIn.getType().equals(AccountType.Admin);
	}

	/**
	 * The users ID.
	 * @return the user's id
	 */
	static int getSignedInID() {
		return signedIn.getID();
	}
	
	
	
}
