package com.rhmpdm.proxy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.rhmpdm.bean.Patient;
import com.rhmpdm.bean.PatientDetails;
import com.rhmpdm.dao.PatientDataDao;

public class PatientDataProxy {

	PatientDataDao dao = new PatientDataDao();
	Gson gson = new Gson();

	public Map<String, String> authenticate(String username, String password) {

		boolean result = false;
		Map<String, String> resultMap = new HashMap<>();
		try {
			result = dao.authenticate(username, password);

			if (result) {

				Patient patient = dao.getPatientByUsername(username);

				if (patient != null) {
					resultMap.put("pId", String.valueOf(patient.getpId()));
					resultMap.put("firstName", patient.getFirstname());
					resultMap.put("lastName", patient.getLastname());
				}

				resultMap.put("result", "success");
				resultMap.put("message", "User authenticated successfully!!!");
			} else {
				resultMap.put("result", "error");
				resultMap.put("message", "User does not exist!!!");
			}

		} catch (SQLException e) {
			resultMap.put("result", "error");
			resultMap.put("message", "Some error occured : " + e.getMessage());
		}

		return resultMap;
	}

	public Map<String, Object> saveOrUpdateHeartBeat(String username, String heartBeat) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			boolean result = dao.saveOrUpdateHeartBeat(username, heartBeat);

			if (result) {
				resultMap.put("Result", "success");
				resultMap.put("message", "Data saved successfully for Heart Beat");
				return resultMap;
			}
			resultMap.put("Result", "Error");
			resultMap.put("Message", "Unable to insert/update");

		} catch (SQLException e) {
			resultMap.put("Result", "Error");
			resultMap.put("Message", "DB Connection error");
			resultMap.put("Exception", e.getMessage());
		}

		return resultMap;
	}

	public Map<String, Object> saveOrUpdateTemperature(String username, String temperature) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			boolean result = dao.saveOrUpdateTemperature(username, temperature);

			if (result) {
				resultMap.put("Result", "success");
				resultMap.put("message", "Data saved successfully for Temperature");
				return resultMap;
			}
			resultMap.put("Result", "Error");
			resultMap.put("Message", "Unable to insert/update");

		} catch (SQLException e) {
			resultMap.put("Result", "Error");
			resultMap.put("Message", "DB Connection error");
			resultMap.put("Exception", e.getMessage());
		}

		return resultMap;
	}

	public Map<String, String> getPatientCurrentStatus(String username) {
		Map<String, String> resultMap = new HashMap<>();

		PatientDetails patientDetails;
		try {

			int pId = dao.getPatientIdByUsername(username);
			if (pId != 0) {

				patientDetails = dao.getPatientCurrentStatus(pId);
				if (patientDetails != null) {
					resultMap.put("result", "success");
					resultMap.put("heartBeat", patientDetails.getHeartBeat());
					resultMap.put("temp", patientDetails.getTemp());
					resultMap.put("detailsDate", patientDetails.getDetailsDate());
				} else {
					resultMap.put("result", "error");
					resultMap.put("message", "No history exists for the you");
				}
			} else {
				resultMap.put("result", "error");
				resultMap.put("message", "Patient does not exist");
			}
		} catch (SQLException e) {
			resultMap.put("result", "error");
			resultMap.put("error", "Some DB error occurred : " + e.getMessage());
		}

		return resultMap;
	}

	public Map<String, String> getPatientHistory(String username) {
		Map<String, String> resultMap = new HashMap<>();

		PatientDetails patientDetails;
		try {
			int pId = dao.getPatientIdByUsername(username);

			if (pId != 0) {
				patientDetails = dao.getPatientHistory(pId);
				if (patientDetails != null) {
					resultMap.put("result", "success");
					resultMap.put("heartBeat", patientDetails.getHeartBeat());
					resultMap.put("temp", patientDetails.getTemp());
					resultMap.put("detailsDate", patientDetails.getDetailsDate());
				} else {
					resultMap.put("result", "error");
					resultMap.put("message", "No history exists for the you");
				}
			} else {
				resultMap.put("result", "error");
				resultMap.put("message", "Patient does not exist");
			}
		} catch (SQLException e) {
			resultMap.put("result", "error");
			resultMap.put("error", "Some DB error occurred : " + e.getMessage());
		}

		return resultMap;
	}

}