package com.rhmpdm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rhmpdm.bean.Patient;
import com.rhmpdm.bean.PatientDetails;
import com.rhmpdm.connection.DBConnection;

public class PatientDataDao {

	public boolean authenticate(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		sql = "select * from patient where username = ? and password = ?";
		Boolean result = false;

		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);

		rs = ps.executeQuery();

		if (rs != null && rs.next()) {
			con.close();
			result = true;
		}

		con.close();
		return result;
	}

	public Patient getPatientByUsername(String username) throws SQLException {
		String sql = "select * from patient where username = ?";

		Patient patient = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = DBConnection.getConnection();

		ps = con.prepareStatement(sql);
		ps.setString(1, username);

		rs = ps.executeQuery();

		if (rs.next()) {
			patient = new Patient();
			patient.setpId(rs.getInt(1));
			patient.setFirstname(rs.getString(2));
			patient.setLastname(rs.getString(3));
		}
		return patient;
	}

	public boolean isPatientExists(String username) throws SQLException {
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

	public boolean saveOrUpdateHeartBeat(String username, String heartBeat) throws SQLException {

		Connection con = null;
		PreparedStatement psInsert = null;
		PreparedStatement psUpdate = null;
		PreparedStatement psTodayDate = null;
		int result = 0;
		ResultSet todayDateResult = null;
		String query = "";
		String updateQuery = "";
		String todayDateQuery = "";
		boolean isPatientHistoryExists = false;

		int pId = getPatientIdByUsername(username);

		if (pId != 0) {
			con = DBConnection.getConnection();
			isPatientHistoryExists = getPatientHistoryStatus(pId);

			if (isPatientHistoryExists) {
				updateQuery = "update patienthealthdetails set historyFlag = true where pId = ?";
				psUpdate = con.prepareStatement(updateQuery);

				psUpdate.setInt(1, pId);

				psUpdate.executeUpdate();
			}

			todayDateQuery = "select * from patienthealthdetails where pId = ? and detailsDate = ?";
			psTodayDate = con.prepareStatement(todayDateQuery);

			psTodayDate.setInt(1, pId);
			psTodayDate.setDate(2, new Date(new java.util.Date().getTime()));

			todayDateResult = psTodayDate.executeQuery();

			if (todayDateResult != null && todayDateResult.next()) {
				query = "update patienthealthdetails set heartBeat = ?, historyFlag = ? where pId = ? and detailsDate = ?";
			} else {
				query = "insert into patienthealthdetails(heartBeat, historyFlag, pId, detailsDate) values(?, ?, ?, ?)";
			}

			psInsert = con.prepareStatement(query);

			psInsert.setString(1, heartBeat);
			psInsert.setBoolean(2, false);
			psInsert.setInt(3, pId);
			psInsert.setDate(4, new Date(new java.util.Date().getTime()));

			result = psInsert.executeUpdate();
			
			con.close();
		}

		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean saveOrUpdateTemperature(String username, String temperature) throws SQLException {

		Connection con = null;
		PreparedStatement psInsert = null;
		PreparedStatement psUpdate = null;
		PreparedStatement psTodayDate = null;
		int result = 0;
		ResultSet todayDateResult = null;
		String query = "";
		String updateQuery = "";
		String todayDateQuery = "";
		boolean isPatientHistoryExists = false;

		int pId = getPatientIdByUsername(username);

		if (pId != 0) {
			con = DBConnection.getConnection();
			isPatientHistoryExists = getPatientHistoryStatus(pId);

			if (isPatientHistoryExists) {
				updateQuery = "update patienthealthdetails set historyFlag = true where pId = ?";
				psUpdate = con.prepareStatement(updateQuery);

				psUpdate.setInt(1, pId);

				psUpdate.executeUpdate();
			}

			todayDateQuery = "select * from patienthealthdetails where pId = ? and detailsDate = ?";
			psTodayDate = con.prepareStatement(todayDateQuery);

			psTodayDate.setInt(1, pId);
			psTodayDate.setDate(2, new Date(new java.util.Date().getTime()));

			todayDateResult = psTodayDate.executeQuery();

			if (todayDateResult != null && todayDateResult.next()) {
				query = "update patienthealthdetails set temperature = ?, historyFlag = ? where pId = ? and detailsDate = ?";
			} else {
				query = "insert into patienthealthdetails(temperature, historyFlag, pId, detailsDate) values(?, ?, ?, ?)";
			}

			psInsert = con.prepareStatement(query);

			psInsert.setString(1, temperature);
			psInsert.setBoolean(2, false);
			psInsert.setInt(3, pId);
			psInsert.setDate(4, new Date(new java.util.Date().getTime()));

			result = psInsert.executeUpdate();

			con.close();
		}

		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getPatientIdByUsername(String username) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select pId from patient where username = ?";
		int pId = 0;

		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, username);

		rs = ps.executeQuery();

		if (rs.next()) {
			pId = rs.getInt(1);
		}

		con.close();

		return pId;
	}

	public boolean getPatientHistoryStatus(int pId) throws SQLException {
		Connection con = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs3 = null;
		PreparedStatement ps3 = null;
		int rs2 = 0;
		int count = 0;
		Date maxDate = null;

		String sql1 = "select count(*) from patienthealthdetails where pId = ? and detailsDate < ?";
		String sql2 = "delete from patienthealthdetails where pId = ? and detailsDate <> ?";
		String sql3 = "select max(detailsDate) from patienthealthdetails p2 where p2.pId = ?";

		con = DBConnection.getConnection();
		ps1 = con.prepareStatement(sql1);

		ps1.setInt(1, pId);
		ps1.setDate(2, new Date(new java.util.Date().getTime()));

		rs1 = ps1.executeQuery();

		if (rs1 != null && rs1.next()) {
			count = rs1.getInt(1);
		}

		if (count > 1) {
			ps3 = con.prepareStatement(sql3);
			ps3.setInt(1, pId);

			rs3 = ps3.executeQuery();

			while (rs3.next()) {
				maxDate = rs3.getDate(1);
			}

			if (maxDate != null) {
				ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, pId);
				ps2.setDate(2, maxDate);
				rs2 = ps2.executeUpdate();
				if (rs2 == 1) {
					System.out.println("Record Deleted");
					System.out.println("check");
					count--;
				}
			}

		}

		con.close();

		if (count == 1) {
			return true;
		}

		return false;
	}

	public PatientDetails getPatientCurrentStatus(int pId) throws SQLException {

		String sql = "select * from patienthealthdetails where pId = ? and historyFlag = false";
		PatientDetails patientDetails = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = DBConnection.getConnection();

		ps = con.prepareStatement(sql);

		ps.setInt(1, pId);

		rs = ps.executeQuery();

		if (rs != null && rs.next()) {
			patientDetails = new PatientDetails();
			patientDetails.setHeartBeat(rs.getString(3));
			patientDetails.setTemp(rs.getString(4));
			patientDetails.setDetailsDate(rs.getDate(5).toString());
		}

		con.close();

		return patientDetails;
	}

	public PatientDetails getPatientHistory(int pId) throws SQLException {
		String sql = "select * from patienthealthdetails where pId = ? and historyFlag = true";
		PatientDetails patientDetails = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = DBConnection.getConnection();

		ps = con.prepareStatement(sql);

		ps.setInt(1, pId);

		rs = ps.executeQuery();

		if (rs != null && rs.next()) {
			patientDetails = new PatientDetails();
			patientDetails.setHeartBeat(String.valueOf(rs.getInt(3)));
			patientDetails.setTemp(rs.getString(4));
			patientDetails.setDetailsDate(rs.getDate(5).toString());
		}

		return patientDetails;
	}
}