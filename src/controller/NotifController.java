
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import service.AuthService;
import utils.SceneManager;

public class NotifController implements Initializable {
	
	@FXML
	private Button button_logout;
	
	@FXML
	private Label label_welcome;
	
	@FXML
	private Button button_nav_accueil;
	
	@FXML
	private Button button_nav_bib;
	
	@FXML
	private Button button_nav_forum;
	
	@FXML
	private Button button_nav_notif;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		button_logout.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AuthService.logout();
				SceneManager.changeScene(event, "login.fxml", "Login", null);
			}
		});
		
		button_nav_accueil.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "home.fxml", "Home", null);
			}
		});
		
		button_nav_bib.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "bib.fxml", "Bibliotheque", null);
			}
		});
		
		button_nav_forum.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "forum.fxml", "Forum", null);
			}
		});

	}
	
	// Setting user info passed from login screen
	public void setUserInformation(int id_user, String first_name, String last_name, String email) {
		label_welcome.setText(last_name);
		System.out.println(AuthService.loggedInUser);
	}
}
