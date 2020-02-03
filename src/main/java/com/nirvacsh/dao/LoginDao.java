package com.nirvacsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	
	String sql = "select * from users where uname=? and pass=? ";
	String url = "jdbc:postgresql://localhost:5432/nirvacsh";
	String username = "postgres";
	String password = "root";
	
	public boolean checkLogin(String uname, String pass) {
		
		try {
			
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, uname);
		st.setString(2, pass);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			
			return true;
		}
		
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
