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
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR PROBLEM GROUP */
@Component("problemsDao")
public class ProblemsDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	/* To get all problem group id's,name from database */
	 public List<Problems> getItems()
	 {
		 	String sql = " ";
		 	sql = "  SELECT b.INT_PROBLEMID, b.INT_PROBLEMGROUPID,a.CHR_PROBLEMGROUP,b.CHR_PROBLEM,b.CHR_DESC ";
		 	sql = sql + " FROM spare_m_problemgroup a , spare_m_problems b ";
		 	sql = sql + " WHERE a.INT_PROBLEMGROUPID = b.INT_PROBLEMGROUPID ";
		 	return jdbc.query(sql,new listRowMapper());
	}
	 
	 
	 /* To insert new equipment make  records into database */
	 public int saveRecord(String groupid,String name,String description,String userid) 
	 {
		
		 String Sql ="INSERT INTO spare_m_problems(INT_PROBLEMGROUPID,CHR_PROBLEM,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { groupid,name,description, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 System.out.println("Insert a problem group");
		 int row = jdbc.update(Sql, params,types);
		 return row;
		 
	 }
	 
    
	/* To fetch required editable problem group record from database */ 
	public List<Problems> editRecord(int editValue) 
	{
		String sql="SELECT INT_PROBLEMID,INT_PROBLEMGROUPID,CHR_PROBLEM,CHR_DESC FROM spare_m_problems WHERE  INT_PROBLEMID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new editRowMapper());
		
		
	}

	/* To update the edited problem group records into database */
	public int updateRecord(int rowid, String groupid,String name, String description,String userid ) 
	{
		System.out.println("update the record");
		return jdbc.update("UPDATE spare_m_problems set INT_PROBLEMGROUPID=?, CHR_PROBLEM = ?,CHR_DESC = ?,CHR_USRNAME=? where INT_PROBLEMID = ?",groupid,name,description,userid,rowid);
	}

	
	/* To delete the problem group  records from database */
	public int deleteRecord(String rowids) 
	{
		System.out.println("delete the record");
		int row  = jdbc.update("DELETE FROM spare_m_problems WHERE INT_PROBLEMID in("+rowids+")");
		return row;
	}

	/* To get sorted problem group data from database */
	public List<Problems> sortData(String letter,String groupid) 
	{
		String sql= "";
		sql = "  SELECT b.INT_PROBLEMID, b.INT_PROBLEMGROUPID,a.CHR_PROBLEMGROUP,b.CHR_PROBLEM,b.CHR_DESC ";
	 	sql = sql + " FROM spare_m_problemgroup a , spare_m_problems b ";
	 	sql = sql + " WHERE a.INT_PROBLEMGROUPID = b.INT_PROBLEMGROUPID ";
	 	if(!"0".equals(groupid))
	 		sql += " AND b.INT_PROBLEMGROUPID= "+groupid;
	 	if(!"0".equals(letter))
			sql += " AND b.CHR_PROBLEM LIKE '"+letter+"%'";
		System.out.println("Query : "+sql);
		return jdbc.query(sql,new listRowMapper());
	}

	
	/* Inner class to map all problem group records  on database */
	private static final class listRowMapper implements RowMapper<Problems> 
	{
		  public Problems mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  Problems equ=new Problems();
			  equ.setRowid(rs.getInt(1));
			  equ.setGroupid(rs.getInt(2));
			  equ.setGroupname(rs.getString(3));
			  equ.setName(rs.getString(4));
			  equ.setDescription(rs.getString(5));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the problem group records on database */
	private static final class editRowMapper implements RowMapper<Problems> 
	{
		  public Problems mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  Problems equ=new Problems();
			  equ.setRowid(rs.getInt(1));
			  equ.setGroupid(rs.getInt(2));
			  equ.setName(rs.getString(3));
			  equ.setDescription(rs.getString(4));
			  return equ;
		  }
	}
	
	
	 	

}


