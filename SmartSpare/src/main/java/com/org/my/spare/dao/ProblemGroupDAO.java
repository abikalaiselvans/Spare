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

import com.org.my.spare.model.ProblemGroup;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR PROBLEM GROUP */
@Component("problemGroupDao")
public class ProblemGroupDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	/* To get all problem group id's,name from database */
	 public List<ProblemGroup> getItems()
	 {
		 	String sql = " SELECT INT_PROBLEMGROUPID,CHR_PROBLEMGROUP,CHR_DESC FROM spare_m_problemgroup ORDER BY CHR_PROBLEMGROUP";
			return jdbc.query(sql,new listRowMapper());
	}
	 
	 
	 /* To insert new equipment make  records into database */
	 public int saveRecord(String name,String description,String userid) 
	 {
		
		 String Sql ="INSERT INTO spare_m_problemgroup(CHR_PROBLEMGROUP,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { name,description, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 System.out.println("Insert a problem group");
		 int row = jdbc.update(Sql, params,types);
		 return row;
		 
	 }
	 
    
	/* To fetch required editable problem group record from database */ 
	public List<ProblemGroup> editRecord(int editValue) 
	{
		String sql="SELECT INT_PROBLEMGROUPID,CHR_PROBLEMGROUP,CHR_DESC FROM spare_m_problemgroup WHERE  INT_PROBLEMGROUPID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new editRowMapper());
		
		
	}

	/* To update the edited problem group records into database */
	public int updateRecord(int rowid, String name, String description,String userid ) 
	{
		System.out.println("update the record");
		return jdbc.update("UPDATE spare_m_problemgroup set  CHR_PROBLEMGROUP = ?,CHR_DESC = ?,CHR_USRNAME=? where INT_PROBLEMGROUPID = ?",name,description,userid,rowid);
	}

	
	/* To delete the problem group  records from database */
	public int deleteRecord(String rowids) 
	{
		System.out.println("delete the record");
		int row  = jdbc.update("DELETE FROM spare_m_problemgroup WHERE INT_PROBLEMGROUPID in("+rowids+")");
		return row;
	}

	/* To get sorted problem group data from database */
	public List<ProblemGroup> sortData(String letter) 
	{
		String sql= "";
		sql = " SELECT INT_PROBLEMGROUPID,CHR_PROBLEMGROUP,CHR_DESC FROM spare_m_problemgroup WHERE  INT_PROBLEMGROUPID >0 ";
		if(!"0".equals(letter))
			sql += " AND CHR_PROBLEMGROUP LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new listRowMapper());
	}

	
	/* Inner class to map all problem group records  on database */
	private static final class listRowMapper implements RowMapper<ProblemGroup> 
	{
		  public ProblemGroup mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  ProblemGroup equ=new ProblemGroup();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the problem group records on database */
	private static final class editRowMapper implements RowMapper<ProblemGroup> 
	{
		  public ProblemGroup mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  ProblemGroup equ=new ProblemGroup();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	}
	
	
	 	

}


