package controller;

import java.net.URL;
import java.util.ResourceBundle;


import java.time.Instant;

import service.AuthService;
import service.CommentaireService;
import utils.AlertModal;
import utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import entity.User;
import entity.Commentaire;
import entity.Post;

public class AjouterCommentaireController implements Initializable {
	
	@FXML
    private VBox AjoutCommentaireView;
	
	@FXML
    private HBox annuler;
	
	    @FXML
	    private Label enregistrer;
	    @FXML
	    private TextArea commentaire;

	    @FXML
	    private ImageView imgProfile;

	    @FXML
	    private ImageView imgVerified;

	    @FXML
	    private Label username;
	    
	    
	    
		private boolean update;
		Instant instant = Instant.now();
		
		
		@FXML
	    void AnnulerAction(ActionEvent event) {
			SceneManager.changeScene(event, "forum.fxml", "Forum", null);
	    }

	    @FXML
	    void addcommentaire(ActionEvent event) {
	    	Commentaire c1 = new Commentaire();
	    	User e = AuthService.loggedInUser;
	    	Post p = new Post() ;
			CommentaireService cs = new CommentaireService();
			String texte = commentaire.getText();
			String nom = username.getText();
			if (update == false) {
				Commentaire c = new Commentaire(nom,texte);
				cs.ajouterCommentaire(c,e);
				AlertModal.showInfoAlert("Post", "Votre Commentaire a été ajouté avec succés");
				SceneManager.changeScene(event, "forum.fxml", "Forum", null);
			} else {
				cs.update(c1,e,p);
			}



	    }
	    
	    private Commentaire getCommentaire(){
	        Commentaire comm = new Commentaire();
	        User user = new User();
	        user.setFirstName("Yassine Hantous");
	        user.setUserImage("/img/user.png");
	        comm.setUser(user);
	        return comm;
	    }
	    
	    public void setData(Commentaire comm){
	        Image img;
	        img = new Image(getClass().getResourceAsStream(comm.getUser().getUserImage()));
	        imgProfile.setImage(img);
	        username.setText(comm.getUser().getFirstName());
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setData(getCommentaire());

	}

}
