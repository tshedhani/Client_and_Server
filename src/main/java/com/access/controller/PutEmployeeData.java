package com.access.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.access.models.EmployeeData;
import com.access.models.ErrorModel;
import com.access.models.Information;
import com.access.utils.SQLCommands;
import com.access.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class PutEmployeeData implements SQLCommands {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Utilities utility;

	@RequestMapping(value = "/updateEmployeeData", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String putEmployeeData(@RequestBody EmployeeData employeeData) throws JsonProcessingException {

		ErrorModel error = new ErrorModel();
		Information info = new Information();

		try {

			jdbcTemplate.execute(updateEmployeeTable + " SAPID ='" + employeeData.getSapID() + "',ContractID = '"
					+ employeeData.getContractID() + "',EnterpriseID = '" + employeeData.getEnterpriseID() + "',"
					+ "EmployeeName = '" + employeeData.getEmployeeName() + "',Level = '" + employeeData.getLevel()
					+ "',AssignedCapacity = '" + employeeData.getAssignedCapacity() + "',RollOnDate = '"
					+ utility.stringToTimestamp(employeeData.getRollOnDate()) + "',RollOffDate = '"
					+ utility.stringToTimestamp(employeeData.getRollOffDate()) + "',DeskAssigned = '"
					+ employeeData.getDeskAssigned() + "',LaptopID = '" + employeeData.getLaptopID() + "',LaptopSN = '"
					+ employeeData.getLaptopSN() + "',PrimarySkill = '" + employeeData.getPrimarySkill()
					+ "',SecondarySkill = '" + employeeData.getSecondarySkill() + "',ProficiencyLevelPrimary = '"
					+ employeeData.getProficiencyLevelPrimary() + "',ProficiencyLevelSecondary = '"
					+ employeeData.getProficiencyLevelSecondary() + "' WHERE" + " EnterpriseID = '"
					+ employeeData.getEnterpriseID() + "'");

		} catch (ParseException e) {
			error.setError("Date format can only be yyyy-MM-dd");
			return utility.converJavaObjectToJsonString(error);
		} catch (Exception e) {
			error.setError(e.getLocalizedMessage());
			return utility.converJavaObjectToJsonString(error);
		}

		info.setInformation("Insert Success");
		return utility.converJavaObjectToJsonString(info);
	}
}
