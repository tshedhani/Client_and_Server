package com.access.models;

import java.util.List;

public class ForecastAndRollOffData {

	private String rollOffDate;
	
	private String enterpriseID;
	
	private List<ForeCast2Data> forecastData;

	public String getRollOffDate() {
		return rollOffDate;
	}

	public void setRollOffDate(String rollOffDate) {
		this.rollOffDate = rollOffDate;
	}

	public List<ForeCast2Data> getForecastData() {
		return forecastData;
	}

	public void setForecastData(List<ForeCast2Data> forecastData) {
		this.forecastData = forecastData;
	}

	public String getEnterpriseID() {
		return enterpriseID;
	}

	public void setEnterpriseID(String enterpriseID) {
		this.enterpriseID = enterpriseID;
	}
	
	
}
