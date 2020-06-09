package com.access.models;

import java.io.Serializable;

public class ForeCastData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseId;
	private String date;
	private Long number;
	
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
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
		return "ForeCastData [enterpriseId=" + enterpriseId + ", date=" + date + ", number=" + number + "]";
	}
}
