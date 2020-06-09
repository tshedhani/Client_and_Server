package com.access.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.access.utils.SQLCommands;

@RestController
public class GetContractData implements SQLCommands {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/contract/{name}", method = RequestMethod.GET)
	public String getDataByPathVariable(@PathVariable String name) {
		
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Name paramter cannot be null");			
		}	
		
		List <String> contractList = jdbcTemplate.query(selectFromContractTable, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("ContractName");
			}			
		});
		
		if (CollectionUtils.isEmpty(contractList)) {
			System.out.println("Nothing returned... table possible empty");
		}else {
			System.out.println(contractList.size());
			contractList.forEach(System.out::println);
		}
		
		return String.valueOf(name);
	}
}
