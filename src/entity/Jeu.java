package entity;



public class Jeu {
    private int ID;
    private String Title;
    private String Categorie;
    private String url;
    private int userId;

    public Jeu() {
    	super();
    }

   
    public Jeu(JeuBuilder jeuBuilder) {
    	super();
    	this.ID=jeuBuilder.ID;
    	this.Title=jeuBuilder.Title;
    	this.Categorie=jeuBuilder.Categorie;
    	this.url=jeuBuilder.url;
    	this.userId=jeuBuilder.userId;
    	
    	
    }

   
	


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getCategorie() {
		return Categorie;
	}


	public void setCategorie(String categorie) {
		Categorie = categorie;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Jeu [ID=" + ID + ", Title=" + Title + ", Categorie=" + Categorie + ", url=" + url + ", userId=" + userId
				+ "]";
	}
	//JeuBuilder
	
	public static class JeuBuilder {
		private int ID;
	    private String Title;
	    private String Categorie;
	    private String url;
	    private int userId;
	    
		public JeuBuilder() {
			super();
		}

		public JeuBuilder ID(int ID) {
			this.ID = ID;
			return this;
		}

		public JeuBuilder Title(String Title) {
			this.Title = Title;
			return this;
		}
		
		public JeuBuilder Categorie(String Categorie) {
			this.Categorie = Categorie;
			return this;
		}

		public JeuBuilder url(String url) {
			this.url = url;
			return this;
		}

		public JeuBuilder userId(int userId) {
			this.userId = userId;
			return this;
		}
		// Return the finally constructed User object
		public Jeu build() {
			Jeu jeu = new Jeu(this);
			validateJeuObject(jeu);
			return jeu;
		}

		private void validateJeuObject(Jeu jeu) {
			// Do some basic validations to check
			// if user object does not break any assumption of system
		}

		@Override
		public String toString() {
			return "JeuBuilder [ID=" + ID + ", Title=" + Title + ", Categorie=" + Categorie + ", url=" + url
					+ ", userId=" + userId + "]";
		}

		
		
	    
	}

	public Jeu(int ID, String Title, String Categorie, String url,int user_id) {
        this.ID = ID;
        this.Title = Title;
        this.Categorie = Categorie;
        this.url = url;
        this.userId=user_id;
    }

	public Jeu(String Title, String Categorie, String url,int user_id) {
		this.Title = Title;
        this.Categorie = Categorie;
        this.url = url;
        this.userId=user_id;
	}
	public Jeu(int ID, String Title, String Categorie, String url) {
        this.ID = ID;
        this.Title = Title;
        this.Categorie = Categorie;
        this.url = url;
    }


	


	

	

}