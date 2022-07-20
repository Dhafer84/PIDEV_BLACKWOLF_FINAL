package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.AuthService;
import entity.User;
import entity.Post;
import entity.PostAudience;
import entity.Reactions;

import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable {
	 	@FXML
	    private VBox post_view;
	   @FXML
	    private ImageView audience;
	   @FXML
	    private VBox updatebox;

	    @FXML
	    private Label post_content;

	    @FXML
	    private Label comment;

	    @FXML
	    private Label date;

	    @FXML
	    private ImageView imgAngry;

	    @FXML
	    private ImageView imgCare;

	    @FXML
	    private ImageView imgHaha;

	    @FXML
	    private ImageView imgLike;

	    @FXML
	    private ImageView imgLove;

	    @FXML
	    private ImageView imgPlus;

	    @FXML
	    private ImageView imgPost;

	    @FXML
	    private ImageView imgProfile;

	    @FXML
	    private ImageView imgReaction;

	    @FXML
	    private ImageView imgSad;

	    @FXML
	    private ImageView imgVerified;

	    @FXML
	    private ImageView imgWow;

	    @FXML
	    private HBox likeContainer;

	    @FXML
	    private Label nbComments;

	    @FXML
	    private Label nbReactions;

	    @FXML
	    private Label nbShares;

	    @FXML
	    private Label reactionName;

	    @FXML
	    private HBox reactionsContainer;

	    @FXML
	    private Label username;
	    @FXML
	    private Button modifier;
	    @FXML
	    private Button supprimer;
	    @FXML
	    void modifierAction(MouseEvent event) {

	    }
	    
	    @FXML
	    void supprimerAction(MouseEvent event) {

	    }
	    
	    

	    
    

    private long startTime = 0;
    private Reactions currentReaction;
    private Post post;
    
    @FXML
    void onCommentContainerPressed(MouseEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/AjouterCommentaire.fxml"));
        	Parent root=(Parent) loader.load();
        	Scene scene=new Scene(root);
        	Stage stage =new Stage();
        	stage.setScene(scene);
        	stage.show();
        	
        	}catch(Exception e) {
        		System.out.append("erreur d'affichage");
        		e.printStackTrace();
        	}

    }

    @FXML
    public void onLikeContainerPressed(MouseEvent me){
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me){
        if(System.currentTimeMillis() - startTime > 500){
            reactionsContainer.setVisible(true);
        }else {
            if(reactionsContainer.isVisible()){
                reactionsContainer.setVisible(false);
            }
            if(currentReaction == Reactions.NON){
                setReaction(Reactions.LIKE);
            }else{
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    public void onReactionImgPressed(MouseEvent me){
        switch (((ImageView) me.getSource()).getId()){
            case "imgLove":
                setReaction(Reactions.LOVE);
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        reactionsContainer.setVisible(false);
    }

    public void setReaction(Reactions reaction){
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgReaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if(currentReaction == Reactions.NON){
            post.setTotalReactions(post.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if(currentReaction == Reactions.NON){
            post.setTotalReactions(post.getTotalReactions() - 1);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
    }
    
    @FXML
    void AfficherCommentaire(MouseEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/AfficherCommentaire.fxml"));
        	Parent root=(Parent) loader.load();
        	Scene scene=new Scene(root);
        	Stage stage =new Stage();
        	stage.setScene(scene);
        	stage.show();
        	
        	}catch(Exception e) {
        		System.out.append("erreur d'affichage");
        		e.printStackTrace();
        	}

    }
    
    @FXML
    void OnUpdateClick(MouseEvent event) {
    	
    	 if(updatebox.isVisible()){
             updatebox.setVisible(false);
         }else {updatebox.setVisible(true);}

    }

    public void setData(Post post){
        this.post = post;
        Image img;
        img = new Image(getClass().getResourceAsStream("/img/user.png"));
        imgProfile.setImage(img);
        
        username.setText(post.getUser().getFirstName());
        if(post.getAudience() == PostAudience.PUBLIC){ 
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        }else{
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);

        if(true){
        	post_content.setText(post.getCaption());
        }else{
        	post_content.setManaged(false);
        }

        if (true){
        	String imageSource = "http://localhost:3030/api/v1/forum/image/"+post.getImage();
        	imgPost.setImage(new Image(imageSource));
          //  img = new Image(getClass().getResourceAsStream("/img/img2.jpg"));
           
        }else{
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }
        
        nbReactions.setText(String.valueOf(post.getTotalReactions()));
        nbComments.setText("comments");
        nbShares.setText(" comments");

        currentReaction = Reactions.NON;
        
    }


    private Post getPost(){
        Post post = new Post();
        User user = new User();
        user.setFirstName("Yassine Hantous");
        user.setUserImage("/img/user.png");
        post.setUser(user);
       
        post.setAudience(PostAudience.PUBLIC);
        post.setCaption("Hello everybody.");
        post.setImage("/img/img2.jpg");
        post.setTotalReactions(0);
        post.setNbComments(1);
        post.setNbShares(0);

        return post;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData(getPost());
    }
}
