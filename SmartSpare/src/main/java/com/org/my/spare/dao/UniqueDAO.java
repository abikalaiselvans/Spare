package com.org.my.spare.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.my.spare.model.Problems;
import com.org.my.spare.model.Unique;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR PROBLEM GROUP */
@Component("uniqueDao")
public class UniqueDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	 

	/* To get sorted problem group data from database */
	public List<Unique> sortData(String startLetter,String table,String field) 
	{
		String sql= "";
		sql = "  SELECT "+field + " FROM "+ table + " WHERE "+field + " IS NOT NULL ";
	 	sql += " AND "+field + " LIKE '"+startLetter+"%'";
	 	sql += " ORDER BY "+field;
		System.out.println(sql);
		return jdbc.query(sql,new listRowMapper());
	}

	
	/* Inner class to map all problem group records  on database */
	private static final class listRowMapper implements RowMapper<Unique> 
	{
		  public Unique mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  Unique equ=new Unique();
			  equ.setName(rs.getString(1));
			  return equ;
		  }
	  }
	
	 
	 	

}


