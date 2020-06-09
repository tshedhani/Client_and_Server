package com.access.models;

import java.io.Serializable;

public class ForeCast2Data implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String date;
	private Long number;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "ForeCast2Data [date=" + date + ", number=" + number + "]";
	}
}
