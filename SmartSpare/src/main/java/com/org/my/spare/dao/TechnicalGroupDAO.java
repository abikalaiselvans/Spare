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

import com.org.my.spare.model.TechnicalGroup;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR PROBLEM GROUP */
@Component("technicalGroupDao")
public class TechnicalGroupDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	/* To get all records id's,name from database */
	 public List<TechnicalGroup> getItems()
	 {
		 	String sql = " SELECT INT_TECHNICALROUPID,CHR_TECHNICALGROUP,CHR_DESC FROM v_spare_m_technicalgroup ORDER BY CHR_TECHNICALGROUP";
			return jdbc.query(sql,new listRowMapper());
	}
	 
	 
	 /* To insert new  records into database */
	 public int saveRecord(String name,String description,String userid) 
	 {
		
		 String Sql ="INSERT INTO spare_m_technicalgroup(CHR_TECHNICALGROUP,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { name,description, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 System.out.println("Insert a problem group");
		 int row = jdbc.update(Sql, params,types);
		 return row;
		 
	 }
	 
    
	/* To fetch required editable   record from database */ 
	public List<TechnicalGroup> editRecord(int editValue) 
	{
		String sql="SELECT INT_TECHNICALROUPID,CHR_TECHNICALGROUP,CHR_DESC FROM spare_m_technicalgroup WHERE  INT_TECHNICALROUPID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new editRowMapper());
		
		
	}

	/* To update the edited problem group records into database */
	public int updateRecord(int rowid, String name, String description,String userid ) 
	{
		System.out.println("update the record");
		return jdbc.update("UPDATE spare_m_technicalgroup set  CHR_TECHNICALGROUP = ?,CHR_DESC = ?,CHR_USRNAME=? where INT_TECHNICALROUPID = ?",name,description,userid,rowid);
	}

	
	/* To delete the problem group  records from database */
	public int deleteRecord(String rowids) 
	{
		System.out.println("delete the record");
		int row  = jdbc.update("DELETE FROM spare_m_technicalgroup WHERE INT_TECHNICALROUPID in("+rowids+")");
		return row;
	}

	/* To get sorted problem group data from database */
	public List<TechnicalGroup> sortData(String letter) 
	{
		String sql= "";
		sql = " SELECT INT_TECHNICALROUPID,CHR_TECHNICALGROUP,CHR_DESC FROM spare_m_technicalgroup WHERE  INT_TECHNICALROUPID >0 ";
		if(!"0".equals(letter))
			sql += " AND CHR_TECHNICALGROUP LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new listRowMapper());
	}

	
	/* Inner class to map all problem group records  on database */
	private static final class listRowMapper implements RowMapper<TechnicalGroup> 
	{
		  public TechnicalGroup mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  TechnicalGroup equ=new TechnicalGroup();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the problem group records on database */
	private static final class editRowMapper implements RowMapper<TechnicalGroup> 
	{
		  public TechnicalGroup mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  TechnicalGroup equ=new TechnicalGroup();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	}
	
	
	 	

}


