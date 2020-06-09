package com.access.utils;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.access.models.ForeCast2Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Utilities {

	public String converJavaObjectToJsonString(Object javaObjectString) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(javaObjectString);
	}

	public String incrementDate(String date) {

		String month = date.substring(0, 2);
		String year = date.substring(2, date.length());

		Integer monthInt = Integer.valueOf(month);
		Integer yearInt = Integer.valueOf(year);

		if (monthInt == 12) {
			yearInt++;
			year = yearInt.toString();
			month = "01";
		} else {
			monthInt++;
			month = monthInt.toString();

			if (monthInt < 10) {
				month = "0".concat(month);
			}
		}

		return month.concat(year);
	}

	public void modifyforeCast2DataList(List<ForeCast2Data> foreCast2DataList) throws ParseException {

		Integer listSize = foreCast2DataList.size();

		if (listSize > 11) {
			return;
		}

		while (listSize < 12) {

			String date = foreCast2DataList.get(listSize - 1).getDate();

			ForeCast2Data foreCastData = new ForeCast2Data();
			foreCastData.setDate(incrementDate(date));
			foreCastData.setNumber(0L);
			foreCast2DataList.add(foreCastData);

			listSize++;
		}

		Collections.sort(foreCast2DataList, new SortByDates());
	}
}
