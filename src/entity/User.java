package entity;

public class User {
	// Properties
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String etatCompte;
	private String password;
	private String role;
	private String user_image;

	// Constructors
	public User() {
		super();
	}

	public User(UserBuilder userBuilder) {
		super();
		this.userId = userBuilder.userId;
		this.firstName = userBuilder.firstName;
		this.lastName = userBuilder.lastName;
		this.email = userBuilder.email;
		this.etatCompte = userBuilder.etatCompte;
		this.password = userBuilder.password;
		this.role = userBuilder.role;
		this.user_image = userBuilder.user_image;
	}

	// Getters and setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEtatCompte() {
		return etatCompte;
	}

	public void setEtatCompte(String etatCompte) {
		this.etatCompte = etatCompte;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getUserImage() {
		return user_image;
	}

	public void setUserImage(String userImg) {
		this.user_image = userImg;
	}
	// Methods
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", etatCompte=" + etatCompte + "]";
	}

	// User builder
	public static class UserBuilder {
		private int userId;
		private String firstName;
		private String lastName;
		private String email;
		private String etatCompte;
		private String password;
		private String role;
		private String user_image;

		public UserBuilder() {
			super();
		}

		public UserBuilder userId(int userId) {
			this.userId = userId;
			return this;
		}

		public UserBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public UserBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}

		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}

		public UserBuilder etatCompte(String etatCompte) {
			this.etatCompte = etatCompte;
			return this;
		}
		public UserBuilder role(String role) {
			this.role = role;
			return this;
		}

		public UserBuilder user_image(String user_iamge) {
			this.user_image = user_iamge;
			return this;
		}

		// Return the finally constructed User object
		public User build() {
			User user = new User(this);
			validateUserObject(user);
			return user;
		}

		private void validateUserObject(User user) {
			// Do some basic validations to check
			// if user object does not break any assumption of system
		}
	}
}