package com.access.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.access.mappers.ContractDataRowMapper;
import com.access.mappers.EmployeeDataRowMapper;
import com.access.mappers.ForeCastDataRowMapper;
import com.access.models.ContractData;
import com.access.models.EmployeeData;
import com.access.models.ErrorModel;
import com.access.models.ForeCast2Data;
import com.access.models.ForeCastData;
import com.access.models.ForecastAndRollOffData;
import com.access.utils.SQLCommands;
import com.access.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class GetEmployeeData implements SQLCommands {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Utilities utility;

	@RequestMapping(value = "/employee/{enterpriseId}", method = RequestMethod.GET)
	public String getEmployeeDataByEnterpriseId(@PathVariable String enterpriseId) throws JsonProcessingException {
		
		ErrorModel error = new ErrorModel();

		if (StringUtils.isBlank(enterpriseId)) {
			error.setError("EnterpriseId parameter cannot be null");
			return utility.converJavaObjectToJsonString(error);
		}
		
		List<EmployeeData> employeeDataList = jdbcTemplate.query(
				selectFromEmployeeTable + "'" + enterpriseId + "'", new EmployeeDataRowMapper());
		
		if (CollectionUtils.isEmpty(employeeDataList)) {
			error.setError("ProjectEmployee table possibly empty or data not found");
			return utility.converJavaObjectToJsonString(error);
		} else {
			return utility.converJavaObjectToJsonString(employeeDataList.get(0));
		}
	}
	
	@RequestMapping(value = "/contracts/{enterpriseId}", method = RequestMethod.GET)
	public String getContractDataByEnterpriseId(@PathVariable String enterpriseId) throws JsonProcessingException {

		List<ContractData> contractData = new ArrayList<ContractData>();
		ErrorModel error = new ErrorModel();

		if (StringUtils.isBlank(enterpriseId)) {
			error.setError("EnterpriseId parameter cannot be null");
			return utility.converJavaObjectToJsonString(error);
		}

		List<Long> enterpriseIdList = jdbcTemplate.query(selectFromEmployeeTable + "'" + enterpriseId + "'",
				new RowMapper<Long>() {
					@Override
					public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getLong("ContractID");
					}
				});

		if (CollectionUtils.isEmpty(enterpriseIdList)) {
			error.setError("ProjectEmployee table possibly empty or data not found");
			return utility.converJavaObjectToJsonString(error);
		} else {
			for (Long contractID : enterpriseIdList) {

				contractData = jdbcTemplate.query(selectFromContractTable + "'" + contractID + "'",
						new ContractDataRowMapper());

				if (!CollectionUtils.isEmpty(contractData)) {
					System.out.println(contractData.get(0).toString());
					break;
				} else {
					error.setError("Contract table possibly empty or data not found");
					return utility.converJavaObjectToJsonString(error);
				}
			}
		}

		return utility.converJavaObjectToJsonString(contractData.get(0));
	}

	@RequestMapping(value = "/forecast/{enterpriseId}", method = RequestMethod.GET)
	public String getForecastDataByEnterpriseId(@PathVariable String enterpriseId) throws JsonProcessingException {

		ForecastAndRollOffData rollOffData = new ForecastAndRollOffData();
		ErrorModel error = new ErrorModel();

		if (StringUtils.isBlank(enterpriseId)) {
			error.setError("EnterpriseId parameter cannot be null");
			return utility.converJavaObjectToJsonString(error);
		}

		List<String> rollOffDateList = jdbcTemplate.query(getRollOffDateFromEmployeeTable + "'" + enterpriseId + "'",
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("RollOffDate");
					}
				});

		if (CollectionUtils.isEmpty(rollOffDateList)) {
			error.setError("Roll Off Date for the employee not found");
			return utility.converJavaObjectToJsonString(error);
		} else {

			if (StringUtils.isBlank(rollOffDateList.get(0))) {
				error.setError("Roll Off Date for the employee not found");
				return utility.converJavaObjectToJsonString(error);
			}

			rollOffData.setRollOffDate(rollOffDateList.get(0).substring(0, 10));
			rollOffData.setEnterpriseID(enterpriseId);
			List<ForeCastData> forecastDataList = jdbcTemplate.query(
					selectFromForecastTablewithEnterpriseID + "'" + enterpriseId + "'", new ForeCastDataRowMapper());

			if (!CollectionUtils.isEmpty(forecastDataList)) {

				List<ForeCast2Data> foreCast2DataList = new ArrayList<ForeCast2Data>();
				forecastDataList.forEach(forecastData -> {
					ForeCast2Data foreCast2Data = new ForeCast2Data();
					foreCast2Data.setDate(forecastData.getDate());
					foreCast2Data.setNumber(forecastData.getNumber());
					foreCast2DataList.add(foreCast2Data);
				});

				try {
					utility.modifyforeCast2DataList(foreCast2DataList);
				} catch (ParseException e) {

					e.printStackTrace();
				}

				rollOffData.setForecastData(foreCast2DataList);

			} else {
				error.setError("Forecast table possibly empty or data not available");
				return utility.converJavaObjectToJsonString(error);
			}
		}
		return utility.converJavaObjectToJsonString(rollOffData);
	}
}
