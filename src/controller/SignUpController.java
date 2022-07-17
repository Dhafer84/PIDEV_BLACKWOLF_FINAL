package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.AuthService;
import utils.AlertModal;
import utils.SceneManager;

public class SignUpController implements Initializable {
	// FXML element selected by their ID's
	@FXML
	private TextField tf_firstname;

	@FXML
	private TextField tf_lastname;

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
		 * Sign up Action responsible for : 
		 * 1- form validation 
		 * 2-  calling the service:  AuthService.signUp
		 */
		button_signup.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tf_firstname.getText().trim().isEmpty() || tf_lastname.getText().trim().isEmpty()
						|| tf_email.getText().trim().isEmpty() || pf_password.getText().trim().isEmpty()) {
					AlertModal.showErrorAlert(null, "Please fill in all information to sign up!");
				} else {
					try {
						User newlyUser = new User.UserBuilder().firstName(tf_firstname.getText()).lastName(tf_lastname.getText()).email(tf_email.getText()).password(pf_password.getText()).build();

						if (AuthService.signUp(newlyUser) == false) {
							AlertModal.showErrorAlert(null, "User already exists!");
						} else {
							AlertModal.showInfoAlert("Account has been created!", "You can now login!");
							SceneManager.changeScene(event, "login.fxml", "log in!", null);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		/*
		 * 
		 * Navigate to the login screen
		 *
		 */
		button_login.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "login.fxml", "log in!", null);
			}
		});

	}

}
