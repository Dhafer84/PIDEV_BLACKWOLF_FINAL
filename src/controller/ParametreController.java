package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.AuthService;
import service.UserDAOImpl;
import utils.AlertModal;
import utils.SceneManager;
import utils.UploadImage;

public class ParametreController implements Initializable {
	private File imageFile;

	@FXML
	private TextField tf_fname;

	@FXML
	private TextField tf_lname;

	@FXML
	private Button button_back;

	@FXML
	private Button button_choosefile;

	@FXML
	private Button button_updateprofile;

	@FXML
	private Text text_filename;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tf_fname.setText(AuthService.loggedInUser.getFirstName());
		tf_lname.setText(AuthService.loggedInUser.getLastName());

		button_back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SceneManager.changeScene(event, "home.fxml", "Home", null);
			}
		});

		button_choosefile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("this works fine");

				FileChooser fileChooser = new FileChooser();

				fileChooser.getExtensionFilters()
						.addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
				File file = fileChooser.showOpenDialog(new Stage());

				if (file != null) {
					imageFile = file;
					System.out.println(file);
					text_filename.setText("Selected file :" + file.getName());
				}

			}
		});

		button_updateprofile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (imageFile != null) {
					String imageName = "";
					try {
						imageName = UploadImage.Uplaod(imageFile,
								Integer.toString(AuthService.loggedInUser.getUserId()));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (!tf_fname.getText().equals("")) {
						AuthService.loggedInUser.setFirstName(tf_fname.getText());
					}
					if (!tf_lname.getText().equals("")) {
						AuthService.loggedInUser.setLastName(tf_lname.getText());
					}
					if (!imageName.equals("")) {
						AuthService.loggedInUser.setUserImage(imageName);
						try {
							new UserDAOImpl().update(AuthService.loggedInUser);
							AlertModal.showInfoAlert(null, "You're account has been updated successfuly");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
	}
}
