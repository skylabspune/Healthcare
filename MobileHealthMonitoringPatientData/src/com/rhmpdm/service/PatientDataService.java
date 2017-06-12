package com.rhmpdm.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.rhmpdm.dto.PatientDto;
import com.rhmpdm.proxy.PatientDataProxy;

@Path("/patientoperations")
public class PatientDataService {

	Gson gson = new Gson();
	PatientDataProxy proxy = new PatientDataProxy();

	@POST
	@Path("/patientlogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response patientLogin(PatientDto patientDto) {
		Map<String, String> result = new HashMap<>();

		result = proxy.authenticate(patientDto.getUsername(), patientDto.getPassword());

		if (result.containsValue("success")) {
			return Response.ok(200).entity(gson.toJson(result)).build();
		}

		return Response.status(500).entity(gson.toJson(result)).build();

	}

	@POST
	@Path("/saveorupdateheartbeat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOrUpdateHeartBeat(@QueryParam("username") String username, @QueryParam("heartBeat") String heartBeat) {
		Map<String, Object> result = new HashMap<>();

		result = proxy.saveOrUpdateHeartBeat(username, heartBeat);

		if (result.containsValue("success")) {
			return Response.ok(200).entity(gson.toJson(result)).build();
		}

		return Response.status(500).entity(gson.toJson(result)).build();

	}

	@POST
	@Path("/saveorupdatepatienttemp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOrUpdateTemperature(@QueryParam("username") String username, @QueryParam("temperature") String temperature) {
		Map<String, Object> result = new HashMap<>();

		result = proxy.saveOrUpdateTemperature(username, temperature);

		if (result.containsValue("success")) {
			return Response.ok(200).entity(gson.toJson(result)).build();
		}

		return Response.status(500).entity(gson.toJson(result)).build();

	}

	@GET
	@Path("/getpatienthistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPatientHistory(@QueryParam("username") String username) {
		Map<String, String> result = new HashMap<>();

		result = proxy.getPatientHistory(username);

		if (result.containsValue("success")) {
			return Response.ok(200).entity(gson.toJson(result)).build();
		}

		return Response.status(500).entity(gson.toJson(result)).build();

	}

	@GET
	@Path("/getpatientcurrentstatus")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPatientCurrentStatus(@QueryParam("username") String username) {
		Map<String, String> result = new HashMap<>();

		result = proxy.getPatientCurrentStatus(username);

		if (result.containsValue("success")) {
			return Response.ok(200).entity(gson.toJson(result)).build();
		}

		return Response.status(500).entity(gson.toJson(result)).build();

	}
}