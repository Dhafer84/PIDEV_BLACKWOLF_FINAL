package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.AuthService;
import utils.AlertModal;
import utils.SceneManager;

public class LogInController implements Initializable {
	// FXML element selected by their ID's
	@FXML
	private TextField tf_email;

	@FXML
	private PasswordField pf_password;

	@FXML
	private Button button_signup;

	@FXML
	private Button button_login;

	// Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * 
		 * login Action responsible for : 1- form validation 2- calling the service:
		 * AuthService.logInUser
		 */
		button_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tf_email.getText().trim().isEmpty() || pf_password.getText().trim().isEmpty()) {
					AlertModal.showErrorAlert(null, "Please fill in all information to log in!");
				} else {
					try {
						if (!AuthService.logInUser(tf_email.getText(), pf_password.getText())) {
							AlertModal.showErrorAlert(null, "incorrect email or password!");
						} else {
							SceneManager.changeScene(event, "Home.fxml", "Home", AuthService.loggedInUser);
						}
					} catch (SQLException e) {
						AlertModal.showErrorAlert(null, "connection problem!");

						e.printStackTrace();
					}
				}
			}
		});

		/*
		 * 
		 * Navigate to the sign up screen
		 *
		 */
		button_signup.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "signUp.fxml", "sign up!", null);
			}
		});

	}
}
