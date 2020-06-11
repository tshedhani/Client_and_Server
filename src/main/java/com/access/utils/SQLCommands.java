package com.access.utils;

public interface SQLCommands {

	
	// Select statements
	String selectFromEmployeeTable = "SELECT * FROM ProjectEmployee where EnterpriseID =";
	String selectFromContractTable = "SELECT * FROM Contracts where ContractId =";
	String selectFromForecastTable = "SELECT * FROM Forecast";
	String selectFromForecastTablewithEnterpriseID = "SELECT * FROM Forecast where EnterpriseID =";
	String getRollOffDateFromEmployeeTable = "SELECT RollOffDate FROM ProjectEmployee where EnterpriseID =";
	
	// Insert statement
	String insertIntoForecastTable = "INSERT into Forecast (EnterpriseID, MMYYYY, Hours) values";
	
	// Update Statements
	String updateForecastTable = "UPDATE Forecast set Hours =";
	String updateEmployeeTable = "UPDATE ProjectEmployee set";
	String updateContractsTable = "UPDATE Contracts set";	
}
