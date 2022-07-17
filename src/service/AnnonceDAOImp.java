/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Annonce;


import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import java.util.List;
import utils.Datasource;

/**
 *
 * @author user
 */
public class AnnonceDAOImp implements AnnonceDAO {
    // public static Annonce annonce;
     
     public static User loggedInJeu;

    @Override
    public Annonce get(int id) throws SQLException {
     Connection con = Datasource.getConnection();
        Annonce annonce = null;

        String sql = "SELECT * FROM annonce WHERE annonce_id   = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int annonce_id = rs.getInt("annonce_id");
            String annonce_type = rs.getString("annonce_type");
            Date annonce_created_at = rs.getDate("annonce_created_at");
            String annonce_title = rs.getString("annonce_title");
            String annonce_content = rs.getString("annonce_content");
            int total_price = rs.getInt("total_price");
            int jeu_id = rs.getInt("jeu_id");
            String annonce_image = rs.getString("annonce_image");
            int userId = rs.getInt("user_id");

            annonce = new Annonce.AnnonceBuilder()
                    .annonce_id(annonce_id)
                    .annonce_type(annonce_type)
                    .annonce_created_at(annonce_created_at)
                    .annonce_title(annonce_title)
                    .annonce_content(annonce_content)
                     .total_price(total_price)
                    
                   
                    .jeu_id(jeu_id)
                    .annonce_image(annonce_image).userId(userId).build();
        }

        Datasource.closeResultSet(rs);
        Datasource.closePreparedStatement(ps);

        return annonce;    }

    @Override
    public List<Annonce> getAll() throws SQLException {
        Connection con = Datasource.getConnection();
        String sql = "SELECT * FROM annonce";

        List<Annonce> annonces = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        Annonce annonce;
        while (rs.next()) {
              int annonce_id = rs.getInt("annonce_id");
            String annonce_type = rs.getString("annonce_type");
           Date annonce_created_at = rs.getDate("annonce_created_at");
            String annonce_title = rs.getString("annonce_title");
            String annonce_content = rs.getString("annonce_content");
            int total_price = rs.getInt("total_price");
            int jeu_id = rs.getInt("jeu_id");
            String annonce_image = rs.getString("annonce_image");
            int userId = rs.getInt("user_id");

            annonce = new Annonce.AnnonceBuilder()
                    .annonce_id(annonce_id)
                    .annonce_type(annonce_type)
                    .annonce_created_at(annonce_created_at)
                    .annonce_title(annonce_title)
                    .annonce_content(annonce_content)
                     .total_price(total_price)
                    
                   .annonce_image(annonce_image)
                    .jeu_id(jeu_id)
                    
                    .userId(userId).build();
             annonces.add(annonce);
        }
           
        

        return annonces;
    }

    @Override
    public int save(Annonce annonce) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Annonce annonce) throws SQLException {
         Connection connection = Datasource.getConnection();
        int result = -1;
        if (connection != null) {
            String insertQuery = "INSERT INTO annonce (annonce_type, total_price,annonce_image, annonce_created_at,annonce_title, annonce_content, user_id ,jeu_id ) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement insertps = connection.prepareStatement(insertQuery);

            insertps.setString(1, annonce.getAnnonce_type());
            insertps.setInt(2, annonce.getTotal_price());
            insertps.setString(3, annonce.getAnnonce_image());
            insertps.setDate(4, annonce.getAnnonce_created_at());
            insertps.setString(5, annonce.getAnnonce_title());
            insertps.setString(6, annonce.getAnnonce_content());
            
            insertps.setInt(7, annonce.getUserId());
            
            insertps.setInt(8,  annonce.getJeu_id());

            result = insertps.executeUpdate();

            Datasource.closePreparedStatement(insertps);
        }

        return result;
    }

    @Override
    public int update(Annonce annonce) throws SQLException {
       Connection connection = Datasource.getConnection();
        int result = -1;
        if (connection != null) {
           
            String updateQuery = " UPDATE annonce "
                    + " SET annonce_type = ?, total_price = ?, annonce_image = ?, annonce_created_at = ?, annonce_title = ?, annonce_content = ?, user_id = ?, jeu_id = ? "
                    + " WHERE annonce_id = ?";
            PreparedStatement updateps = connection.prepareStatement(updateQuery);
          
            
            
             updateps.setString(1, annonce.getAnnonce_type());
            updateps.setInt(2, annonce.getTotal_price());
            updateps.setString(3, annonce.getAnnonce_image());
            updateps.setDate(4, (java.sql.Date) annonce.getAnnonce_created_at());
            updateps.setString(5, annonce.getAnnonce_title());
            updateps.setString(6, annonce.getAnnonce_content());
            
            updateps.setInt(7, annonce.getUserId());
            
            updateps.setInt(8, annonce.getJeu_id());
             updateps.setInt(9, annonce.getAnnonce_id());
       

            result = updateps.executeUpdate();
            Datasource.closePreparedStatement(updateps);
        }
       
        return result;
    }

    @Override
    public int delete(Annonce annonce) throws SQLException {
  Connection connection = Datasource.getConnection();

        String sql = "DELETE FROM annonce WHERE annonce_id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, annonce.getAnnonce_id());

        int result = ps.executeUpdate();

        Datasource.closePreparedStatement(ps);

        return result;    }

    

}
