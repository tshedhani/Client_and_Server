package com.access.models;

import java.io.Serializable;

public class Information implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String information;

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}

