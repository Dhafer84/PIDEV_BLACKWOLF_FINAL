package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Jeu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.Datasource;


public class JeuDAOImpl implements JeuDAO {
	//public static Jeu getimage;
	Connection con = Datasource.getConnection();

	@Override
	public Jeu get(int id) throws SQLException {
		Connection con = Datasource.getConnection();
		Jeu Jeu = null;

		String sql = "SELECT jeu_id, jeu_name, jeu_category, jeu_image,user_id FROM Jeu WHERE jeu_id = ?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int JeuId = rs.getInt("jeu_id");
			String name = rs.getString("jeu_name");
			String cat = rs.getString("jeu_category");
			String img = rs.getString("jeu_image");
			int userId = rs.getInt("user_id");

			Jeu = new Jeu(JeuId, name, cat, img,userId);
		}

		Datasource.closeResultSet(rs);
		Datasource.closePreparedStatement(ps);
		// Datasource.closeConnection(con);

		return Jeu;
	}

	@Override
	public List<Jeu> getAll() throws SQLException {
		Connection con = Datasource.getConnection();
		String sql = "SELECT jeu_id, jeu_name, jeu_category, jeu_image,user_id FROM jeu";

		List<Jeu> jeu = new ArrayList<>();

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int JeuId = rs.getInt("jeu_id");
			String name = rs.getString("jeu_name");
			String cat = rs.getString("jeu_category");
			String img = rs.getString("jeu_image");
			int userId = rs.getInt("user_id");

			Jeu Jeu = new Jeu(JeuId, name, cat, img,userId);

			jeu.add(Jeu);
		}

		return jeu;
	}

	@Override
	public int save(Jeu t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Jeu jeu) throws SQLException {
		Connection con = Datasource.getConnection();

		String sql = "INSERT INTO jeu ( jeu_name, jeu_category, jeu_image,user_id) VALUES (?, ?, ?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, jeu.getTitle());
		ps.setString(2, jeu.getCategorie());
		ps.setString(3, jeu.getUrl());
		ps.setInt(4, jeu.getUserId());

		int result = ps.executeUpdate();

		Datasource.closePreparedStatement(ps);
		// Datasource.closeConnection(con);

		return result;
	}

	@Override
	public int update(Jeu t) throws SQLException {
		Connection connection = Datasource.getConnection();

		String sql = "UPDATE jeu set jeu_id = ?, jeu_name = ?, jeu_category = ?, jeu_image = ?, user_id=? where jeu_id = ?";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, t.getID());
		ps.setString(2, t.getTitle());
		ps.setString(3, t.getCategorie());
		ps.setString(4, t.getUrl());
		ps.setInt(5, t.getUserId());
		ps.setInt(6, t.getID());

		int result = ps.executeUpdate();

		Datasource.closePreparedStatement(ps);
		// Datasource.closeConnection(connection);

		return result;
	}

	@Override
	public int delete(Jeu t) throws SQLException {
		Connection connection = Datasource.getConnection();
		String sql = "DELETE FROM jeu WHERE jeu_id = ?";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, t.getID());

		int result = ps.executeUpdate();

		Datasource.closePreparedStatement(ps);
		// Datasource.closeConnection(connection);

		return result;
	}

	public ObservableList<Jeu> data() {

		ObservableList<Jeu> datax = FXCollections.observableArrayList();

		try {
			String sql = "select * from jeu";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Jeu Jeu = new Jeu();

				Jeu.setID(rs.getInt(1));
				Jeu.setTitle(rs.getString(2));
				Jeu.setCategorie(rs.getString(3));
				Jeu.setUrl(rs.getString(4));
				Jeu.setUserId(rs.getInt(5));
				// ps.setName(rs.getString(2));
				// c.setCommentaire(rs.getString("text"));
				System.out.print(Jeu);
				datax.add(Jeu);
				System.out.println(Jeu);

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return datax;

	}

	public Object getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
