package com.mhfa.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mhfa.dao.AdminDao;

public class AdminService {
	AdminDao adminDao = new AdminDao();

	public Map<String, String> addPatient(String username, String password, String firstname, String lastname) {
		Map<String, String> resultMap = new HashMap<String, String>();

		try {
			boolean isUsernameTaken = adminDao.isUsernameTaken(username);

			if (isUsernameTaken) {
				resultMap.put("result", "error");
				resultMap.put("message", "Username already taken, please try with another username!!!");
			} else {
				boolean employeeAdded = adminDao.savePatientDetails(username, firstname, lastname, password);

				if (employeeAdded) {
					resultMap.put("result", "success");
					resultMap.put("message", "Patient added successfully!!!");
				} else {
					resultMap.put("result", "error");
					resultMap.put("message", "Unable to add the employee!!!");
				}
			}
		} catch (SQLException e) {
			resultMap.put("result", "error");
			resultMap.put("message", "Some error occured : " + e.getMessage());
		}

		return resultMap;
	}
}