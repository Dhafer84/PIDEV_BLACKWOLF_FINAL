/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Annonce;
import entity.Event;
import java.io.File;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import service.AnnonceDAOImp;
import service.AuthService;
import service.EventDAOImp;
import service.JeuDAOImpl;
import utils.AlertModal;
import utils.SceneManager;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AnnonceController implements Initializable {

      @FXML
    private Label label_welcome;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_nav_accueil;

    @FXML
    private Button button_nav_forum;

    @FXML
    private Button button_nav_bib;

    @FXML
    private Button button_nav_notif;

    @FXML
    private TextField filterFiled;

    @FXML
    private TextField titlAnnonce;

    @FXML
    private TextField priceAn;

    @FXML
    private TextField ContentAn;


    @FXML
    private Button ajoute_Annonce;

    @FXML
    private DatePicker dateCre;

    @FXML
    private Button UPDATE;

   

    @FXML
    private TableView<Annonce> TableAnnonceListe;

    @FXML
    private TableColumn<Annonce, String> titleAnnonce;

    @FXML
    private TableColumn<Annonce, Date> DateCreate;

    @FXML
    private TableColumn<Annonce, String> typeAnnonce;

    @FXML
    private TableColumn<Annonce, Integer> Price;

    @FXML
    private TableColumn<Annonce, String> annonceC;

    @FXML
    private TableColumn<Annonce, Annonce> action;
    
     @FXML
    private ComboBox <String> comboannonce;
     
        @FXML
    private Button imgAn;

    @FXML
    private Label urlSelected;

     @FXML
    private ImageView imguploqd;
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
	void Addimg(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image Files", "*.jpg", "*.png"));
		File f = fc.showOpenDialog(null);
		if (f != null) {
			urlSelected.setText(f.getAbsolutePath());
			//lab_url.setText(f.getPath());
			Image image = new Image(f.toURI().toString(), 270, 225, true, true);
			imguploqd.setImage(image);
		}

	}


   
    
    
@FXML
    void Select(ActionEvent event) {
        String s =comboannonce.getSelectionModel().getSelectedItem().toString();

    }
    Annonce annonceselected;

    ObservableList<Annonce> listAnnonce;

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
        
        
         ObservableList<String> list =FXCollections.observableArrayList("echange","vente");
        comboannonce.setItems(list);
        

        // TODO
        
        buildTableAndData();
    }

    private void buildTableAndData() {
        //add haifa
        AnnonceDAOImp a1 = new AnnonceDAOImp();
        titleAnnonce.setCellValueFactory(new PropertyValueFactory<>("annonce_title"));
      DateCreate.setCellValueFactory(new PropertyValueFactory<>("annonce_created_at"));
        typeAnnonce.setCellValueFactory(new PropertyValueFactory<>("annonce_type"));
        Price.setCellValueFactory(new PropertyValueFactory<>("total_price"));
       
        annonceC.setCellValueFactory(new PropertyValueFactory<>("annonce_content"));
        

        List<Annonce> list = new ArrayList<>();
        try {
            list = a1.getAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        listAnnonce = FXCollections.observableArrayList(list);
        System.out.println(list);

         titleAnnonce.setCellValueFactory(new PropertyValueFactory<>("annonce_title"));
        DateCreate.setCellValueFactory(new PropertyValueFactory<>("annonce_created_at"));
        typeAnnonce.setCellValueFactory(new PropertyValueFactory<>("annonce_type"));
        Price.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        
        annonceC.setCellValueFactory(new PropertyValueFactory<>("annonce_content"));
        TableAnnonceListe.setItems(FXCollections.observableArrayList(listAnnonce));

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Annonce> filteredData = new FilteredList<>(listAnnonce, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getAnnonce_title().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (event.getAnnonce_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.

                } else {
                    return false; // Does not match.
                }
            });
        }
        );

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Annonce> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty()
                .bind(TableAnnonceListe.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TableAnnonceListe.setItems(sortedData);

        Callback<TableColumn<Annonce, Annonce>, TableCell<Annonce, Annonce>> cellFactory = new Callback<TableColumn<Annonce, Annonce>, TableCell<Annonce, Annonce>>() {
            @Override
            public TableCell call(final TableColumn<Annonce, Annonce> param) {
                final TableCell<Annonce, Annonce> cell = new TableCell<Annonce, Annonce>() {

                    @Override
                    public void updateItem(Annonce item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button editBtn = new Button("UPDATE");
                            final Button dltBtn = new Button("DELETE");
                            editBtn.setOnAction((ActionEvent event) -> {
                                //  saveButton.setText("UPDATE");
                                annonceselected = getTableView().getItems().get(getIndex());
                                titlAnnonce.setText(annonceselected.getAnnonce_title());
                                dateCre.setValue((annonceselected.getAnnonce_created_at()).toLocalDate());
                                priceAn.setText(String.valueOf(annonceselected.getTotal_price()));
                               ContentAn.setText(String.valueOf(annonceselected.getAnnonce_content()));



                                comboannonce.setValue(annonceselected.getAnnonce_type());

                                //TotalPriceAn.setText(annonceselected.getTotal_price());
                              
                                try {

                                    a1.update(annonceselected);
                                    // passwordField.setText( eventselected.getPassword());
                                } catch (SQLException ex) {
                                    Logger.getLogger(AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            });
                            dltBtn.setOnAction(event -> {
                                try {
                                    annonceselected = getTableView().getItems().get(getIndex());

                                    System.out.println("test" + annonceselected);
                                    a1.delete(annonceselected);
                                    TableAnnonceListe.setItems(FXCollections.observableArrayList(a1.getAll()));

                                    AlertModal.showErrorAlert(null, "your annonce is delete!");

                                } catch (SQLException ex) {
                                    Logger.getLogger(AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
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
        ajoute_Annonce.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (titlAnnonce.getText().trim().isEmpty() || priceAn.getText().trim().isEmpty()
                      || ContentAn.getText().trim().isEmpty()) {
                    AlertModal.showErrorAlert(null, "Please fill in all information to add annonce!");
                } 
                if ( !priceAn.getText().matches("[0-9]+")) {
                    AlertModal.showErrorAlert("Price" , "Please must be number !");
                
                }
                else {
                    {
                        try {
                            Annonce a;
                            a = new Annonce.AnnonceBuilder().annonce_type(comboannonce.getValue()).annonce_created_at(Date.valueOf(dateCre.getValue())).annonce_title(titlAnnonce.getText()).annonce_content(ContentAn.getText())
                                    .total_price(Integer.parseInt(priceAn.getText())).annonce_image(urlSelected.getText()).jeu_id(1).userId(AuthService.loggedInUser.getUserId()).build();

                            AnnonceDAOImp eventService = new AnnonceDAOImp();

                            eventService.insert(a);
                            TableAnnonceListe.setItems(FXCollections.observableArrayList(eventService.getAll()));

                        } catch (SQLException e) {
                            e.printStackTrace();

                        }

                        AlertModal.showInfoAlert("! annonce ajouté avec succès ", "   annonce ajouté avec succès!");

                        titlAnnonce.setText(null);
                        dateCre.setValue(null);
                         ContentAn.setText(null);

                     //   comboannonce.setValue(null);
                        priceAn.setText(null);
                        urlSelected.setText(null);
                        imguploqd.setImage(null);

                    }
                }
            }

        });
        //UPDATE
         UPDATE.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
                    
                        try {
                            Annonce a;
                            a = new Annonce(annonceselected.getAnnonce_id(),comboannonce.getValue(),Date.valueOf(dateCre.getValue()),Integer.parseInt(priceAn.getText()),imgAn.getText(),ContentAn.getText(),titlAnnonce.getText(),AuthService.loggedInUser.getUserId(),1);
                            System.out.println(".handle()"+a);
                            AnnonceDAOImp eventService = new AnnonceDAOImp();
                            eventService.update(a);
                            TableAnnonceListe.setItems(FXCollections.observableArrayList(eventService.getAll()));

                        } catch (SQLException e) {
                            e.printStackTrace();

                        }

                        AlertModal.showInfoAlert("! annonce update avec succès ", "   annonce ajouté avec succès!");

                      

                    }
      

        });
    }

}