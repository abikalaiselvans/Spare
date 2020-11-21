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

import com.org.my.spare.model.EquipementMake;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR EQUIPMENT MAKE */
@Component("equipementMakelDao")
public class EquipementMakeDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	/* To get all equipment type id's,name from database */
	 public List<EquipementMake> getItems()
	 {
		 	String sql = " SELECT INT_MAKEID,CHR_MAKENAME,CHR_DESC FROM spare_m_equipement_make ORDER BY CHR_MAKENAME";
			return jdbc.query(sql,new equipementMakeMapper());
	}
	 
	 
	 /* To insert new equipment make  records into database */
	 public int saveRecord(String name,String description,String userid) 
	 {
		
		 String Sql ="INSERT INTO spare_m_equipement_make(CHR_MAKENAME,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { name,description, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 int row = jdbc.update(Sql, params,types);
		 return row;
		 
	 }
	 
    
	/* To fetch required editable equipment make record from database */ 
	public List<EquipementMake> editRecord(int editValue) 
	{
		String sql="SELECT INT_MAKEID,CHR_MAKENAME,CHR_DESC FROM spare_m_equipement_make WHERE  INT_MAKEID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new resultsetRowMapper());
		
		
	}

	/* To update the edited equipment type records into database */
	public int updateRecord(int rowid, String name, String description,String userid ) 
	{
		System.out.println("-");
		return jdbc.update("UPDATE spare_m_equipement_make set  CHR_MAKENAME = ?,CHR_DESC = ?,CHR_USRNAME=? where INT_MAKEID = ?",name,description,userid,rowid);
	}

	
	/* To delete the equipment type records from database */
	public int deleteRecord(String rowids) 
	{
		int row  = jdbc.update("DELETE FROM spare_m_equipement_make WHERE INT_MAKEID in("+rowids+")");
		return row;
	}

	/* To get sorted equipment make data from database */
	public List<EquipementMake> sortData(String letter) 
	{
		String sql= "";
		sql = " SELECT INT_MAKEID,CHR_MAKENAME,CHR_DESC FROM spare_m_equipement_make WHERE  INT_MAKEID >0 ";
		if(!"0".equals(letter))
			sql += " AND CHR_MAKENAME LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new equipementMakeMapper());
	}

	
	/* Inner class to map all equipment make records  on database */
	private static final class equipementMakeMapper implements RowMapper<EquipementMake> 
	{
		  public EquipementMake mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  EquipementMake equ=new EquipementMake();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the Equipment edit records on database */
	private static final class resultsetRowMapper implements RowMapper<EquipementMake> 
	{
		  public EquipementMake mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  EquipementMake equ=new EquipementMake();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	}
	
	
	 	

}


