package com.nirvacsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nirvacsh.models.User;


/**
 * UserDao.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author Vlad Zazhyrko
 */
public class UserDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertUser(User user) throws SQLException {
		String sql = "INSERT INTO user (name, password, role) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getRole());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<User> listAllUsers() throws SQLException {
		List<User> listUser = new ArrayList<>();
		
		String sql = "SELECT * FROM user";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			
			User user = new User(id, name, password, role);
			listUser.add(user);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listUser;
	}
	
	public boolean deleteUser(User user) throws SQLException {
		String sql = "DELETE FROM user where id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, user.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateUser(User user) throws SQLException {
		String sql = "UPDATE user SET name = ?, password = ?, role = ?";
		sql += " WHERE id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getRole());
		statement.setInt(4, user.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public User getUserById(int id) throws SQLException {
		User user = null;
		String sql = "SELECT * FROM user WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			
			user = new User(id, name, password, role);
		}
		
		resultSet.close();
		statement.close();
		
		return user;
	}
}
