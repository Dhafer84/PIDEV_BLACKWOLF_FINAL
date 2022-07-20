package entity;





import java.util.Date;

public class Post {
    private User user;
    private int id;
    private String Post;
    private String category;
    private PostAudience audience;
    private Date date;
    private String post_content;
    private String image;
    private int totalReactions;
    public Post(User user, int id, String category, Date date, String post_content, String image) {
		super();
		this.user = user;
		this.id = id;
		this.category = category;
		this.date = date;
		this.post_content = post_content;
		this.image = image;
	}

	private int nbComments;
    private int nbShares;
  
    
    public void setDate(Date date) {
		this.date = date;
	}

	public Post(User user, String post, String category, Date date, String image) {
		super();
		this.user = user;
		Post = post;
		this.category = category;
		this.date = date;
		this.image = image;
	}

	public Post() {
		super();
	}

	public Post(User user, String post, String category, Date date) {
		super();
		this.user = user;
		Post = post;
		this.category = category;
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPost() {
		return Post;
	}

	public void setPost(String post) {
		Post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostAudience getAudience() {
        return audience;
    }

    public void setAudience(PostAudience audience) {
        this.audience = audience;
    }

    public Date getDate() {
        return date;
    }


    public String getCaption() {
        return post_content;
    }

    public void setCaption(String caption) {
        this.post_content = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(int totalReactions) {
        this.totalReactions = totalReactions;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getNbShares() {
        return nbShares;
    }

    public void setNbShares(int nbShares) {
        this.nbShares = nbShares;
    }

	@Override
	public String toString() {
		return "Post [user=" + user + ", audience=" + audience + ", date=" + date + ", caption=" + post_content + ", image="
				+ image + ", totalReactions=" + totalReactions + ", nbComments=" + nbComments + ", nbShares=" + nbShares
				+ "]";
	}
    
}
