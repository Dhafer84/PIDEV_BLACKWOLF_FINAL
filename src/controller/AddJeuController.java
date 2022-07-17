package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import entity.Jeu;
import service.AuthService;
import service.JeuDAOImpl;
import utils.Datasource;
import utils.SceneManager;
import utils.UploadImage2;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AddJeuController implements Initializable {
	private File imageFile;
	@FXML
	private TableView<Jeu> jeu;

	@FXML
	private TableColumn<Jeu, Integer> id;

	@FXML
	private TableColumn<Jeu, String> name;

	@FXML
	private TableColumn<Jeu, String> cat;

	@FXML
	private TableColumn<Jeu, String> picture;

	@FXML
	private Button add;

	@FXML
	private Button upload;

	@FXML
	private Button afficher;

	@FXML
	private Button update;

	@FXML
	private Button delete;

	@FXML
	private Label lab_url;

	@FXML
	private ComboBox<String> cbcat;

	@FXML
	private ImageView img;

	@FXML
	private TextField nametxt;

	@FXML
	private TextField txt_id;

	@FXML
	private TextField Search;

	@FXML
	private Label label_welcome;

	@FXML
	private Button button_logout;

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
	private TextField txt_iduser;
	
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
	void GoJeux(MouseEvent event) {

	}

	@FXML
	void DeleteJeu(ActionEvent event) {

	}

	@FXML
	void Update(ActionEvent event) {

	}

	ObservableList<Jeu> list;

	public ObservableList<Jeu> data = FXCollections.observableArrayList();
	int index = -1;

	public void setUserInformation(int id_user, String first_name, String last_name, String email) {
		label_welcome.setText(last_name);
		System.out.println(AuthService.loggedInUser);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * if(!AuthService.loggedInUser.getRole().equals("admin")) {
		 * item_annonce.setVisible(false); item_event.setVisible(false);
		 * item_compte.setVisible(false); }
		 */
		
		label_welcome.setText(AuthService.loggedInUser.getFirstName());
		if(AuthService.loggedInUser.getUserImage().equals("")) {
			AuthService.loggedInUser.setUserImage( "avatar.png");
		}
		String imageSource = "http://localhost:3030/api/v1/users/image/"+AuthService.loggedInUser.getUserImage();
		profile_image.setImage(new Image(imageSource));

		search();
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
				
			}
		});

		button_logout.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AuthService.logout();
				SceneManager.changeScene(event, "login.fxml", "Login", null);
			}
		});
		update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int id = Integer.parseInt(txt_id.getText());
				// int iduser= Integer.parseInt(txt_iduser.getText());
				int IdU = AuthService.loggedInUser.getUserId();

				String imageName2 = "";
				if (imageFile != null) {

					try {
						imageName2 = UploadImage2.Uplaod(imageFile,
								Integer.toString(AuthService.loggedInUser.getUserId()));
					} catch (IOException e) { // TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(id + "" + nametxt.getText() + "" + cbcat.getValue() + "" + lab_url.getText() + ""
						+ txt_iduser.getText());

				Jeu j = new Jeu.JeuBuilder().ID(id).Title(nametxt.getText()).Categorie(cbcat.getValue()).url(imageName2)
						.userId(IdU).build();

				JeuDAOImpl ps = new JeuDAOImpl();

				try {
					ps.update(j);
					search();
					JOptionPane.showMessageDialog(null, "Update");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int id = Integer.parseInt(txt_id.getText());
				System.out.println(id + "" + nametxt.getText() + "" + cbcat.getValue() + "" + lab_url.getText());
				Jeu j = new Jeu(id, nametxt.getText(), cbcat.getValue(), lab_url.getText());
				JeuDAOImpl ps = new JeuDAOImpl();

				try {
					ps.delete(j);
					search();
					JOptionPane.showMessageDialog(null, "delete");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	@FXML
	void Addimg(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("image Files", "*.jpg", "*.png"));
		File f = fc.showOpenDialog(new Stage());
		if (f != null) {
			imageFile = f;
			setImageFile(f);
			lab_url.setText(f.getName());
			Image image = new Image(f.toURI().toString(), 270, 225, true, true);
			img.setImage(image);
		}

	}

	@FXML
	void AddJeux(ActionEvent event) throws SQLException {
		int IdU = AuthService.loggedInUser.getUserId();

		String imageName2 = "";

		if (imageFile != null) {

			try {
				imageName2 = UploadImage2.Uplaod(imageFile, Integer.toString(AuthService.loggedInUser.getUserId()));
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(nametxt.getText() + "" + cbcat.getValue() + "" + lab_url.getText() + "" + IdU);
		Jeu j = new Jeu.JeuBuilder().Title(nametxt.getText()).Categorie(cbcat.getValue()).url(imageName2).userId(IdU)
				.build();

		JeuDAOImpl ps = new JeuDAOImpl();
		SceneManager.changeScene(event, "AddJeu.fxml", null, null);
		ps.insert(j);
		search();

	}

	@FXML
	void addCat(MouseEvent event) {
		Connection con = Datasource.getConnection();

		String sql = "SELECT categorie FROM categorie";
		List<String> categories = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				categories.add(rs.getString("categorie"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(categories.get(0));

		ObservableList<String> list = FXCollections.observableArrayList(categories);

		cbcat.setItems(list);

	}

	@FXML
	public void ViewJeu() {
		JeuDAOImpl ps = new JeuDAOImpl();
		ObservableList<Jeu> datax = ps.data();
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		name.setCellValueFactory(new PropertyValueFactory<>("Title"));
		cat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
		picture.setCellValueFactory(new PropertyValueFactory<>("url"));
		picture.setCellValueFactory(new PropertyValueFactory<>("url"));
		jeu.setItems(datax);
		search();
	}

	@FXML
	void getSelected(MouseEvent event) {
		index = jeu.getSelectionModel().getSelectedIndex();

		if (index <= -1) {

			return;
		}

		txt_id.setText(id.getCellData(index).toString());
		nametxt.setText(name.getCellData(index).toString());
		cbcat.setPromptText(cat.getCellData(index).toString());
		lab_url.setText(picture.getCellData(index).toString());
		// Image image = new Image(lab_url.getText());
		// img.setImage(image);
		Jeu game = null;
		try {
			int idjeu = Integer.parseInt(txt_id.getText());
			game = new JeuDAOImpl().get(idjeu);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imageSource = "http://localhost:3030/api/v1/games/image/" + game.getUrl();
		img.setImage(new Image(imageSource));
	}

	@FXML
	public void search() {
		JeuDAOImpl ps = new JeuDAOImpl();
		ObservableList<Jeu> datas = ps.data();
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		name.setCellValueFactory(new PropertyValueFactory<>("Title"));
		cat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
		picture.setCellValueFactory(new PropertyValueFactory<>("url"));
		jeu.setItems(datas);
		FilteredList<Jeu> filteredData = new FilteredList<>(datas, b -> true);
		Search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Jeu je) -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (je.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (je.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (je.getUrl().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {
					return false;
				}
			});
		});
		SortedList<Jeu> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(jeu.comparatorProperty());
		jeu.setItems(sortedData);
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

}
