package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import utils.Datasource;
import utils.PasswordManager;

public class AuthService {
	public static User loggedInUser;

	/*
	 * 
	 * Sign up function responsible for creating user account 
	 * Input : User(first_name, last_name, email, password) 
	 * Output : integer as isOperation
	 * true : No Errors, account has been created false : Error, operation failed
	 */

	public static boolean signUp(User user) throws SQLException {
		Connection con = Datasource.getConnection();
		boolean isOperationSuccessful = false;
		if (con != null) {
			ResultSet rs;
			String selectQuery = "SELECT * FROM USER WHERE email = ?";
			PreparedStatement selectps = con.prepareStatement(selectQuery);
			selectps.setString(1, user.getEmail());
			rs = selectps.executeQuery();

			if (rs.isBeforeFirst()) {
				System.out.println("User already exists !");
			} else {
				// Hashing password before insertion
				user.setPassword(PasswordManager.SecurePassword(user.getPassword()));
				// Persisting user to DB
				new UserDAOImpl().insert(user);
				isOperationSuccessful = true;
			}

			Datasource.closeResultSet(rs);
			Datasource.closePreparedStatement(selectps);
		}
		return isOperationSuccessful;
	}

	/*
	 * 
	 * Log in function responsible for checking user credentials. 
	 * Input : email,password 
	 * Output : integer as isOperation 
	 * true : No Errors, correct,credentials 
	 * false : Error, wrong credentials
	 */
	public static boolean logInUser(String email, String password) throws SQLException {
		Connection con = Datasource.getConnection();
		boolean isOperationSuccessful = false;

		if (con != null) {
			ResultSet rs;
			String selectQuery = "SELECT * FROM USER WHERE email = ?";
			PreparedStatement selectps = con.prepareStatement(selectQuery);
			selectps.setString(1, email);
			rs = selectps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("User not found in the data base !");
			} else {
				while (rs.next()) {
					String retrivedPassword = rs.getString("password");
					if (PasswordManager.verifyPassword(password, retrivedPassword)) {
						System.out.println("You are logged in !");
						int userId = rs.getInt("user_id");
						String first_name = rs.getString("first_name");
						String last_name = rs.getString("last_name");
						String user_email = rs.getString("email");
						String etatCompte = rs.getString("etat_compte");
						String role = rs.getString("role");
						String user_image = rs.getString("user_image");

						AuthService.loggedInUser = new User.UserBuilder().userId(userId).firstName(first_name)
								.lastName(last_name).email(user_email).etatCompte(etatCompte).role(role).user_image(user_image).build();

						isOperationSuccessful = true;
					} else {
						System.out.println("Password did not match !");
					}
				}
			}

			Datasource.closeResultSet(rs);
			Datasource.closePreparedStatement(selectps);
		}
		return isOperationSuccessful;
	}

	/*
	 * 
	 * Logout function responsible for clearing the user state
	 */
	public static void logout() {
		loggedInUser = null;
	}
}
