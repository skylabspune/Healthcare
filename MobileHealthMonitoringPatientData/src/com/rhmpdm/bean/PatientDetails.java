package com.rhmpdm.bean;

public class PatientDetails {
	private int pId;
	private String heartBeat;
	private String temp;
	private String detailsDate;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getDetailsDate() {
		return detailsDate;
	}

	public void setDetailsDate(String detailsDate) {
		this.detailsDate = detailsDate;
	}

	public String getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(String heartBeat) {
		this.heartBeat = heartBeat;
	}
}