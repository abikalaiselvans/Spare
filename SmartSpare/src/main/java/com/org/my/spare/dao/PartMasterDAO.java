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

import com.org.my.spare.model.PartMaster;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR PartMaster */
@Component("partmasterDao")
public class PartMasterDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	/* To get all  from database */
	 public List<PartMaster> getItems()
	 {
		 	String sql = "";
			sql = sql +" SELECT a.INT_PARTMASTERID,a.CHR_PARTNO,a.CHR_DESC,d.CHR_TECHNICALGROUP,a.INT_INT_EQUIPEMENTMODELID,a.INT_GENERALGROUPID, ";
			sql = sql +" b.CHR_GENERALGROUP,c.CHR_MODEL,d.INT_TECHNICALROUPID ,a.CHR_OEM_PARTNO ";
			sql = sql +" FROM spare_m_partmaster a, spare_m_generalgroup b, spare_m_equipement_model c , spare_m_technicalgroup d ";
			sql = sql +" WHERE a.INT_GENERALGROUPID = b.INT_GENERALGROUPID "; 
			sql = sql +" AND a.INT_INT_EQUIPEMENTMODELID =c.INT_INT_EQUIPEMENTMODELID";
			sql = sql +" AND a.INT_TECHNICALROUPID =  d.INT_TECHNICALROUPID ";
			
			sql = " SELECT * FROM partcode ";
			return jdbc.query(sql,new listRowMapper());
	}
	 
	 
	 /* To insert records into database */
	 public int saveRecord(String name, String oemname, String description,String technicalgroupid, String modelid, String generalgroupid, String userid ) 
	 {
		
		 String Sql ="INSERT INTO spare_m_partmaster(CHR_PARTNO,CHR_OEM_PARTNO,CHR_DESC,INT_TECHNICALROUPID,INT_INT_EQUIPEMENTMODELID,INT_GENERALGROUPID,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,?,?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] { name,oemname,description,technicalgroupid,modelid,generalgroupid, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 int row = jdbc.update(Sql, params,types);
		 return row;
	 }
	 
    
	/* To fetch required editable record from database */ 
	public List<PartMaster> editRecord(int rowid) 
	{
		String sql="";
		sql = sql +" SELECT a.INT_PARTMASTERID,a.CHR_PARTNO,a.CHR_DESC,a.INT_TECHNICALROUPID,a.INT_INT_EQUIPEMENTMODELID,a.INT_GENERALGROUPID,a.CHR_OEM_PARTNO ";
		sql = sql +" FROM spare_m_partmaster a  WHERE  a.INT_PARTMASTERID="+rowid;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new resultsetRowMapper());
		
		
	}

	/* To update the edited records into database */
	public int updateRecord(int rowid, String name, String  oemname, String description,String technicalgroupid,String modelid, String generalgroupid,String userid ) 
	{
		System.out.println("Update the record");
		return jdbc.update("UPDATE spare_m_partmaster set  CHR_PARTNO = ?,CHR_OEM_PARTNO=?,CHR_DESC = ?,INT_TECHNICALROUPID=?,INT_INT_EQUIPEMENTMODELID=?,INT_GENERALGROUPID=?,CHR_USRNAME=? where INT_PARTMASTERID = ?",name,oemname,description,technicalgroupid,modelid,generalgroupid,userid,rowid);
	}

	
	/* To delete the records from database */
	public int deleteRecord(String rowids) 
	{
		int row  = jdbc.update("DELETE FROM spare_m_partmaster WHERE INT_PARTMASTERID in("+rowids+")");
		return row;
	}

	/* To get sorted PartMaster data from database */
	public List<PartMaster> sortData(String letter, String modelid, String generalgroupid,String technicalgroupid) 
	{
		String sql= "";
		sql = sql +" SELECT a.INT_PARTMASTERID,a.CHR_PARTNO,a.CHR_DESC,d.CHR_TECHNICALGROUP,a.INT_INT_EQUIPEMENTMODELID,a.INT_GENERALGROUPID, ";
		sql = sql +" b.CHR_GENERALGROUP,c.CHR_MODEL,d.INT_TECHNICALROUPID ,a.CHR_OEM_PARTNO ";
		sql = sql +" FROM spare_m_partmaster a, spare_m_generalgroup b, spare_m_equipement_model c , spare_m_technicalgroup d";
		sql = sql +" WHERE a.INT_GENERALGROUPID = b.INT_GENERALGROUPID "; 
		sql = sql +" AND a.INT_INT_EQUIPEMENTMODELID =c.INT_INT_EQUIPEMENTMODELID"; 
		sql = sql +" AND a.INT_TECHNICALROUPID =  d.INT_TECHNICALROUPID ";
		if(!"0".equals(modelid))
			sql += " AND a.INT_INT_EQUIPEMENTMODELID =  "+modelid;
		if(!"0".equals(generalgroupid))
			sql += " AND a.INT_GENERALGROUPID = "+generalgroupid;
		if(!"0".equals(technicalgroupid))
			sql += " AND a.INT_TECHNICALROUPID = "+technicalgroupid;
		if(!"0".equals(letter))
			sql += " AND a.CHR_PARTNO LIKE '"+letter+"%'";
		System.out.println(sql);
		
		sql = " SELECT * FROM partcode WHERE INT_PARTMASTERID >0 ";
		if(!"0".equals(modelid))
			sql += " AND INT_INT_EQUIPEMENTMODELID =  "+modelid;
		if(!"0".equals(generalgroupid))
			sql += " AND INT_GENERALGROUPID = "+generalgroupid;
		if(!"0".equals(technicalgroupid))
			sql += " AND INT_TECHNICALROUPID = "+technicalgroupid;
		if(!"0".equals(letter))
			sql += " AND CHR_PARTNO LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new listRowMapper());
	}


	
	/* To get sorted PartMaster description from database */
	public List<PartMaster> partCodeDescription(String partcode ) 
	{
		String sql= "";
		sql = sql +" SELECT  CHR_DESC FROM spare_m_partmaster WHERE INT_PARTMASTERID ="+partcode;
		System.out.println(sql);
		return jdbc.query(sql,new descriptionRowMapper());
	}

	
	/* Inner class to map all equipment make records  on database */
	private static final class listRowMapper implements RowMapper<PartMaster> 
	{
		  public PartMaster mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  PartMaster equ=new PartMaster();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  equ.setTechnicaldescription(rs.getString(4));
			  equ.setModelid(rs.getInt(5));
			  equ.setGeneralgroupid(rs.getInt(6));
			  equ.setGeneralgroupname(rs.getString(7));
			  equ.setModelname(rs.getString(8));
			  equ.setTechnicalgroupid(rs.getInt(9));
			  equ.setOemname(rs.getString(10));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the Equipment edit records on database */
	private static final class resultsetRowMapper implements RowMapper<PartMaster> 
	{
		  public PartMaster mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  PartMaster equ=new PartMaster();
			  equ.setRowid(rs.getInt(1));
			  equ.setName(rs.getString(2));
			  equ.setDescription(rs.getString(3));
			  equ.setTechnicalgroupid(rs.getInt(4));
			  equ.setModelid(rs.getInt(5));
			  equ.setGeneralgroupid(rs.getInt(6));
			  equ.setOemname(rs.getString(7));
			  return equ;
		  }
	}
	
	
	 	
	private static final class descriptionRowMapper implements RowMapper<PartMaster> 
	{
		  public PartMaster mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  PartMaster p=new PartMaster();
			  p.setDescription(rs.getString(1));
			  return p;
		  }
	  }
	

}


