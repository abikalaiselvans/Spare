package com.org.my.spare.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.my.architecture.MyDateUtil;
import com.org.my.spare.model.Demand;
import com.org.my.spare.model.DemandItem;
import com.org.my.spare.model.IssueSlip;
 
@Component()
public class IssueSlipDAO 
{
	
	private static JdbcTemplate jdbc;
	private String sql = "";
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
 		
		
	 /* To get all records from database */
	 public List<IssueSlip> getItems()
	 {
		try
		{
			sql = "";
			sql = sql+ " SELECT b.INT_ITEMROWID,b.CHR_ISSUENO, a.CHR_CUSTOMERNAME,DATE_FORMAT(DAT_ISSUEDDATE,'%d-%b-%Y'),b.CHR_RETURNSTATUS, ";//COUNT(*),
			sql = sql+ " a.CHR_CALLNUMBER,a.CHR_MACHINESERIALNUMBER  ";
		 	sql = sql+ " FROM spare_t_demand a, spare_t_demanditem b ";
		 	sql = sql+ " WHERE a.CHR_DEMANDNO =b.CHR_DEMANDNO ";
		 	sql = sql+ " AND DAY(DAT_ISSUEDDATE) = DAY(NOW()) ";
		 	sql = sql+ " AND MONTH(DAT_ISSUEDDATE) = MONTH(NOW()) ";
		 	sql = sql+ " AND YEAR(DAT_ISSUEDDATE) = YEAR(NOW()) ";
		 	sql = sql + " ORDER BY b.CHR_ISSUENO";
		 	return jdbc.query(sql,new listRowMapper());
		}
		catch (Exception e) 
		{
				System.out.println(e.getMessage());
		}
		return null;
			 
	}
	 
	 
 
	public List<IssueSlip> sortData(Map request) 
	{
		sql = "";
		sql = sql+ " SELECT b.INT_ITEMROWID,b.CHR_ISSUENO, a.CHR_CUSTOMERNAME,DATE_FORMAT(DAT_ISSUEDDATE,'%d-%b-%Y'),b.CHR_RETURNSTATUS, ";
		sql = sql+ " a.CHR_CALLNUMBER,a.CHR_MACHINESERIALNUMBER  ";
	 	sql = sql+ " FROM spare_t_demand a, spare_t_demanditem b ";
	 	sql = sql+ " WHERE a.CHR_DEMANDNO =b.CHR_DEMANDNO ";
	 	 
	 	if(!"0".equals(request.get("status")))
	 		sql = sql+ " AND b.CHR_ISSUED ='"+request.get("status")+"'  ";
	 	if(!"0".equals(request.get("day")))
	 		sql = sql+ " AND DAY(DAT_ISSUEDDATE) = "+request.get("day");
	 	if(!"0".equals(request.get("month")))
	 		sql = sql+ " AND MONTH(DAT_ISSUEDDATE) =  "+request.get("month");
	 	if(!"0".equals(request.get("year")))
	 		sql = sql+ " AND YEAR(DAT_ISSUEDDATE) =  "+request.get("year");
	 	if(!"0".equals(request.get("startLetter")))
			sql += " AND a.CHR_CUSTOMERNAME LIKE '"+request.get("startLetter")+"%'";
	 	sql = sql + " ORDER BY b.CHR_ISSUENO";
	 	System.out.println("==="+sql);
		return jdbc.query(sql,new listRowMapper());
	}

	
	
	
	/* Inner class to map all records  on database */
	private static final class listRowMapper implements RowMapper<IssueSlip> 
	{
		  public IssueSlip mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  IssueSlip d=new IssueSlip();
			  d.setRowid(rs.getInt(1));
			  d.setIsueslipno(rs.getString(2));
			  d.setCustomer(rs.getString(3));
			  d.setIssuedate(rs.getString(4));
			  d.setReturnstatus(rs.getString(5));
			  d.setCallnumber(rs.getString(6));
			  d.setMachinenumber(rs.getString(7));
			  
			  return d;
		  }
	  }
	
		
	 
}


