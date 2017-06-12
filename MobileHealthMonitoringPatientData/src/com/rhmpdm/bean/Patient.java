package com.rhmpdm.bean;

public class Patient {
	private String firstname;
	private String lastname;
	private int assignedDoctorId;
	private String assignedDoctor;
	private int pId;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAssignedDoctorId() {
		return assignedDoctorId;
	}

	public void setAssignedDoctorId(int assignedDoctorId) {
		this.assignedDoctorId = assignedDoctorId;
	}

	public String getAssignedDoctor() {
		return assignedDoctor;
	}

	public void setAssignedDoctor(String assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}

}
