package com.access.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContractDataRowMapper implements RowMapper<com.access.models.ContractData> {

	@Override
	public com.access.models.ContractData mapRow(ResultSet rs, int rowNum) throws SQLException {

		com.access.models.ContractData contractData = new com.access.models.ContractData();
		
		if (rs != null) {			
			contractData.setContractId(rs.getLong(1));
			contractData.setContractName(rs.getString(2));
			contractData.setCdl(rs.getString(3));
			contractData.setOffshoreMD(rs.getString(4));
			contractData.setDul(rs.getString(5));
			contractData.setProjectLead(rs.getString(6));
			contractData.setMasterClient(rs.getString(7));
		}
		
		return contractData;
	}
}
