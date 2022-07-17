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
import javafx.scene.control.DatePicker;
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
import utils.AlertModal;
import utils.SceneManager;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EventController implements Initializable {

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
    private TextField nameEvent;

    @FXML
    private TextField nb_participant;

    @FXML
    private TextField tanche_Age;

    @FXML
    private TextField nbReservation;

    @FXML
    private TextField despEvent;

    @FXML
    private DatePicker date_debut;

    @FXML
    private DatePicker date_Fin;

    @FXML
    private TextField adresseEvente;

    @FXML
    private Button ajoute_Event;
    @FXML
    private Button Ajouter;
    
              @FXML
    private Button UPDATE;
       

    @FXML
    private TableColumn<Event, String> TrancheAge;

    @FXML
    private TableColumn<Event, String> adresseEvent;

    @FXML
    private TableColumn<Event, Date> dateDebut;

    @FXML
    private TableColumn<Event, Date> dateFin;
    @FXML
	private ImageView profile_image;

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
    private TextField filterFiled;

    @FXML
    private TableColumn<Event, Event> action;
    @FXML
    private TableColumn<Event, String> eventName;
    @FXML

    private TableView<Event> TableEventList;
    Event eventselected;

    ObservableList<Event> listEvent;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	label_welcome.setText(AuthService.loggedInUser.getFirstName());
		if (AuthService.loggedInUser.getUserImage().equals("")) {
			AuthService.loggedInUser.setUserImage("avatar.png");
		}
		String imageSource = "http://localhost:3030/api/v1/users/image/" + AuthService.loggedInUser.getUserImage();
		profile_image.setImage(new Image(imageSource));

		
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

        // TODO
        //add Melek
        buildTableAndData();
    }

    private void buildTableAndData() {
        //add melek
        EventDAOImp e1 = new EventDAOImp();
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("eventStartDate"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("eventEndDate"));
        TrancheAge.setCellValueFactory(new PropertyValueFactory<>("eventAgeRange"));
        adresseEvent.setCellValueFactory(new PropertyValueFactory<>("eventAddress"));
        List<Event> list = new ArrayList<>();
           try {
               Integer user_id= AuthService.loggedInUser.getUserId();   
            list = e1.getAllByUserConncet(user_id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        listEvent = FXCollections.observableArrayList(list);
        System.out.println(list);

        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("eventStartDate"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("eventEndDate"));
        TrancheAge.setCellValueFactory(new PropertyValueFactory<>("eventAgeRange"));
        adresseEvent.setCellValueFactory(new PropertyValueFactory<>("eventAddress"));

        TableEventList.setItems(FXCollections.observableArrayList(listEvent));

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
                .bind(TableEventList.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TableEventList.setItems(sortedData);

        Callback<TableColumn<Event, Event>, TableCell<Event, Event>> cellFactory = new Callback<TableColumn<Event, Event>, TableCell<Event, Event>>() {
            @Override
            public TableCell call(final TableColumn<Event, Event> param) {
                final TableCell<Event, Event> cell = new TableCell<Event, Event>() {

                    @Override
                    public void updateItem(Event item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button editBtn = new Button("UPDATE");
                            final Button dltBtn = new Button("DELETE");
                            editBtn.setOnAction((ActionEvent event) -> {
                              //  saveButton.setText("UPDATE");
                              eventselected = getTableView().getItems().get(getIndex());
                                nameEvent.setText(eventselected.getEventName());

                                tanche_Age.setText(String.valueOf(eventselected.getEventAgeRange()));
                                adresseEvente.setText(eventselected.getEventAddress());

                                date_debut.setValue((eventselected.getEventStartDate()).toLocalDate());
                                date_Fin.setValue((eventselected.getEventEndDate()).toLocalDate());
                                nb_participant.setText(String.valueOf(eventselected.getEventMaxNumberParticipant()));
                                nbReservation.setText(String.valueOf(eventselected.getEventNumberReservation()));

                                despEvent.setText(eventselected.getEventDescription());
                                try {
                                    
                                    e1.update(eventselected);
                                    // passwordField.setText( eventselected.getPassword());
                                    System.out.println("selected"+ eventselected.getEventName()+ eventselected.getEventDescription());
                                } catch (SQLException ex) {
                                    Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            });
                            dltBtn.setOnAction(event -> {
                                try {
                                    eventselected = getTableView().getItems().get(getIndex());

                                    System.out.println("aliiiii" + eventselected);
                                    e1.delete(eventselected);
Integer user_id= AuthService.loggedInUser.getUserId();             
TableEventList.setItems(FXCollections.observableArrayList(e1.getAllByUserConncet(user_id)));

                                    AlertModal.showErrorAlert(null, "your event is delete!");

                                } catch (SQLException ex) {
                                    Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            });

                            HBox hb = new HBox();
                            hb.setSpacing(2);
                            hb.getChildren().addAll(editBtn, dltBtn);
                            setGraphic(hb);
                            setText(null);
                        }
                    }
                };
                return cell;

            }
        };

        action.setCellFactory(cellFactory);

        //addd
        ajoute_Event.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameEvent.getText().trim().isEmpty() || nb_participant.getText().trim().isEmpty() || despEvent.getText().trim().isEmpty()
                        || tanche_Age.getText().trim().isEmpty() || nbReservation.getText().trim().isEmpty() || adresseEvente.getText().trim().isEmpty()) {
                    AlertModal.showErrorAlert(null, "Please fill in all information to add event!");
                }   if ( !nb_participant.getText().matches("[0-9]+")) {
                    AlertModal.showErrorAlert("nombre de participant" , "Please must be number !");
                    
                }  if ( !nbReservation.getText().matches("[0-9]+")) {
                    AlertModal.showErrorAlert("nombre de Reservation" , "Please must be number !");
                
                }if ( !tanche_Age.getText().matches("[0-9]+")) {
                    AlertModal.showErrorAlert("tranche d'age" , "Please must be number !");
                
                }  
                
                
                
                if (Date.valueOf(date_debut.getValue()).after(Date.valueOf(date_Fin.getValue()))) {
                    AlertModal.showErrorAlert("Date Debut must be > date fin" , "Date Debut must be > date fin !");
                       date_debut.setValue(null);
                        date_Fin.setValue(null);
                }
                
                
                
                else {
                    {
                        try {
                            Event e;
                            e = new Event.EventBuilder().eventName(nameEvent.getText()).eventStartDate(Date.valueOf(date_debut.getValue())).eventEndDate(Date.valueOf(date_Fin.getValue())).eventAgeRange(Integer.parseInt(tanche_Age.getText())).eventAddress(adresseEvente.getText())
                                    .eventMaxNumberParticipant(Integer.parseInt(nb_participant.getText())).eventNumberReservation(Integer.parseInt(nbReservation.getText())).eventDescription(despEvent.getText()).userId(AuthService.loggedInUser.getUserId()).build();

                            EventDAOImp eventService = new EventDAOImp();

                            eventService.insert(e);
                            Integer user_id= AuthService.loggedInUser.getUserId();             

                            TableEventList.setItems(FXCollections.observableArrayList(e1.getAllByUserConncet(user_id)));


                        } catch (SQLException e) {
                            e.printStackTrace();

                        }

                        AlertModal.showInfoAlert("! evenement ajouté avec succès ", "   evenement ajouté avec succès!");

                        nameEvent.setText(null);
                        date_debut.setValue(null);
                        date_Fin.setValue(null);
                        tanche_Age.setText(null);
                        adresseEvente.setText(null);
                        nb_participant.setText(null);
                        nbReservation.setText(null);
                        despEvent.setText(null);

                    }
                }
            }

        });
        
        
        ////update 
        
         UPDATE.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
                    
                        try {
                            Event e;
                            e = new Event(eventselected.getEventId(),nameEvent.getText(),Date.valueOf(date_debut.getValue()),Date.valueOf(date_Fin.getValue()),Integer.parseInt(tanche_Age.getText()),adresseEvente.getText(),Integer.parseInt(nb_participant.getText()),Integer.parseInt(nbReservation.getText()),despEvent.getText(),AuthService.loggedInUser.getUserId());

                            EventDAOImp eventService = new EventDAOImp();

                            eventService.update(e);
                            TableEventList.setItems(FXCollections.observableArrayList(eventService.getAll()));

                        } catch (SQLException e) {
                            e.printStackTrace();

                        }

                        AlertModal.showInfoAlert("! evenement update avec succès ", "   evenement ajouté avec succès!");


                    }
                
            

        });
        
    }

}
