/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Haifa
 */
public class Annonce {

    public Annonce(int annonce_id, String annonce_type, Date annonce_created_at, int total_price, String annonce_image, String annonce_content, String annonce_title, int userId, int jeu_id) {
        this.annonce_id = annonce_id;
        this.annonce_type = annonce_type;
        this.annonce_created_at = annonce_created_at;
        this.total_price = total_price;
        this.annonce_image = annonce_image;
        this.annonce_content = annonce_content;
        this.annonce_title = annonce_title;
        this.userId = userId;
        this.jeu_id = jeu_id;
    }
    
    private int annonce_id;
    private String annonce_type;
    private Date annonce_created_at;
    private int total_price;
    private String annonce_image;
    private String annonce_content;

  
    private String annonce_title;
    private int userId;
    private int jeu_id ;
    
     public Annonce() {

    }

    public int getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(int annonce_id) {
        this.annonce_id = annonce_id;
    }

    public String getAnnonce_type() {
        return annonce_type;
    }

    public void setAnnonce_type(String annonce_type) {
        this.annonce_type = annonce_type;
    }

    public Date getAnnonce_created_at() {
        return annonce_created_at;
    }

    public void setAnnonce_created_at(Date annonce_created_at) {
        this.annonce_created_at = annonce_created_at;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getAnnonce_image() {
        return annonce_image;
    }

    public void setAnnonce_image(String annonce_image) {
        this.annonce_image = annonce_image;
    }

    public String getAnnonce_content() {
        return annonce_content;
    }

    public void setAnnonce_content(String annonce_content) {
        this.annonce_content = annonce_content;
    }

    public String getAnnonce_title() {
        return annonce_title;
    }

    public void setAnnonce_title(String annonce_title) {
        this.annonce_title = annonce_title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJeu_id() {
        return jeu_id;
    }

    public void setJeu_id(int jeu_id) {
        this.jeu_id = jeu_id;
    }
    
    
      public Annonce(Annonce.AnnonceBuilder annonceBuilder) {

        this.annonce_id = annonceBuilder.annonce_id;
        this.annonce_type = annonceBuilder.annonce_type;
        this.annonce_created_at = annonceBuilder.annonce_created_at;
        this.total_price = annonceBuilder.total_price;
        this.annonce_image = annonceBuilder.annonce_image;
        this.annonce_content = annonceBuilder.annonce_content;
        this.annonce_title = annonceBuilder.annonce_title;
        this.jeu_id = annonceBuilder.jeu_id;
        this.userId = annonceBuilder.userId;
    }

    @Override
    public String toString() {
        return "Annonce{" + "annonce_id=" + annonce_id + ", annonce_type=" + annonce_type + ", annonce_created_at=" + annonce_created_at + ", total_price=" + total_price + ", annonce_image=" + annonce_image + ", annonce_content=" + annonce_content + ", annonce_title=" + annonce_title + ", userId=" + userId + ", jeu_id=" + jeu_id + '}';
    }
       
     public static class AnnonceBuilder {
          private int annonce_id;
    private String annonce_type;
    private Date annonce_created_at;
    private int total_price;
    private String annonce_image;
    private String annonce_content;

  
    private String annonce_title;
    private int userId;
    private int jeu_id ;

        
        public AnnonceBuilder() {
        }
         
        
         public AnnonceBuilder(String text, String text0, String text1, String text2, String text3, String text4,java.sql.Date valueOf) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public AnnonceBuilder annonce_id(int annonce_id) {
            this.annonce_id = annonce_id;
            return this;
        }

        public AnnonceBuilder annonce_type(String annonce_type) {
            this.annonce_type = annonce_type;
            return this;
        }

        public AnnonceBuilder annonce_created_at(Date annonce_created_at) {
            this.annonce_created_at = annonce_created_at;
            return this;
        }

     public AnnonceBuilder total_price(int total_price) {
            this.total_price = total_price;
            return this;
        }

        public AnnonceBuilder annonce_content(String annonce_content) {
            this.annonce_content = annonce_content;
            return this;
        }

        public AnnonceBuilder annonce_image(String annonce_image) {
            this.annonce_image = annonce_image;
            return this;
        }

        public AnnonceBuilder annonce_title(String annonce_title) {
            this.annonce_title = annonce_title;
            return this;
        }

        public AnnonceBuilder jeu_id(int jeu_id) {
            this.jeu_id = jeu_id;
            return this;
        }

      

        public AnnonceBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        // Return the finally constructed User object
       // Return the finally constructed User object
        public Annonce build() {
            Annonce annonce = new Annonce(this);
            validateUserObject(annonce);
            return annonce;
        }


        private void validateUserObject(Annonce annonce) {
            // Do some basic validations to check
            // if user object does not break any assumption of system
        }
    }
         
     }
     
        
    

    

    

