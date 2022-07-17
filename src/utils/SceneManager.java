package utils;

import java.io.IOException;

import controller.HomeController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	public static void changeScene(ActionEvent event, String fxmlFile, String title, User usr) {

		Parent root = null;
		fxmlFile = "/view/" + fxmlFile;
		if (usr != null) {
			try {
				FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
				root = loader.load();
				HomeController homeController = loader.getController();
				homeController.setUserInformation(usr.getUserId(), usr.getFirstName(), usr.getLastName(),
						usr.getEmail());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root, 1000, 700));
		stage.show();
	}

	public static void changeSceneForMenuBar(Stage menubar, String fxmlFile, String title) {

		Parent root = null;
		fxmlFile = "/view/" + fxmlFile;
		try {
			root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Stage stage = menubar;
		stage.setTitle(title);
		stage.setScene(new Scene(root, 1000, 700));
		stage.show();
	}

	public static void changeSceneForMenuBar1(Stage menubar1, String fxmlFile, String title) {


		Parent root = null;
		fxmlFile = "/view/" + fxmlFile;
		try {
			root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Stage stage = menubar1;
		stage.setTitle(title);
		stage.setScene(new Scene(root, 1000, 700));
		stage.show();
			
	}

	
}
