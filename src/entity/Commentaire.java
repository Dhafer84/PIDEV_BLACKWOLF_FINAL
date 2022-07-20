package entity;
import java.util.Date;


public class Commentaire {
	private String name;
	private User user;
    private String commentaire;
    private Date date;
    private int id;
    

	public Commentaire(User user, String commentaire, Date date, int id) {
		super();
		this.user = user;
		this.commentaire = commentaire;
		this.date = date;
		this.id = id;
	}

	public Commentaire(String name, User user, String commentaire, Date date, int id) {
		super();
		this.name = name;
		this.user = user;
		this.commentaire = commentaire;
		this.date = date;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommentaire() {
        return commentaire;
    }

    public Commentaire(String name, String commentaire) {
		super();
		this.name = name;
		this.commentaire = commentaire;
	}

	public Commentaire() {
	}

	public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Commentaire [name=" + name + ", user=" + user + ", commentaire=" + commentaire + ", date=" + date
				+ "]";
	}
    
}
