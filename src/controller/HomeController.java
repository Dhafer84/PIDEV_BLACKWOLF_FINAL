package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Jeu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.AuthService;
import utils.SceneManager;

public class HomeController implements Initializable {

	@FXML
	private Button button_nav_accueil;

	@FXML
	private Button button_nav_bib;

	@FXML
	private Button button_nav_forum;

	@FXML
	private Button button_nav_notif;

	@FXML
	private HBox cardgame;

	@FXML
	private Menu customMenu;

	@FXML
	private Menu customMenu1;

	@FXML
	private MenuItem item_annonce;

	@FXML
	private MenuItem item_annonce1;

	@FXML
	private MenuItem item_compte;

	@FXML
	private MenuItem item_event;

	@FXML
	private MenuItem item_event2;

	@FXML
	private MenuItem item_jeu1;

	@FXML
	private MenuItem item_logout;

	@FXML
	private MenuItem item_parametre;

	@FXML
	private Label label_welcome;

	@FXML
	private MenuBar menubar;

	@FXML
	private MenuBar menubar1;
	@FXML
	private ImageView view_1;

	@FXML
	private ImageView view_2;

	@FXML
	private ImageView view_3;

	@FXML
	private ImageView view_4;

	@FXML
	private ImageView view_5;
	
	@FXML
	private ImageView profile_image;

    

	@FXML
	private ComboBox<String> dropdownmenu;
	@FXML
	private ComboBox<String> dropdownmenu1;
	
	ObservableList<Jeu> list;

	public ObservableList<Jeu> data = FXCollections.observableArrayList();
	int index = -1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label_welcome.setText(AuthService.loggedInUser.getFirstName());
		if(AuthService.loggedInUser.getUserImage().equals("")) {
			AuthService.loggedInUser.setUserImage( "avatar.png");
		}
		String imageSource = "http://localhost:3030/api/v1/users/image/"+AuthService.loggedInUser.getUserImage();
		profile_image.setImage(new Image(imageSource));
		
		String imageSource2 = "http://localhost:3030/api/v1/games/image/jeu-4-1657573799613.jpeg";
		view_1.setImage(new Image(imageSource2));

		// Hide functionality for simple users
		if (!AuthService.loggedInUser.getRole().equals("admin")) {
						item_compte.setVisible(false);
		}
		

		customMenu.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String targetElement = event.getTarget().toString();
				if (targetElement.contains("item_annonce")) {
					return;
				}
				if (targetElement.contains("item_event")) {

					return;
				}
				if (targetElement.contains("item_compte")) {
					SceneManager.changeSceneForMenuBar((Stage) menubar.getScene().getWindow(), "GererCompte.fxml",
							"Login");
					return;
				}
				if (targetElement.contains("item_parametre")) {
					SceneManager.changeSceneForMenuBar((Stage) menubar.getScene().getWindow(), "Parametre.fxml",
							"Login");
					return;
				}
				if (targetElement.contains("item_logout")) {
					AuthService.logout();
					SceneManager.changeSceneForMenuBar((Stage) menubar.getScene().getWindow(), "login.fxml", "Login");
					return;
				}

				if (targetElement.contains("item_annonce1")) {

					return;
				}
				if (targetElement.contains("item_event2")) {

					return;
				}
				if (targetElement.contains("item_jeu1")) {
					System.out.println("test");
					SceneManager.changeSceneForMenuBar1((Stage) menubar1.getScene().getWindow(), "AddJeu.fxml",
							"Login");
					return;
				}
			}
		});
		customMenu1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String targetElement = event.getTarget().toString();

				if (targetElement.contains("item_annonce1")) {
					System.out.println("test2");
					SceneManager.changeSceneForMenuBar1((Stage) menubar1.getScene().getWindow(), "Annonce.fxml",
							"Login");
					return;
				}
				if (targetElement.contains("item_event2")) {
					System.out.println("test2");
					SceneManager.changeSceneForMenuBar1((Stage) menubar1.getScene().getWindow(), "Event.fxml",
							"Login");
					return;
				}
				if (targetElement.contains("item_jeu1")) {
					System.out.println("test");
					SceneManager.changeSceneForMenuBar1((Stage) menubar1.getScene().getWindow(), "AddJeu.fxml",
							"Login");
					return;
				}
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
				SceneManager.changeScene(event, "bib.fxml", "Bibliotheque", null);
			}
		});

		button_nav_notif.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "EventListAccueil.fxml", "EventList", null);
			}
		});
		

	}
	

	// Setting user info passed from login screen
	public void setUserInformation(int id_user, String first_name, String last_name, String email) {
		label_welcome.setText(last_name);
		System.out.println(AuthService.loggedInUser);
	}
}
