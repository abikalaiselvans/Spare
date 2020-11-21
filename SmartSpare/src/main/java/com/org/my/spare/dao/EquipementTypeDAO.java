package com.org.my.spare.dao ;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.my.spare.model.EquipementType;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR EQUIPMENT TYPE */
@Component("equTypeDao")
public class EquipementTypeDAO {
	
	private JdbcTemplate jdbc;
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) {
		  this.jdbc=new JdbcTemplate(datasource); 
	  }
	
	
	/* Inner class to map all equipment type records  on database */
	private static final class EquTypeMapper implements RowMapper<EquipementType> 
	{
		  public EquipementType mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  EquipementType equ=new EquipementType();
			  equ.setName(rs.getString(2));
			  equ.setRowid(rs.getInt(1));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the Equipment edit records on database */
	private static final class resultsetRowMapper implements RowMapper<EquipementType> 
	{
		  public EquipementType mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  EquipementType equ=new EquipementType();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  return equ;
		  }
	  }
	
	/* To get all equipment type id's,name from database */
	 public List<EquipementType> getItems()
	 {
		  String sql= "SELECT INT_EQUIPEMENTTYPEID,CHR_EQUIPEMENTTYPENAME FROM spare_m_equipement_type";
		  return jdbc.query(sql,new EquTypeMapper());
	}
	 
	 
	 /* To insert new equipment type  records into database */
	 public int saveRecord(String equTypeName,String equTypeDesc,String sessionName) 
	 {
		
		 String insertSql ="INSERT INTO spare_m_equipement_type(CHR_EQUIPEMENTTYPENAME,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { equTypeName,equTypeDesc, sessionName };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 int row = jdbc.update(insertSql, params,types);
		 return row;
		 
	 }
    
	/* To fetch required editable equipment type record from database */ 
	public List<EquipementType> editRecord(int editValue) 
	{
		String sql="SELECT INT_EQUIPEMENTTYPEID, CHR_EQUIPEMENTTYPENAME,CHR_DESC FROM spare_m_equipement_type WHERE  INT_EQUIPEMENTTYPEID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new resultsetRowMapper());
		
		
	}

	/* To update the edited equipment type records into database */
	public int updateRecord(String equName, String equDesc,int equId) {
		return jdbc.update("UPDATE spare_m_equipement_type set CHR_EQUIPEMENTTYPENAME = ?,CHR_DESC = ? where INT_EQUIPEMENTTYPEID = ?",equName,equDesc,equId);
	}

	/* To delete the equipment type records from database */
	public int deleteRecord(String equTypeIds) 
	{
		int row  = jdbc.update("DELETE FROM spare_m_equipement_type WHERE INT_EQUIPEMENTTYPEID in("+equTypeIds+")");
		return row;
	}

	/* To get sorted equipment type data from database */
	public List<EquipementType> sortData(String letter) 
	{
		String sql= "SELECT INT_EQUIPEMENTTYPEID,CHR_EQUIPEMENTTYPENAME FROM spare_m_equipement_type";
		if(!"0".equals(letter))
			sql += " WHERE CHR_EQUIPEMENTTYPENAME LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new EquTypeMapper());
	}

	 	

}


