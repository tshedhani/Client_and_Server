package com.access.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.access.models.ContractData;
import com.access.models.ErrorModel;
import com.access.models.Information;
import com.access.utils.SQLCommands;
import com.access.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class PutContractsData implements SQLCommands {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Utilities utility;

	@RequestMapping(value = "/updateContractsData", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String putEmployeeData(@RequestBody ContractData contractData) throws JsonProcessingException {

		ErrorModel error = new ErrorModel();
		Information info = new Information();
		
		if (StringUtils.isBlank(contractData.getContractId().toString())) {
			error.setError("Contract ID cannot be null or empty");
			return utility.converJavaObjectToJsonString(error);
		}

		try {
			jdbcTemplate.execute(
					updateContractsTable + " ContractName ='" + contractData.getContractName() + "',ContractID = '"
							+ contractData.getContractId() + "',CDL = '" + contractData.getCdl()
							+ "'," + "OffshoreMD = '" + contractData.getOffshoreMD() + "',DUL = '"
							+ contractData.getDul() + "',Project_Lead = '" + contractData.getProjectLead()
							+ "',MasterClient = '" + contractData.getMasterClient() + "' WHERE ContractID = '"
							+ contractData.getContractId() + "'");
		} catch (Exception e) {
			e.printStackTrace();
			error.setError("Inserting to DB failed.");
			return utility.converJavaObjectToJsonString(error);
		}
		info.setInformation("Insert Success");
		return utility.converJavaObjectToJsonString(info);
	}
}
