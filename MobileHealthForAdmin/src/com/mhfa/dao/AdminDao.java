package com.mhfa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mhfa.connection.DBConnection;

public class AdminDao {

	public boolean isUsernameTaken(String username) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from patient where username = ?";
		Boolean result = false;

		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, username);
		
		rs = ps.executeQuery();

		if (rs != null && rs.next()) {
			con.close();
			result = true;
		}

		con.close();
		return result;
	}

	public boolean savePatientDetails(String username, String firstname, String lastname, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String query = "insert into patient (username, firstname, lastname, password) values(?,?,?,?)";

		con = DBConnection.getConnection();
		ps = con.prepareStatement(query);
		
		ps.setString(1, username);
		ps.setString(2, firstname);
		ps.setString(3, lastname);
		ps.setString(4, password);

		result = ps.executeUpdate();

		System.out.println("Result : " + result);

		con.close();

		if (result > 0) {
			return true;
		}

		return false;
	}
}