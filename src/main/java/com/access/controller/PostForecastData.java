package com.access.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.access.mappers.ForeCastDataRowMapper;
import com.access.models.ErrorModel;
import com.access.models.ForeCast2Data;
import com.access.models.ForeCastData;
import com.access.models.ForecastAndRollOffData;
import com.access.models.Information;
import com.access.utils.SQLCommands;
import com.access.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class PostForecastData implements SQLCommands {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Utilities utility;

	@RequestMapping(value = "/updateForecast", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateForecastTable(@RequestBody ForeCastData foreCastData) throws JsonProcessingException {

		ErrorModel error = new ErrorModel();
		Information info = new Information();

		if (foreCastData != null) {

			if (StringUtils.isBlank(foreCastData.getEnterpriseId())) {
				error.setError("Enterprise ID cannot be null");
				return utility.converJavaObjectToJsonString(error);
			}

			if (foreCastData.getNumber() != null && foreCastData.getNumber() > 0) {
				error.setError("Hours should not be null and greater than zero");
				return utility.converJavaObjectToJsonString(error);
			}

			if (StringUtils.isBlank(foreCastData.getDate())) {
				error.setError("Date cannot be null");
				return utility.converJavaObjectToJsonString(error);
			}

			try {
				jdbcTemplate.execute(insertIntoForecastTable + "('" + foreCastData.getEnterpriseId() + "', '"
						+ foreCastData.getDate() + "'," + foreCastData.getNumber() + " )");
			} catch (Exception e) {
				error.setError(e.getMessage());
				return utility.converJavaObjectToJsonString(error);
			}
		} else {
			error.setError("Request body cannot be null");
			return utility.converJavaObjectToJsonString(error);
		}

		info.setInformation("Insert Success");
		return utility.converJavaObjectToJsonString(info);
	}

	@RequestMapping(value = "/postForecast", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String postForecastDataUsingEnterpriseID(@RequestBody ForecastAndRollOffData foreCastAndRollOffData)
			throws JsonProcessingException {

		ErrorModel error = new ErrorModel();
		Information info = new Information();

		if (foreCastAndRollOffData != null) {

			if (StringUtils.isBlank(foreCastAndRollOffData.getEnterpriseID())) {
				error.setError("Enterprise ID cannot be null");
				return utility.converJavaObjectToJsonString(error);
			}

			if (StringUtils.isBlank(foreCastAndRollOffData.getRollOffDate())) {
				error.setError("Roll Off Date cannot be null");
				return utility.converJavaObjectToJsonString(error);
			}

			String enterpriseId = foreCastAndRollOffData.getEnterpriseID();

			for (ForeCast2Data foreCast2Data : foreCastAndRollOffData.getForecastData()) {

				try {
					List<ForeCastData> forecastDataList = jdbcTemplate.query(selectFromForecastTablewithEnterpriseID
							+ "'" + enterpriseId + "' and MMYYYY = " + "'" + foreCast2Data.getDate() + "'",
							new ForeCastDataRowMapper());

					if (!CollectionUtils.isEmpty(forecastDataList)) {

						jdbcTemplate.execute(
								updateForecastTable + "'" + foreCast2Data.getNumber() + "' Where" + " EnterpriseID="
										+ "'" + enterpriseId + "' and MMYYYY = '" + foreCast2Data.getDate() + "'");

					} else {

						jdbcTemplate.execute(insertIntoForecastTable + "('" + foreCastAndRollOffData.getEnterpriseID()
								+ "', '" + foreCast2Data.getDate() + "'," + foreCast2Data.getNumber() + " )");
					}
				} catch (Exception e) {
					error.setError(e.getMessage());
					return utility.converJavaObjectToJsonString(error);
				}
			}

		} else {
			error.setError("Request body cannot be null");
			return utility.converJavaObjectToJsonString(error);
		}

		info.setInformation("Insert Success");
		return utility.converJavaObjectToJsonString(info);
	}
}
