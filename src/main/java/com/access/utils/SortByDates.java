package com.access.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.access.models.ForeCast2Data;

public class SortByDates implements Comparator<ForeCast2Data> {

	@Override
	public int compare(ForeCast2Data o1, ForeCast2Data o2) {

		DateFormat f = new SimpleDateFormat("MMyyyy");

		if (StringUtils.isBlank(o1.getDate()) || StringUtils.isBlank(o2.getDate())) {
			return 0;
		}
		
		try {
			return f.parse(o1.getDate()).compareTo(f.parse(o2.getDate()));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
