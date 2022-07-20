/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Event;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.AuthService;
import service.EventDAOImp;
import utils.SceneManager;

/**
 * FXML Controller class
 *
 * @author user
 */
public class gererEventController implements Initializable {

	/**
	 * Initializes the controller class.
	 * 
	 */

	@FXML
	private TableColumn<Event, Event> action;

	@FXML
	private TableColumn<Event, String> adresse;

	@FXML
	private Button button_logout;

	@FXML
	private Button button_nav_accueil;
	@FXML
	private TextField filterFiled;
	@FXML
	private Button button_nav_bib;

	@FXML
	private Button button_nav_forum;

	@FXML
	private Button button_nav_notif;

	@FXML
	private TableColumn<Event, Date> dateDebut;

	@FXML
	private TableColumn<Event, Date> dateFin;

	@FXML
	private Label label_welcome;

	@FXML
	private TableColumn<Event, String> nameEvent;

	@FXML
	private TableColumn<Event, Integer> nbMax;

	@FXML
	private TableColumn<Event, String> tancheAge;

	@FXML
	private TextField despEvent;
	@FXML
	private TableView<Event> tableEventListe;
	Event eventselected;

	@FXML
	private Menu customMenu1;

	@FXML
	private MenuItem item_event2;

	@FXML
	private MenuItem item_jeu1;

	@FXML
	private MenuItem item_annonce1;

	@FXML
	private MenuBar menubar1;

	@FXML
	private ComboBox<String> dropdownmenu1;

	@FXML
	private ImageView profile_image;

	ObservableList<Event> listEvent;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		label_welcome.setText(AuthService.loggedInUser.getFirstName());
		if (AuthService.loggedInUser.getUserImage().equals("")) {
			AuthService.loggedInUser.setUserImage("avatar.png");
		}
		String imageSource = "http://localhost:3030/api/v1/users/image/" + AuthService.loggedInUser.getUserImage();
		profile_image.setImage(new Image(imageSource));
		button_logout.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AuthService.logout();
				SceneManager.changeScene(event, "login.fxml", "Login", null);
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
				SceneManager.changeScene(event, "bib.fxml", "Bibliotheque", null);
			}
		});

		 //add melek
        EventDAOImp e1 = new EventDAOImp();
        nameEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("eventStartDate"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("eventEndDate"));
        tancheAge.setCellValueFactory(new PropertyValueFactory<>("eventAgeRange"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("eventAddress"));
           nbMax.setCellValueFactory(new PropertyValueFactory<>("eventMaxNumberParticipant"));

        List<Event> list = new ArrayList<>();
        try {
            list = e1.getAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        listEvent = FXCollections.observableArrayList(list);
        System.out.println(list);
//idevent.setVisible(false);
      nameEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("eventStartDate"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("eventEndDate"));
        tancheAge.setCellValueFactory(new PropertyValueFactory<>("eventAgeRange"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("eventAddress"));

           nbMax.setCellValueFactory(new PropertyValueFactory<>("eventMaxNumberParticipant"));

        tableEventListe.setItems(FXCollections.observableArrayList(listEvent));

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Event> filteredData = new FilteredList<>(listEvent, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getEventName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (event.getEventAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(event.getEventAgeRange()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        }
        );

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty()
                .bind(tableEventListe.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableEventListe.setItems(sortedData);

        Callback<TableColumn<Event, Event>, TableCell<Event, Event>> cellFactory;
           cellFactory = new Callback<TableColumn<Event, Event>, TableCell<Event, Event>>() {
               @Override
               public TableCell call(final TableColumn<Event, Event> param) {
                   final TableCell<Event, Event> cell;
                   cell = new TableCell<Event, Event>() {
                       
                       @Override
                       public void updateItem(Event item, boolean empty) {
                           super.updateItem(item, empty);
                           if (empty) {
                               setGraphic(null);
                               setText(null);
                           } else {
                               Button editBtn = new Button("valide");
                            
                              
                                   editBtn.setOnAction((ActionEvent event) -> {
                                       
                               


                               
                         despEvent.setText(eventselected.getEventDescription());
                                try {
                                    
                                    e1.update(eventselected);
                                    // passwordField.setText( eventselected.getPassword());
                                    System.out.println("selected"+ eventselected.getEventName()+ eventselected.getEventDescription());
                                } catch (SQLException ex) {
                                    Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                }



                    
                            
                                   }); 
                                   
                           
                         
                           
                           
                           HBox hb = new HBox();
                           hb.setSpacing(2);
                           hb.getChildren().addAll(editBtn);
                           setGraphic(hb);
                           setText(null);
                       }
                   };
                   };
                   return cell;
                   
               }
           };

        action.setCellFactory(cellFactory);
    }    
    
}