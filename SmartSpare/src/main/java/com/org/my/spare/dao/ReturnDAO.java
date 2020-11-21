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
import com.org.my.spare.model.Return;
 
@Component()
public class ReturnDAO 
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
	 public List<Return> getItems()
	 {
		try
		{
			sql = "";
			sql = sql+ " SELECT INT_RETURNID,CHR_RETURNNO,CHR_ISSUENO,CHR_ISSUESERIALNUMBER,CHR_SERIALNUMBER,";
			sql = sql+ " DATE_FORMAT(CHR_RETURNDATE,'%d-%b-%Y'),CHR_RECEIVEDSTATUS,CHR_QUALITYSTATUS  FROM spare_t_issueslipreturn ";
			sql = sql+ " WHERE INT_RETURNID >0 ";
		 	sql = sql+ " AND DAY(CHR_RETURNDATE) = DAY(NOW()) ";
		 	sql = sql+ " AND MONTH(CHR_RETURNDATE) = MONTH(NOW()) ";
		 	sql = sql+ " AND YEAR(CHR_RETURNDATE) = YEAR(NOW()) ";
		 	sql = sql + " ORDER BY INT_RETURNID";
		 	return jdbc.query(sql,new listRowMapper());
		}
		catch (Exception e) 
		{
				System.out.println(e.getMessage());
		}
		return null;
			 
	}
	 
	 
 
	public List<Return> sortData(Map request) 
	{
		sql = "";
		
		sql = sql+ " SELECT INT_RETURNID,CHR_RETURNNO,CHR_ISSUENO,CHR_ISSUESERIALNUMBER,CHR_SERIALNUMBER,";
		sql = sql+ " DATE_FORMAT(CHR_RETURNDATE,'%d-%b-%Y'),CHR_RECEIVEDSTATUS,CHR_QUALITYSTATUS  FROM spare_t_issueslipreturn ";
		sql = sql+ " WHERE INT_RETURNID >0 ";
	 	 
	 	if(!"0".equals(request.get("status")))
	 		sql = sql+ " AND CHR_RECEIVEDSTATUS  ='"+request.get("status")+"'  ";
	 	if(!"0".equals(request.get("day")))
	 		sql = sql+ " AND DAY(CHR_RETURNDATE) = "+request.get("day");
	 	if(!"0".equals(request.get("month")))
	 		sql = sql+ " AND MONTH(CHR_RETURNDATE) =  "+request.get("month");
	 	if(!"0".equals(request.get("year")))
	 		sql = sql+ " AND YEAR(CHR_RETURNDATE) =  "+request.get("year");
	 	if(!"0".equals(request.get("startLetter")))
			sql += " AND CHR_RETURNNO LIKE '"+request.get("startLetter")+"%'";
	 	sql = sql + " ORDER BY CHR_RETURNNO";
	 	System.out.println("==="+sql);
		return jdbc.query(sql,new listRowMapper());
	 	 
	}

	
	
	
	/* Inner class to map all records  on database */
	private static final class listRowMapper implements RowMapper<Return> 
	{
		  public Return mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  Return d=new Return();
			  d.setRowid(rs.getInt(1));
			  d.setReturnno(rs.getString(2));
			  d.setIssuenumber(rs.getString(3));
			  d.setIssueserialnumber(rs.getString(4));
			  d.setSerialnumber(rs.getString(5));
			  d.setReturndate(rs.getString(6));
			  d.setReceivedstatus(rs.getString(7));
			  d.setQualitystatus(rs.getString(8));
			  return d;
		  }
	  }




	public void ReturnSerialNumberReceived(String row,String receivedid) 
	{
		// TODO Auto-generated method stub
		sql = "UPDATE spare_t_issueslipreturn SET CHR_RECEIVEDSTATUS=?,CHR_RECEIVEDBY=?,DT_RECEIVEDDATE=NOW()  WHERE INT_RETURNID=? ";
		jdbc.update(sql, "Y",receivedid,row);
		System.out.println("UPDATE RETURN SERIALNUMBER RECEIVED STATUS");
	}
	
	public void BulkReceived(String serialnumbers,String receivedid) 
	{
		String serialnumber[] = serialnumbers.split(",");
		for(int x=0; x< serialnumber.length;x++)
		{
			sql = "UPDATE spare_t_issueslipreturn SET CHR_RECEIVEDSTATUS=?,CHR_RECEIVEDBY=?,DT_RECEIVEDDATE=NOW()  WHERE CHR_SERIALNUMBER=?  AND CHR_RECEIVEDSTATUS = 'N' ";
			jdbc.update(sql, "Y",receivedid,serialnumber[x]);
				
		}
		System.out.println("Bulk RETURN SERIALNUMBER RECEIVED STATUS");
	}
	
	
	public void QualityStatus(String row,String empid,String status,String desc) 
	{
		// TODO Auto-generated method stub
		sql = "UPDATE spare_t_issueslipreturn SET CHR_QUALITYSTATUS=?,CHR_QC_DESC=?,CHR_QUALITY_TESTEDBY=?,DT_QUALITY_TESTDATE=NOW()  WHERE INT_RETURNID=? ";
		jdbc.update(sql, status.toUpperCase(),desc,empid,row);
		System.out.println("UPDATE QUALITY STATUS");
	}
	 
}


