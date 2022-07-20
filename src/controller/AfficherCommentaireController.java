package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import entity.Post;


public class AfficherCommentaireController {
	 	Commentaire c ;
	    CommentaireService cs = new  CommentaireService ();
	    @FXML
	    private TableView<Commentaire> commentaireview;
	    @FXML
	    private TableColumn<Commentaire, Date> commentaire_date;
	    @FXML
	    private TableColumn<Commentaire, String> commentaire_content;
	    @FXML
	    private TableColumn<Commentaire, String> editcol;
	    
	   
	    
	ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
	
	
	@FXML
    private Button btnDelete;
	
	@FXML
	private Button refresh;
	@FXML
	private Button btnajouter;
	@FXML
	private TextField chercher_blog;
	 @FXML
	    void DeleteComment(ActionEvent event) {

	    }

	// Event Listener on Button[#chercher_blog].onMouseDragged
	@FXML
	public void chercherBlog(ActionEvent event) {
		Post p = new Post();
		int id = p.getId();
		ObservableList<Commentaire> commentaires = cs.afficherCommentaire();  
        
        commentaire_content.setCellValueFactory(new PropertyValueFactory<>("commentaire_content") );
        commentaire_date.setCellValueFactory(new PropertyValueFactory<>("date") );

        commentaireview.setItems(commentaires);
        
        FilteredList<Commentaire> filteredData = new FilteredList<>(commentaires, b -> true);
        
       chercher_blog.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Commentaire cm) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;      
                }
                else if (cm.getCommentaire().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;      
                }
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Commentaire> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(commentaireview.comparatorProperty());
        commentaireview.setItems(sortedData);
    }
	
	// Event Listener on Button[#refresh].onMouseClicked
	@FXML
	public void refreshTable(MouseEvent event) {
        commentaires.clear();
        
        afficher_commentaire();
	}
	
	public void loadData() {
        
        afficher_commentaire();
        
       
    }    
	
	
public void afficher_commentaire(){

        
        
        ObservableList<Commentaire> commentaires = cs.afficherCommentaire(); 
        
        commentaire_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        commentaire_content.setCellValueFactory(new PropertyValueFactory<>("commentaire") );
   
        
         commentaireview.setItems(commentaires);
         Callback<TableColumn<Commentaire, String>, TableCell<Commentaire, String>> cellFoctory = (TableColumn<Commentaire, String> param) ->
         {
             // make cell containing buttons
             final TableCell<Commentaire, String> cell = new TableCell<Commentaire, String>() {
                 @Override
                 public void updateItem(String item, boolean empty) {
                     super.updateItem(item, empty);
                     //that cell created only on non-empty rows
                     if (empty) {
                         setGraphic(null);
                         setText(null);

                     } else {
                    	 

                         Button deleteIcon = new Button("Delete");
                         Button editIcon = new Button("Edit");
                         
                      
                         

               
                         deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                             c = commentaireview.getSelectionModel().getSelectedItem();
                             cs.supprimer(c);
                             refreshTable(event);
                             

                         });
                         editIcon.setOnMouseClicked((MouseEvent event) -> {

                             c = commentaireview.getSelectionModel().getSelectedItem();
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("/view/AjouterCommentaire.fxml"));
                             try {
                                 loader.load();
                             } catch (IOException ex) {
                                 Logger.getLogger(AfficherCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                                 
                             setUpdate(true);
                             setTextField( c.getName(),c.getCommentaire());
                             Parent parent = loader.getRoot();
                             Stage stage = new Stage();
                             stage.setScene(new Scene(parent));
                             stage.initStyle(StageStyle.UTILITY);
                             stage.show();
                             




                         });

                         HBox managebtn = new HBox(editIcon, deleteIcon);
                         managebtn.setStyle("-fx-alignment:center");
                         HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                         HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                         setGraphic(managebtn);

                         setText(null);

                     }
                 }

             };
             return cell;
          };
          editcol.setCellFactory(cellFoctory);
          
         
     }

	@FXML
	public void btnAjouter(MouseEvent event) {
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
	
	void setTextField( String name,String commentaire) {

		commentaire_date.setText(name);
       
		commentaire_content.setText(commentaire);

    }
	void setUpdate(boolean b) {

    }
	

  
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
	}
}
