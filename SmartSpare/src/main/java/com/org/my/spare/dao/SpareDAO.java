package com.org.my.spare.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class SpareDAO 
{

	private static JdbcTemplate jdbc;
	private String sql = "";
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}

	public List<Map<String,Object>> loadBranchDetails(Map request) 
	{
	    String id = ""+request.get("officeid");
		sql = " SELECT b.INT_COMPANYID cpyid,UPPER(a.CHR_COMPANYNAME) cpyname,b.INT_BRANCHID bid,UPPER(b.CHR_BRANCHNAME) bname FROM com_m_branch b, com_m_company a   WHERE a.INT_COMPANYID = b.INT_COMPANYID ";
		sql = sql + " AND SOUNDEX(b.CHR_BRANCHNAME) = SOUNDEX((SELECT CHR_OFFICENAME FROM  com_m_office  ";
		sql = sql + "WHERE INT_OFFICEID = "+ id + ")) AND a.INT_ACTIVE =1  AND b.INT_ACTIVE =1 ORDER BY a.CHR_COMPANYNAME ";
		return jdbc.queryForList(sql);
	}

	public void ControlAssign(Map request, Model model, HttpSession session)
	{
		String office = ""+request.get("office");
		String branchid = ""+request.get("branchid");
		sql = " SELECT b.INT_COMPANYID cpyid,a.CHR_COMPANYNAME cpyname,b.CHR_BRANCHNAME bname,b.INT_BRANCHID bid,a.CHR_SHORTNAME sname FROM com_m_branch b, com_m_company a ";
		sql = sql +"	WHERE a.INT_COMPANYID = b.INT_COMPANYID 	AND b.INT_BRANCHID="+Integer.parseInt(branchid);
		System.out.println(sql);
		Map<String,Object> m =jdbc.queryForMap(sql);
		session.setAttribute("SPRCOMPANY", m.get("cpyid"));	
		session.setAttribute("SPRBRANCH",m.get("bid"));	
		session.setAttribute("SPROFFICEID", office);	
		session.setAttribute("COMPANYSHORTNAME", m.get("sname"));	
		 m =jdbc.queryForMap("SELECT INT_STATEID stateid FROM com_m_office WHERE INT_OFFICEID ="+office);
		session.setAttribute("SPRSTATE", m.get("stateid"));
		 
	}
	
}

