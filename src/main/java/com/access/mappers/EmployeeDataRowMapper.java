package com.access.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.access.models.EmployeeData;

public class EmployeeDataRowMapper implements RowMapper<com.access.models.EmployeeData> {

	@Override
	public EmployeeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		com.access.models.EmployeeData employeeData = new com.access.models.EmployeeData();
		
		if (rs != null) {
			employeeData.setSapID(rs.getString(1));
			employeeData.setContractID(rs.getString(2));
			employeeData.setEnterpriseID(rs.getString(3));
			employeeData.setEmployeeName(rs.getString(4));
			employeeData.setLevel(rs.getString(5));
			employeeData.setAssignedCapacity(rs.getString(6));
			employeeData.setRollOnDate(StringUtils.isNotBlank(rs.getString(7)) ? rs.getString(7).substring(0, 10) : null);
			employeeData.setRollOffDate(StringUtils.isNotBlank(rs.getString(8)) ? rs.getString(8).substring(0, 10) : null);
			employeeData.setDeskAssigned(rs.getString(9));			
			employeeData.setLaptopID(rs.getString(10));
			employeeData.setLaptopSN(rs.getString(11));
			employeeData.setPrimarySkill(rs.getString(12));
			employeeData.setSecondarySkill(rs.getString(13));
			employeeData.setProficiencyLevelPrimary(rs.getString(14));
			employeeData.setProficiencyLevelSecondary(rs.getString(15));
		}

		return employeeData;
	}

}
