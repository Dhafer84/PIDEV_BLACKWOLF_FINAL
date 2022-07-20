package service;
import utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Commentaire;
import entity.User;
import entity.Post;
public class CommentaireService {
	 Connection cnx;
	    PreparedStatement ste;

	    public CommentaireService(){
	        Datasource.getInstance();
			cnx = Datasource.getConnection();
	    }
	    
	     public void ajouterCommentaire(Commentaire c, User e){
	    	 

	        try {
	            String sql = "insert into commentaire (commentaire_date,commentaire_content,user_id,post_id) values (NOW(),?,?,?)";
	            ste = cnx.prepareStatement(sql);
	            ste.setString(1,c.getCommentaire());
	            ste.setInt(2,e.getUserId());
	            ste.setInt(3,1);
	            ste.executeUpdate();
	            System.out.println("Commentaire Ajouté");
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }

	    }
	     

	     public void supprimer(Commentaire c) {
	         String requete = "DELETE FROM commentaire WHERE commentaire_id=?";
	         try {
	             ste = cnx.prepareStatement(requete);
	             ste.setInt(1, c.getId());
	             ste.executeUpdate();
	             System.out.println(c.getId() + "Supprimée !");
	         } catch (SQLException ex) {
	             System.err.println(ex.getMessage());
	         }
	     }
	     
	     public void update(Commentaire c, User e, Post p) {
	           try {
	              PreparedStatement pre =cnx.prepareStatement(
	                      "UPDATE commentaire SET `commentaire_content` = ? WHERE commentaire_id =? AND post_id=? AND user_id=?");
	           
	               System.out.println(c);
	                    
	    pre.setString(1, c.getCommentaire());
	    pre.setInt(2, c.getId());
	    pre.setInt(3,p.getId());
	    pre.setInt(3,e.getUserId());
	    pre.executeUpdate();
	    
	          } catch (SQLException ex) {
	              System.out.println(ex);
	          }
	    
	    }
	     
	    public ObservableList<Commentaire> afficherCommentaire() {
	    	 Post p=new Post() ;
	         ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
	          try {
	        	  String sql= "SELECT * FROM commentaire";
				PreparedStatement pre =cnx.prepareStatement(sql);
	          
	             Connection cnx;
	             Statement ste;
	             Datasource.getInstance();
				cnx = Datasource.getConnection();
	             
	              ste = cnx.createStatement();
	              
	      		ResultSet rs = pre.executeQuery();

	              while (rs.next()) {  
	                 Commentaire c = new Commentaire();
	                 c.setId(rs.getInt("commentaire_id"));
	                 User u = new User();
	                  u.setFirstName(rs.getString(2));
	                  c.setCommentaire(rs.getString("commentaire_content"));
	                  //System.out.print(c);
	                  commentaires.add(c);
	                  System.out.print(c);
	                  commentaires.add(c);
	                  System.out.println(c);
	                
	             }
	         } catch (SQLException ex) {
	             System.out.println(ex.getMessage());
	         }
	     
	     return  commentaires;
	       }
	     
	   /*  public Commentaire afficherCommentaire(int id) throws SQLException {
	    	 Commentaire c = new Commentaire();
	  		Connection con = Datasource.getConnection();
			User user = null;
	  		
	  		String sql = "SELECT * FROM commentaire WHERE post_id = ?";

	  		PreparedStatement ps = con.prepareStatement(sql);

	  		ps.setInt(1, id);

	  		ResultSet rs = ps.executeQuery();

	  		if (rs.next()) {
	  			int commentaire_id = rs.getInt("commentaire_id");
	  			String commentaire_content = rs.getString("commentaire_content");
	  			int user_id = rs.getInt("user_id");
	  			int post_id = rs.getInt("post_id");
	  			Date commentaire_date = rs.getDate("commentaire_date");
	  			
	  			
	  			c = new Commentaire(user, commentaire_content, commentaire_date, commentaire_id);

	  		}

	  		Datasource.closeResultSet(rs);
	  		Datasource.closePreparedStatement(ps);

	  		return c;
	  	}*/
	      

}
