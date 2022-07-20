package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.Datasource;

import entity.Post;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostService implements PostDAO{
	Connection cnx;
	PreparedStatement ste;

	public PostService() {

		Datasource.getInstance();
		cnx = Datasource.getConnection();
	}

	public void ajouterPost(User e, Post p) {

		try {
			String sql = "insert into post (post_date,post_content,user_id,post_category,post_title,image) values (NOW(),?,?,?,?,?)";
			ste = cnx.prepareStatement(sql);
			ste.setString(1, p.getPost());
			ste.setInt(2, e.getUserId());
			ste.setString(3, "c");
			ste.setString(4, "c");
			ste.setString(5, p.getImage());
			ste.executeUpdate();
			System.out.println("Post Ajouté");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public int update(Post p) {
		try {
			PreparedStatement pre = cnx
					.prepareStatement("UPDATE post SET `post_category` = ?,`post_content` = ? WHERE post_id = ?");

			System.out.println(p);

			pre.setString(1, p.getCategory());
			pre.setString(2, p.getPost());
			pre.setInt(3, p.getId());
			pre.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return 0;

	}

	public ObservableList<Post> afficherPost() {
		ObservableList<Post> post = FXCollections.observableArrayList();
		try {
			String sql = "select * from post";
			Connection cnx;
			PreparedStatement ste;
			cnx = Datasource.getConnection();

			ste = cnx.prepareStatement(sql);
			// ste.setInt(e.getUserId());
			// System.out.println(id);
			ResultSet rs = ste.executeQuery();
			while (rs.next()) {
				Post p = new Post();
				p.setId(rs.getInt("post_id"));
				p.setCategory("post_category");
				p.setCaption("post_content");
				p.setImage("image");

				System.out.print(p);
				post.add(p);

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return post;
	}
	
	
	public List<Post> getAll() throws SQLException {

		Connection con = Datasource.getConnection();
		String sql = "SELECT * FROM post";

		List<Post> post = new ArrayList<>();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		Post p;
		User user = new User();
		while (rs.next()) {
			int postId = rs.getInt("post_id");
			int userId = rs.getInt("user_id");
			String category = rs.getString("post_category");
			String post_content = rs.getString("post_content");
			String image = rs.getString("image");
			Date date = rs.getDate("post_date");
			user= new UserDAOImpl().get(userId);
			//p = new Post(user, post_content, category, null, image);
			p = new Post(user, postId, category, date, post_content, image);
			post.add(p);
		}

		return post;
	}

	@Override
	public Post get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Post t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Post t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(Post t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
