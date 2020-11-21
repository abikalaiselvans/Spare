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

import com.org.my.spare.model.EquipementModel;
 

/* THE FOLLOWING CLASSES ACT AS DATABASE OBJECT (DAO) FOR EQUIPMENT MODEL */
@Component("equipementModelDao")
public class EquipementModelDAO 
{
	
	private JdbcTemplate jdbc;
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	/* Inner class to map all equipment type records  on database */
	private static final class equipementModelMapper implements RowMapper<EquipementModel> 
	{
		  public EquipementModel mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  EquipementModel equ=new EquipementModel();
			  equ.setRowid(rs.getInt(1));
			  equ.setEquipementtypeid(rs.getInt(2));
			  equ.setEquipementmakeid(rs.getInt(3));
			  equ.setEquipementtypename(rs.getString(4));
			  equ.setEquipementmake(rs.getString(5) );
			  equ.setName(rs.getString(6));
			  equ.setDescription(rs.getString(7));
			  return equ;
		  }
	  }
	
		
	
	/* Inner class to map the Equipment edit records on database */
	private static final class resultsetRowMapper implements RowMapper<EquipementModel> 
	{
		  public EquipementModel mapRow(ResultSet rs,int rowNum)throws SQLException
		  {
			  EquipementModel equ=new EquipementModel();
			  equ.setRowid(rs.getInt(1));
			  equ.setEquipementtypeid(rs.getInt(2));
			  equ.setEquipementmakeid(rs.getInt(3));
			  equ.setName(rs.getString(4));
			  equ.setDescription(rs.getString(5));
			  return equ;
		  }
	}
	
	
	
	 
	 /* To insert new equipment type  records into database */
	 public int saveRecord(String typeid, String makeid,String name,String description,String userid) 
	 {
		
		 String Sql ="INSERT INTO spare_m_equipement_model(INT_EQUIPEMENTTYPEID,INT_MAKEID,CHR_MODEL,CHR_DESC,CHR_USRNAME,DT_UPDATEDATE) values(?,?,?,?,?,DATE(NOW()))";
		 Object[] params = new Object[] {typeid, makeid,name,description, userid };
		 int[] types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		 int row = jdbc.update(Sql, params,types);
		 return row;
		 
	 }
	 
    
	/* To fetch required editable equipment type record from database */ 
	public List<EquipementModel> editRecord(int editValue) 
	{
		String sql="SELECT INT_INT_EQUIPEMENTMODELID,INT_EQUIPEMENTTYPEID,INT_MAKEID, CHR_MODEL,CHR_DESC FROM spare_m_equipement_model WHERE  INT_INT_EQUIPEMENTMODELID="+editValue;
		System.out.println("DAO :"+sql);
		return jdbc.query(sql,new resultsetRowMapper());
		
		
	}

	/* To update the edited equipment type records into database */
	public int updateRecord(int rowid, String name, String description,String userid, int  typeid , int makeid) {
		return jdbc.update("UPDATE spare_m_equipement_model set INT_EQUIPEMENTTYPEID=?,INT_MAKEID=?,CHR_MODEL = ?,CHR_DESC = ?,CHR_USRNAME=? where INT_INT_EQUIPEMENTMODELID = ?",typeid,makeid,name,description,userid,rowid);
	}

	/* To delete the equipment type records from database */
	public int deleteRecord(String rowids) 
	{
		int row  = jdbc.update("DELETE FROM spare_m_equipement_model WHERE INT_INT_EQUIPEMENTMODELID in("+rowids+")");
		return row;
	}

	/* To get sorted equipment type data from database */
	public List<EquipementModel> sortData(String letter,String typeid,String makeid) 
	{
		String sql= "";
		sql = " SELECT 	c.INT_INT_EQUIPEMENTMODELID,c.INT_EQUIPEMENTTYPEID,c.INT_MAKEID, a.CHR_EQUIPEMENTTYPENAME ,b.CHR_MAKENAME, ";
		sql = sql + " c.CHR_MODEL,c.CHR_DESC ";
		sql = sql + " FROM spare_m_equipement_type a, spare_m_equipement_make b,  spare_m_equipement_model c ";
		sql = sql + " WHERE a.INT_EQUIPEMENTTYPEID= c.INT_EQUIPEMENTTYPEID  ";
		sql = sql + " AND b.INT_MAKEID = c.INT_MAKEID ";
		if(!"0".equals(typeid))
			sql += " AND c.INT_EQUIPEMENTTYPEID ="+typeid ;
		if(!"0".equals(makeid))
			sql += " AND c.INT_MAKEID ="+makeid ;
		if(!"0".equals(letter))
			sql += " AND c.CHR_MODEL LIKE '"+letter+"%'";
		System.out.println(sql);
		return jdbc.query(sql,new equipementModelMapper());
	}

	/* To get all equipment type id's,name from database */
	 public List<EquipementModel> getItems()
	 {
		 	String sql = ""; 
			sql = " SELECT 	c.INT_INT_EQUIPEMENTMODELID,c.INT_EQUIPEMENTTYPEID,c.INT_MAKEID, a.CHR_EQUIPEMENTTYPENAME ,b.CHR_MAKENAME, ";
			sql = sql + " c.CHR_MODEL,c.CHR_DESC ";
			sql = sql + " FROM spare_m_equipement_type a, spare_m_equipement_make b,  spare_m_equipement_model c ";
			sql = sql + " WHERE a.INT_EQUIPEMENTTYPEID= c.INT_EQUIPEMENTTYPEID  ";
			sql = sql + " AND b.INT_MAKEID = c.INT_MAKEID ";
			return jdbc.query(sql,new equipementModelMapper());
	}
	 	

}


