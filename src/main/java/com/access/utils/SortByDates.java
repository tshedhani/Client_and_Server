package com.access.utils;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.access.models.ForeCast2Data;

public class SortByDates implements Comparator<ForeCast2Data> {

	@Override
	public int compare(ForeCast2Data o1, ForeCast2Data o2) {

		if (StringUtils.isBlank(o1.getDate()) || StringUtils.isBlank(o2.getDate())) {
			return 0;
		}
		return o1.getDate().compareTo(o2.getDate());
	}
}
