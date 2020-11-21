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
 
@Component()
public class DemandDAO 
{
	
	private static JdbcTemplate jdbc;
	private String sql = "";
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		if ( datasource != null )
			this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	
	 
	 /* To insert  records into database */
	 public int saveRecord( HttpServletRequest r,Demand d, String userid) 
	 {
	 	
		try 
		{
			String Refnumber =jdbc.queryForObject("SELECT FUN_SPR_DEMAND_NUMBER("+d.getLocation()+")", String.class);
			
			sql = " INSERT INTO spare_t_demand (";
			sql = sql + " CHR_DEMANDNO,CHR_STORAGELOCATION,INT_LOCATIONID,CHR_CUSTOMERNAME,CHR_ADDRESS,";
			sql = sql + " CHR_CONTACTPERSON,CHR_CONTACTNO,CHR_CALLNUMBER,CHR_MACHINESERIALNUMBER, ";
			sql = sql + " CHR_DOCUMENTTYPE,DT_CREATIONTIME,CHR_CREATEDBY, ";
			sql = sql + " CHR_USRNAME,DT_UPDATEDATE,CHR_UPDATESTATUS )";
			sql = sql + " VALUES (?,?,?,?,?,?,?,?,?,?,NOW(),?,?,DATE(NOW()),'Y' )";
			Object[] params = new Object[] { Refnumber,d.getStoragelocation(),d.getLocation(),
					d.getCustomername(), d.getCustomeraddress(),
					d.getContactname(),d.getContactnumber(),d.getCallnumber(),d.getMachineserialnumber(),
					d.getDocumenttype(),userid,userid};
			int row = jdbc.update(sql, params);
			if(row >0)
			{
				//System.out.println("Insert a demand");
				int rowcount = d.getRowcount();
				if(rowcount>0)
				{	
					sql = "";
					for(int x=0;x<rowcount;x++)
					{
						sql =" INSERT INTO  spare_t_demanditem (";
						sql = sql + "CHR_DEMANDNO,INT_GENERALGROUPID,INT_PARTMASTERID,CHR_DESC,CHR_DEFECTIVESERIALNUMBER,";
						sql = sql + " CHR_USRNAME,DT_UPDATEDATE,CHR_UPDATESTATUS )" ;
						sql = sql + " VALUES(?,?,?,?,?,?,DATE(NOW()),'Y' )" ;
						Object[] cparams = new Object[] { Refnumber, r.getParameter("generalgroup"+x) ,r.getParameter("partcode"+x),
								 r.getParameter("idescription"+x), r.getParameter("idefectiveserialnumber"+x),
								   userid };
						int crow = jdbc.update(sql, cparams);
						//System.out.println("Insert demand item");
					}
					
					
					sql = "SELECT FUN_SPR_DEMAND_NUMBER_INSERT('"+d.getLocation()+"')";
					//System.out.println("insert demand number "+sql);
					jdbc.execute(sql);
				}
			}
			 
			return row;
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return 0;
		 
		  
		 		 
	 }
	 
	 
  
	 
	 
	 public int cancelRecord(String row,String userid) 
	 {
		try 
		{
			/*String Refnumber =jdbc.queryForObject("SELECT CHR_DEMANDNO FROM spare_t_demanditem  WHERE INT_ISSUESLIPROWID= "+row+"", String.class);
			
			sql = " SELECT CHR_SERIALNUMBER serial FROM  spare_t_demanditem WHERE CHR_ISSUESLIPREFNO=? ";
			List<Map<String,Object>> d = jdbc.queryForList(sql ,Refnumber);
			Map m = null;
			if(d.size()>0)
			{
				for(int u=0;u<d.size();u++)
				{	
					m   = (Map)d.get(u);
					sql = "UPDATE inv_t_vendorgoodsreceived SET CHR_FLAG ='N', CHR_SALESNO=NULL, DT_SALEDATE = NULL WHERE CHR_SERIALNO=?";
					jdbc.update(sql,m.get("serial"));
				}	
				
			}
			*/
			
			sql = "UPDATE spare_t_demand SET CHR_CANCEL=?,CHR_CANCELEDBY=?,DAT_CANCELDATE=NOW() WHERE INT_DEMANDID=?";
			return jdbc.update(sql,"Y",userid,row);
			 
			
			
			
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return 0;
	 }
	
	 
    
  
	 
	    public List<Demand> editRecord(int rowid) 
		{
			sql = " SELECT INT_DEMANDID,CHR_DEMANDNO,INT_LOCATIONID,CHR_STORAGELOCATION,  ";
			sql = sql + " CHR_CUSTOMERNAME,CHR_ADDRESS,  ";
			sql = sql + " CHR_DOCUMENTTYPE,CHR_CONTACTPERSON,CHR_CONTACTNO,CHR_CALLNUMBER,CHR_MACHINESERIALNUMBER FROM spare_t_demand  WHERE  INT_DEMANDID="+rowid;
			return jdbc.query(sql,new editRowMapper());
		}
		private static final class editRowMapper implements RowMapper<Demand> 
		{
			  public Demand mapRow(ResultSet rs,int rowNum)throws SQLException
			  {
				  Demand d=new Demand();
				  d.setRowid(rs.getInt(1));
				  d.setDemandno(rs.getString(2));
				  d.setLocation(rs.getString(3));
				  d.setStoragelocation(rs.getString(4));
				  d.setCustomername(rs.getString(5));
				  d.setCustomeraddress(rs.getString(6));
				  d.setDocumenttype(rs.getString(7));
				  d.setContactname(rs.getString(8));
				  d.setContactnumber(rs.getString(9));
				  d.setCallnumber(rs.getString(10));
				  d.setMachineserialnumber(rs.getString(11));
				  String sql = " SELECT INT_GENERALGROUPID,INT_PARTMASTERID, CHR_DESC ,CHR_DEFECTIVESERIALNUMBER ";
				  sql =  sql +" FROM spare_t_demanditem WHERE CHR_DEMANDNO = '"+rs.getString(2)+"' ORDER BY INT_ITEMROWID";
				  List<DemandItem> list=  jdbc.query(sql,new editChildRowMapper());
				  d.setItems(list);
				  return d;
			  }
		}
		private static final class editChildRowMapper implements RowMapper<DemandItem> 
		{
			  public DemandItem mapRow(ResultSet rs,int rowNum)throws SQLException
			  {
				  DemandItem d=new DemandItem();
				  d.setGeneralgroupid(rs.getInt(1));
				  d.setPartcode(rs.getInt(2));
				  d.setIdescription(rs.getString(3));
				  d.setIdefectiveserialnumber(rs.getString(4));
				  return d;
			  }
		}

	 
		
		
		
		
		
		public int updateRecord( HttpServletRequest r,Demand d, String userid) 
		{
			try 
			{ 
				//System.out.println("UPDATE ");
				sql = " UPDATE spare_t_demand SET INT_LOCATIONID=?, CHR_STORAGELOCATION=?,  ";
				sql = sql + " CHR_CUSTOMERNAME=?, CHR_ADDRESS=?, CHR_DOCUMENTTYPE=?,CHR_CONTACTPERSON=?,CHR_CONTACTNO=?,CHR_CALLNUMBER=?, CHR_MACHINESERIALNUMBER=?,";
				sql = sql +" CHR_USRNAME =?,DT_UPDATEDATE=DATE(NOW()),CHR_UPDATESTATUS='Y' ";
				sql = sql + " WHERE INT_DEMANDID = ?" ;
				
				
				int row =  jdbc.update(sql,
						d.getLocation(), d.getStoragelocation(), 
						d.getCustomername(), d.getCustomeraddress(),  d.getDocumenttype(),
						d.getContactname(),d.getContactnumber(),d.getCallnumber(),d.getMachineserialnumber(),
						userid ,d.getErowid() ); 
				if(row>0)
				{
					//System.out.println("update demand "+sql);
					sql ="DELETE FROM spare_t_demanditem WHERE CHR_DEMANDNO = ?";;
					int delte_row=jdbc.update(sql,d.getDemandno());
					int rowcount = d.getRowcount();
					//System.out.println("no of item :"+rowcount);
					if(rowcount>0)
					{	
						sql = "";
						for(int x=0;x<rowcount;x++)
						{
							sql =" INSERT INTO  spare_t_demanditem (";
							sql = sql + "CHR_DEMANDNO,INT_GENERALGROUPID,INT_PARTMASTERID,CHR_DESC,CHR_DEFECTIVESERIALNUMBER,";
							sql = sql + " CHR_USRNAME,DT_UPDATEDATE,CHR_UPDATESTATUS )" ;
							sql = sql + " VALUES(?,?,?,?,?,?,DATE(NOW()),'Y' )" ;
							Object[] cparams = new Object[] { d.getDemandno(), r.getParameter("generalgroup"+x) ,r.getParameter("partcode"+x),
									 r.getParameter("idescription"+x), r.getParameter("idefectiveserialnumber"+x),
									   userid };
							int crow = jdbc.update(sql, cparams);
							//System.out.println("Insert demand item");
						}
						
						 
					}
				
					/*
					System.out.println("update demand "+sql);
					sql ="DELETE FROM spare_t_demanditem WHERE CHR_DEMANDNO = ?";;
					int delte_row=jdbc.update(sql,d.getDemandno());
					int rowcount = d.getRowcount();
					if(rowcount>0 )//&& delte_row>0
					{	
						List<DemandItem> l = new ArrayList<DemandItem>();
						DemandItem dd ;
						for(int x=0;x<rowcount;x++)
						{
							dd = new DemandItem();
							dd.setGeneralgroupid(Integer.parseInt(r.getParameter("generalgroup"+x)));
							dd.setDemandno(d.getDemandno());
							dd.setPartcode(Integer.parseInt(r.getParameter("partcode"+x)));
							dd.setIdescription(r.getParameter("idescription"+x));
							dd.setServiceordernumberr(r.getParameter("iserviceordernumber"+x));
							l.add(dd);
							 
						}
						insertBatchIssueSlipItemRecords(l,userid);
					}
					 */
				}
				return row;
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
			return 0;
		}
	  
		public void insertBatchIssueSlipItemRecords(final List<DemandItem> dd, final String userid)
		{
			  sql =" INSERT INTO  spare_t_demanditem (";
			  sql = sql + "INT_GENERALGROUPID,CHR_DEMANDNO,INT_PARTMASTERID,CHR_DESC,  ";
			  sql = sql + "CHR_DEFECTIVESERIALNUMBER, CHR_USRNAME,DT_UPDATEDATE,CHR_UPDATESTATUS )" ;
			  sql = sql + " VALUES(?,?,?,?,?,?,DATE(NOW()),'Y' )" ;
			  jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() 
			  {
			 	@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
			 		DemandItem d = dd.get(i);
			 		ps.setInt(1, d.getGeneralgroupid());
					ps.setString(2, d.getDemandno().trim());
					ps.setInt(3, d.getPartcode());
					ps.setString(4, d.getIdescription().trim());
					ps.setString(5, d.getIdefectiveserialnumber().trim());
					ps.setString(6, userid);
					//System.out.println("statement "+ps);
				}
			 
				@Override
				public int getBatchSize() {
					return dd.size();
				}
			  });
			}
		
		
		
		
		public int deleteRecord(String rowids) 
		{
			//System.out.println("delete the record");
			String ids[] = rowids.split(",");
			int row=0;
			 
			for(int x=0;x<ids.length;x++)
			{
				String Refnumber =jdbc.queryForObject("SELECT CHR_DEMANDNO FROM spare_t_demand  WHERE INT_DEMANDID= "+ids[x]+"", String.class);
				row  = jdbc.update("DELETE FROM spare_t_demanditem WHERE CHR_DEMANDNO =  '"+ Refnumber+"' ");
				row  = jdbc.update("DELETE FROM spare_t_demand WHERE  INT_DEMANDID= "+ ids[x]+"   AND  CHR_CANCEL ='N'"  );
			}
			
			return row;
		}
		
		
		
		
		
	 /* To get all records from database */
	 public List<Demand> getItems()
	 {
		try
		{
			sql = "";
			sql = sql+ " SELECT INT_DEMANDID,CHR_DEMANDNO,CHR_STORAGELOCATION,INT_LOCATIONID,CHR_CUSTOMERNAME,CHR_CALLNUMBER,CHR_ADDRESS, ";
		 	sql = sql+ " CHR_DOCUMENTTYPE,DATE_FORMAT(DT_CREATIONTIME,'%d-%b-%Y'), ";
		 	sql = sql+ " IF(CHR_CANCEL='Y','Cancel',''),FIND_A_EMPLOYEE_ID_NAMEONLY_BY_USERID(CHR_CREATEDBY),CHR_STATUS, ";
		 	sql = sql+ " DATEDIFF(NOW(),DT_CREATIONTIME )";
		 	sql = sql+ " FROM spare_t_demand a "; 
		 	sql = sql+ " WHERE DAY(DT_CREATIONTIME) = DAY(NOW()) ";
		 	sql = sql+ " AND MONTH(DT_CREATIONTIME) = MONTH(NOW()) ";
		 	sql = sql+ " AND YEAR(DT_CREATIONTIME) = YEAR(NOW()) ";
		 	sql = sql+ " ORDER BY INT_DEMANDID ";
		 	
		 	sql = "";
		 	sql = sql+ " SELECT a.INT_DEMANDID,a.CHR_DEMANDNO,a.CHR_STORAGELOCATION,a.INT_LOCATIONID,a.CHR_CUSTOMERNAME,a.CHR_CALLNUMBER,a.CHR_ADDRESS, "; 
		 	sql = sql+ " a.CHR_DOCUMENTTYPE,DATE_FORMAT(a.DT_CREATIONTIME,'%d-%b-%Y'),    ";
		 	sql = sql+ " IF(a.CHR_CANCEL='Y','Cancel',''),b.CHR_STAFFNAME ,a.CHR_STATUS,   ";
		 	sql = sql+ " DATEDIFF(NOW(),a.DT_CREATIONTIME ) ";
		 	sql = sql+ " FROM spare_t_demand a  , com_m_staff b ";
		 	sql = sql+ " WHERE a.CHR_CREATEDBY = b.CHR_USERID AND DAY(a.DT_CREATIONTIME) = DAY(NOW())";  
		 	sql = sql+ " AND MONTH(a.DT_CREATIONTIME) = MONTH(NOW())  ";
		 	sql = sql+ " AND YEAR(a.DT_CREATIONTIME) = YEAR(NOW())  ";
		 	sql = sql+ " ORDER BY a.INT_DEMANDID ";		 	 
		 	System.out.println(sql);
		 	return jdbc.query(sql,new listRowMapper());

		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return null;
			 
	}
	 

	public List<Demand> sortData(Map request) 
	{
		
		 
	 	sql = "";
		sql = sql+ " SELECT INT_DEMANDID,CHR_DEMANDNO,CHR_STORAGELOCATION,INT_LOCATIONID,CHR_CUSTOMERNAME,CHR_CALLNUMBER, CHR_ADDRESS,";
	 	sql = sql+ " CHR_DOCUMENTTYPE,DATE_FORMAT(DT_CREATIONTIME,'%d-%b-%Y'), ";
	 	sql = sql+ " IF(CHR_CANCEL='Y','Cancel',''),FIND_A_EMPLOYEE_NAME(CHR_CREATEDBY),CHR_STATUS, ";
	 	sql = sql+ " DATEDIFF(NOW(),DT_CREATIONTIME )";
	 	sql = sql+ " FROM spare_t_demand WHERE INT_DEMANDID > 0"; 
	 	 
	 	if(!"0".equals(request.get("day")))
	 		sql = sql+ " AND DAY(DT_CREATIONTIME) = "+request.get("day");
	 	if(!"0".equals(request.get("month")))
	 		sql = sql+ " AND MONTH(DT_CREATIONTIME) =  "+request.get("month");
	 	if(!"0".equals(request.get("year")))
	 		sql = sql+ " AND YEAR(DT_CREATIONTIME) =  "+request.get("year");
	 	if(!"0".equals(request.get("status")))
	 		sql = sql+ " AND CHR_STATUS =  '"+request.get("status")+"' ";
		if(!"0".equals(request.get("startLetter")))
			sql += " AND CHR_CUSTOMERNAME LIKE '"+request.get("startLetter")+"%'";
		 
		return jdbc.query(sql,new listRowMapper());
	}

	
	
	
	/* Inner class to map all records  on database */
	private static final class listRowMapper implements RowMapper<Demand> 
	{
		 
			public Demand mapRow(ResultSet rs,int rowNum)throws SQLException
			{
				Demand d = new Demand();
				d.setRowid(rs.getInt(1));
				d.setDemandno(rs.getString(2));
				d.setStoragelocation(rs.getString(3));
				d.setLocation(rs.getString(4));
				d.setCustomername(rs.getString(5));
				d.setCallnumber(rs.getString(6));
				d.setCustomeraddress(rs.getString(7));
				d.setDocumenttype(rs.getString(8));
				d.setCreationtime(rs.getString(9));
				d.setCancel(rs.getString(10));
				d.setCreatedby(rs.getString(11));
				d.setStatus(rs.getString(12));
				d.setDemandage(rs.getInt(13));
				return d;
		  }
		 
	  }
	
		
	
	
	///UPDATE DEMAND ISSUE
	public int updateDemandIssue( @RequestParam Map<String,String> req ,  HttpSession session) 
	{
		try 
		{  
			
			String userid = (String) session.getAttribute("USERID");
			String empid = (String) session.getAttribute("EMPID");
			String demandno = req.get("demandno");
			String officeid = req.get("officeid");
			int itemrowcount = Integer.parseInt(req.get("itemrowcount"));
			if(itemrowcount>0)
			{
				String issuenumber =jdbc.queryForObject("SELECT FUN_SPR_DEMAND_ISSUE_NUMBER("+officeid+")", String.class);
				List<Map<String,Object>> al=null;
				for(int u=0; u<itemrowcount;u++)
				{
					
					if("Y".equals(req.get("issued"+(u+1))))
					{	
						//update the demand item
						sql ="UPDATE spare_t_demanditem SET CHR_SERIALNUMBER=? , CHR_ISSUED=?,CHR_ISSUENO=?,DOU_VALUE=?,";
						sql = sql + " INT_PARTMASTERID1 =?,CHR_DESC1 = ?, ";
						sql = sql + " DAT_ISSUEDDATE =NOW(), CHR_ISSUEDBY=?,CHR_USRNAME=?,DT_UPDATEDATE=DATE(NOW()) WHERE INT_ITEMROWID=? ";
						Object[] cparams = new Object[] {  req.get("iserialnumber"+(u+1)) , ("Y".equals(req.get("issued"+(u+1)))?"Y":"N" ), issuenumber,req.get("ivalue"+(u+1)),
								req.get("partcode"+(u+1)),req.get("idescription"+(u+1)),
								empid, userid,req.get("itemrowid"+(u+1)) };
						int crow = jdbc.update(sql, cparams);
						
						//update inventopry
						if(crow>0)
						{
							sql = "UPDATE inv_t_vendorgoodsreceived SET CHR_FLAG ='S', CHR_SALESNO=?, DT_SALEDATE = NOW() WHERE CHR_SERIALNO=?";
							jdbc.update(sql, issuenumber, req.get("iserialnumber"+(u+1)) );
						}
					}
				}
				
				sql = "SELECT FUN_SPR_DEMAND_ISSUE_NUMBER_INSERT('"+officeid+"')";
				jdbc.execute(sql);
				
				
				sql ="SELECT CHR_ISSUED issue FROM spare_t_demanditem WHERE CHR_DEMANDNO=? GROUP BY CHR_ISSUED ";
				al= jdbc.queryForList(sql,demandno);
				
				//UPDATE DEMAND
				sql = " UPDATE spare_t_demand SET CHR_STATUS=?,CHR_USRNAME=?, DT_UPDATEDATE= DATE(NOW()) 	WHERE CHR_DEMANDNO=?  ";
				jdbc.update(sql,( (al.size()>1)?"P":"Y"),userid, demandno);
				
				//update demand despatch details
				sql = "INSERT INTO spare_t_issueslip_despatch (CHR_ISSUENO) VALUES (?)";
				int crow = jdbc.update(sql, issuenumber);
								
			}
			return 0;
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	 
	
	
	
	
	
	
	public List<Map<String,Object>> loadpartCodedropdown(Map request) 
	{
		sql ="SELECT INT_PARTMASTERID id ,CHR_PARTNO code  FROM spare_m_partmaster WHERE INT_GENERALGROUPID  ="+request.get("generalgroup");
		return jdbc.queryForList(sql);
	}

		
	 public String loadpartCodeDescription(Map request) 
	 {
			String descript =jdbc.queryForObject("SELECT CHR_DESC  FROM spare_t_issueslip WHERE INT_ISSUESLIPROWID  ="+request.get("partcode"), String.class);
			return descript;
	}
	 
	 
	 
	 public List<Map<String,Object>>  loadSerialnumberIssueslip(Map request) 
	{
		String partcode = ""+request.get("partcode");
		sql = "SELECT CHR_TYPE itype ,CHR_ITEMID icode  FROM spare_m_partmaster WHERE INT_PARTMASTERID=?";
		Map<String,Object> m = jdbc.queryForMap(sql, partcode);
		String serial = ""+request.get("serial");
		sql = "SELECT CHR_SERIALNO iserial ,DOU_UNITPRICE iprice FROM inv_t_vendorgoodsreceived WHERE CHR_TYPE=? AND CHR_ITEMID =? AND CHR_SERIALNO IS NOT NULL AND  CHR_FLAG ='N' AND CHR_SERIALNO LIKE '%"+serial+"%'";
		return jdbc.queryForList(sql,m.get("itype"),m.get("icode"));
	}
	 
}


