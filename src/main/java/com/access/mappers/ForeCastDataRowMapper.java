package com.access.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.access.models.ForeCastData;

public class ForeCastDataRowMapper implements RowMapper<ForeCastData> {

	@Override
	public ForeCastData mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ForeCastData foreCastData = new ForeCastData();
		foreCastData.setEnterpriseId(rs.getString(1));
		foreCastData.setDate(rs.getString(2));
		foreCastData.setNumber(rs.getLong(3));
		return foreCastData;
	}
}
