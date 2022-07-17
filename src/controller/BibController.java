
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import service.AuthService;
import utils.SceneManager;

public class BibController implements Initializable {
	
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
	
	@FXML
	private Button getmetheuser;
	@FXML 
	private Text label_content;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
label_welcome.setText(AuthService.loggedInUser.getFirstName());
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
				SceneManager.changeScene(event, "home.fxml", "Forum", null);
			}
		});
		
		button_nav_notif.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "EventListAccueil.fxml", "EventList", null);
			}
		});
		
		button_nav_forum.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "forum.fxml", "Forum", null);
			}
		});
		
		button_nav_bib.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "AddJeu.fxml", "Jeu", null);
			}
		});
		
		getmetheuser.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				label_content.setText(AuthService.loggedInUser.toString());
			}
		});

	}
	
	// Setting user info passed from login screen
	public void setUserInformation(int id_user, String first_name, String last_name, String email) {
		label_welcome.setText(last_name);
		System.out.println(AuthService.loggedInUser);
	}
}
