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

import com.org.my.spare.model.GeneralGroup;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR GeneralGroup */
@Component("generalgroupDao")
public class GeneralGroupDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	/* To get all General Group id's,name from database */
	 public List<GeneralGroup> getItems()
	 {
		 	String sql = " SELECT INT_GENERALGROUPID,CHR_GENERALGROUP,CHR_DESC FROM spare_m_generalgroup ORDER BY CHR_GENERALGROUP";
			return jdbc.query(sql,new listRowMapper());
	}
	 
	 
	 /* To insert new GeneralGroup  records into database */
	 public int saveRecord(String name,String description,String userid) 
	 {
		
		 String Sql ="INSERT INTO spare_m_generalgroup(CHR_GENERALGROUP,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { name,description, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 int row = jdbc.update(Sql, params,types);
		 return row;
		 
	 }
	 
    
	/* To fetch required editable GeneralGroupe record from database */ 
	public List<GeneralGroup> editRecord(int editValue) 
	{
		String sql="SELECT INT_GENERALGROUPID,CHR_GENERALGROUP,CHR_DESC FROM spare_m_generalgroup WHERE  INT_GENERALGROUPID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new resultsetRowMapper());
		
		
	}

	/* To update the edited GeneralGroup records into database */
	public int updateRecord(int rowid, String name, String description,String userid ) 
	{
		System.out.println("-");
		return jdbc.update("UPDATE spare_m_generalgroup set  CHR_GENERALGROUP = ?,CHR_DESC = ?,CHR_USRNAME=? where INT_GENERALGROUPID = ?",name,description,userid,rowid);
	}

	
	/* To delete the GeneralGroup records from database */
	public int deleteRecord(String rowids) 
	{
		int row  = jdbc.update("DELETE FROM spare_m_generalgroup WHERE INT_GENERALGROUPID in("+rowids+")");
		return row;
	}

	/* To get sorted GeneralGroup data from database */
	public List<GeneralGroup> sortData(String letter) 
	{
		String sql= "";
		sql = " SELECT INT_GENERALGROUPID,CHR_GENERALGROUP,CHR_DESC FROM spare_m_generalgroup WHERE  INT_GENERALGROUPID >0 ";
		if(!"0".equals(letter))
			sql += " AND CHR_GENERALGROUP LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new listRowMapper());
	}

	
	/* Inner class to map all equipment make records  on database */
	private static final class listRowMapper implements RowMapper<GeneralGroup> 
	{
		  public GeneralGroup mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  GeneralGroup equ=new GeneralGroup();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the Equipment edit records on database */
	private static final class resultsetRowMapper implements RowMapper<GeneralGroup> 
	{
		  public GeneralGroup mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  GeneralGroup equ=new GeneralGroup();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	}
	
	
	 	

}


