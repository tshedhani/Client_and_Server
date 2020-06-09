package com.access.models;

import java.io.Serializable;

public class ErrorModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
