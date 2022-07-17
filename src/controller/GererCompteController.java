package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.AuthService;
import service.UserDAOImpl;
import utils.AlertModal;
import utils.SceneManager;

public class GererCompteController implements Initializable {
	// Header section
	@FXML
	private Button button_back;
	@FXML
	private Button button_active_users;
	@FXML
	private Button button_inactive_users;
	@FXML
	private Button button_all_users;
	@FXML
	private TextField tf_search;
	
	// User Information section
	private int selected_user_id;
	@FXML
	private ImageView iv_user_image;
	@FXML
	private Text text_user_full_name;
	@FXML
	private Text text_user_email;
	@FXML
	private Text text_user_role;
	@FXML
	private Text text_user_label_role;
	@FXML
	private Text text_user_label_email;
	@FXML
	private Text text_user_label_full_name;
	@FXML
	private Button button_user_toggle_activate; 
	
	
	// TableView section
	@FXML
	private TableView<User> tv_users;

	@FXML
	private TableColumn<User, String> tc_fn;
	@FXML
	private TableColumn<User, String> tc_ln;
	@FXML
	private TableColumn<User, String> tc_etatCompte;

	private ObservableList<User> usersList;
	List<User> filteredList = new ArrayList<User>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tc_fn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
		tc_ln.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));
		tc_etatCompte.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEtatCompte()));
		button_user_toggle_activate.setVisible(false);
		
		// initialize the list of all users
		setInitialList();

		// Click handlers
		button_back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "home.fxml", "Home", null);
			}
		});

		tf_search.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String searchTerm = tf_search.getText();
				if (searchTerm.equals("")) {
					tv_users.setItems(usersList);
					return;
				}
				filterListUsers(searchTerm, usersList, "all");
			}
		});
		
		// Toggle active button
		button_user_toggle_activate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				User currentUserInfo = null;
				try {
					currentUserInfo = new UserDAOImpl().get(selected_user_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String currentEtatCompte = currentUserInfo.getEtatCompte();
				if(currentEtatCompte.equals("inactive")) {
					try {
						new UserDAOImpl().changeEtatCompte(selected_user_id, "active");
						AuthService.loggedInUser.setEtatCompte("active");
						setInitialList();
						AlertModal.showInfoAlert("Etat Compte updated", "Your account state with id = "+ selected_user_id +" has become active");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						new UserDAOImpl().changeEtatCompte(selected_user_id, "inactive");
						AuthService.loggedInUser.setEtatCompte("inactive");
						setInitialList();
						AlertModal.showInfoAlert("Etat Compte updated", "account state with id = "+ selected_user_id +" has become inactive");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		tv_users.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				User person = tv_users.getSelectionModel().getSelectedItem();
				// Set labels
				text_user_label_full_name.setText("Full name :");
				text_user_label_role.setText("role    :");
				text_user_label_email.setText("Email    :");
				button_user_toggle_activate.setVisible(true);
				// Set user info section with selected record
				String imageSource = "http://localhost:3030/api/v1/users/image/"+person.getUserImage();
				iv_user_image.setImage(new Image(imageSource));
				text_user_full_name.setText(person.getFirstName() + ' ' + person.getLastName());
				text_user_role.setText(person.getRole());
				text_user_email.setText(person.getEmail());
				selected_user_id = person.getUserId();
			}
		});

		button_all_users.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				filterListUsersByAccountState(usersList, "all");
				tf_search.setText("");

			}
		});
		button_active_users.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				filterListUsersByAccountState(usersList, "active");
			}
		});
		button_inactive_users.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				filterListUsersByAccountState(usersList, "inactive");
			}
		});

	}

	// Function that initialize the tableView with existing users in DB
	public void setInitialList() {

		try {
			usersList = FXCollections.observableArrayList(new UserDAOImpl().getAll());

			tv_users.setItems(usersList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function that filter the users based on the search term
	public void filterListUsers(String searchTerm, ObservableList<User> list, String etatCompte) {
		filteredList.clear();
		filteredList.addAll(list);
		filteredList.removeIf(item -> item.getFirstName().toLowerCase().indexOf(searchTerm.toLowerCase()) == -1
				&& item.getLastName().toLowerCase().indexOf(searchTerm.toLowerCase()) == -1);
		tv_users.setItems(FXCollections.observableArrayList(filteredList));
	}

	// Function that filter users based on their state (EtatCompte)
	public void filterListUsersByAccountState(List<User> usersList, String etatCompte) {
		List<User> secondFilter = new ArrayList<User>();
		secondFilter.addAll(usersList);
		if (etatCompte.equals("active"))
			secondFilter.removeIf(item -> item.getEtatCompte().equals("inactive"));
		if (etatCompte.equals("inactive"))
			secondFilter.removeIf(item -> item.getEtatCompte().equals("active"));

		tv_users.setItems(FXCollections.observableArrayList(secondFilter));
	}
}
