
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CommentaireService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import entity.Commentaire;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import entity.Post;
import entity.PostAudience;
import entity.User;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import service.AuthService;
import service.PostService;
import utils.AlertModal;
import utils.SceneManager;
import utils.UploadImage;
import utils.UploadImage3;

public class ForumController implements Initializable {

	PostService cs = new PostService();
	ObservableList<Post> post = FXCollections.observableArrayList();
	Post p;

	@FXML
	private VBox PostForum;

	@FXML
	private Button button_nav_accueil;

	@FXML
	private Button button_nav_bib;

	@FXML
	private Button button_nav_forum;

	@FXML
	private Button button_nav_notif;

	@FXML
	private Menu customMenu;

	@FXML
	private Label enregistrer;

	@FXML
	private ImageView img;

	@FXML
	private ImageView imgProfile;
	@FXML
	private ChoiceBox<String> category;

	@FXML
	private ImageView imgVerified;

	@FXML
	private MenuItem item_annonce;

	@FXML
	private MenuItem item_compte;

	@FXML
	private MenuItem item_event;

	@FXML
	private MenuItem item_logout;

	@FXML
	private MenuItem item_parametre;

	@FXML
	private Label label_welcome;

	@FXML
	private Label lab_url;
	@FXML
	private MenuBar menubar;

	@FXML
	private TextArea post_content;
	
	private File imageFile;

	@FXML
	void AddImage(MouseEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("image Files", "*.jpg", "*.png"));
		File f = fc.showOpenDialog(null);
		if (f != null) {
			imageFile = f;
			lab_url.setText(f.getAbsolutePath());
			// lab_url.setText(f.getPath());
			Image image = new Image(f.toURI().toString(), 270, 225, true, true);
			img.setImage(image);
		}

	}

	@FXML
	private Label username;

	private boolean update;

	@FXML
	void addpost(MouseEvent event) {
		
		User e = AuthService.loggedInUser;
		PostService cs = new PostService();
		String texte = post_content.getText();
		String category = null;
		String image1 = lab_url.getText();
		Date date = (Date) Date.from(Instant.now());

		if (update == false) {
			Post p = new Post(e, texte, category, date, image1);
			String UplodedImage = "" ;
			try {
				UplodedImage = UploadImage3.Uplaod(imageFile,Integer.toString(p.getId()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p.setImage(UplodedImage);
			cs.ajouterPost(e, p);
			AlertModal.showInfoAlert("Post", "Votre Post a été ajouté avec succés");
		}

	}

	List<Post> recentlyPosted;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recentlyPosted = new ArrayList<>(getRecentlyPosted());
		try {
			for (Post post : recentlyPosted) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/post.fxml"));

				VBox VBox = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setData(post);

				PostForum.getChildren().add(VBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Hide functionality for simple users
		if (!AuthService.loggedInUser.getRole().equals("admin")) {
			item_annonce.setVisible(false);
			item_event.setVisible(false);
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

		button_nav_notif.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "notif.fxml", "Notification", null);
			}
		});

	}

	private List<Post> getRecentlyPosted() {

		List<Post> ls = new ArrayList<>();
		try {
			ls = new PostService().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ls);

		/*
		 * Post post = new Post(); User user = new User(); post.setId(1);
		 * user.setFirstName(AuthService.loggedInUser.getFirstName());
		 * user.setUserImage("/img/user.png"); post.setUser(user);
		 * post.setAudience(PostAudience.PUBLIC); post.setCaption("Hello everybody.");
		 * post.setImage("/img/img2.jpg"); post.setTotalReactions(0);
		 * post.setNbComments(1); post.setNbShares(100); ls.add(post);
		 * 
		 * Post post1 = new Post(); User user1 = new User();
		 * user1.setFirstName("Souhayel Machfer"); user1.setUserImage("/img/user.png");
		 * post1.setUser(user1);
		 * 
		 * post1.setAudience(PostAudience.PUBLIC); post1.setCaption("Malla Hantous");
		 * post1.setImage("/img/img2.jpg"); post1.setTotalReactions(0);
		 * post1.setNbComments(1); post1.setNbShares(0); ls.add(post1);
		 * 
		 * Post post11 = new Post(); User user11 = new User();
		 * user11.setFirstName("Dhafer"); user11.setUserImage("/img/user.png");
		 * post11.setUser(user11);
		 * 
		 * post11.setAudience(PostAudience.PUBLIC);
		 * post11.setCaption("Shouf k Hantous ki yobde3");
		 * post11.setImage("/img/img2.jpg"); post11.setTotalReactions(0);
		 * post11.setNbComments(1); post11.setNbShares(0); ls.add(post11);
		 * 
		 * Post post111 = new Post(); User user111 = new User();
		 * user111.setFirstName("Yassine Hantous");
		 * user111.setUserImage("/img/user.png"); post111.setUser(user111);
		 * 
		 * post111.setAudience(PostAudience.PUBLIC);
		 * post111.setCaption("Hello everybody."); post111.setImage("/img/img2.jpg");
		 * post111.setTotalReactions(0); post111.setNbComments(1);
		 * post111.setNbShares(0); ls.add(post111);
		 */

		return ls;

	}

	// Setting user info passed from login screen
	public void setUserInformation(int id_user, String first_name, String last_name, String email) {
		System.out.println(AuthService.loggedInUser);
	}
}
