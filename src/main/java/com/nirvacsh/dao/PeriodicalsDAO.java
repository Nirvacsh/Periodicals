package com.nirvacsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nirvacsh.models.Periodicals;

public class PeriodicalsDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public PeriodicalsDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean insertPeriodicals(Periodicals periodicals) throws SQLException {
		String sql = "INSERT INTO periodicals (pid, title, price) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, periodicals.getTitle());
		statement.setFloat(2, periodicals.getPrice());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Periodicals> listAllPeriodicals() throws SQLException {
		List<Periodicals> listPeriodicials = new ArrayList<>();
		
		String sql = "SELECT * FROM periodicals";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int pid = resultSet.getInt("pid");
			String title = resultSet.getString("title");
			float price = resultSet.getFloat("price");
			
			Periodicals periodical = new Periodicals(pid, title, price);
			listPeriodicials.add(periodical);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listPeriodicials;
	}
	
	public boolean deletePeriodical(Periodicals periodical) throws SQLException {
		String sql = "DELETE FROM periodicals where pid = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, periodical.getPid());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updatePeriodicals(Periodicals periodical) throws SQLException {
		String sql = "UPDATE periodicals SET title = ?, price = ? ";
		sql += " WHERE pid = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, periodical.getTitle());
		statement.setFloat(2, periodical.getPrice());
		statement.setInt(3, periodical.getPid());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Periodicals getPeriodicalById(int pid) throws SQLException {
		Periodicals periodical = null;
		String sql = "SELECT * FROM periodicals WHERE pid = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, pid);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String title = resultSet.getString("title");
			float price = resultSet.getFloat("price");

			
			periodical = new Periodicals(pid, title, price);
		}
		
		resultSet.close();
		statement.close();
		
		return periodical;
	}
}
