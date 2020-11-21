package com.org.my.mailtemplates;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

 
@Component
public class Footer 
{
	
	private static JdbcTemplate jdbc;
	
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	public static String  footerContent(String branchid)
    {
    	String content="";
    	try
    	{
    		
    		String ssql = " SELECT b.CHR_COMPANYNAME cpyname,a.CHR_BRANCHNAME bchname,a.CHR_STREET street,e.CHR_CITYNAME city,d.CHR_STATENAME state ,a.INT_PINCODE pincode,a.CHR_PHONE phone,  ";
    		ssql = ssql +" a.CHR_FAX fax,a.CHR_EMAIL email, a.CHR_TNGSTNO tngst,a.CHR_CSTNO cst,a.CHR_REGNO reg,a.CHR_TINNO tin,a.CHR_LOCALTAXNO localtax,a.CHR_PANNO pan, "; 
    		ssql = ssql +" a.CHR_SERVICETAX ,d1.CHR_DISTRICT district,a.CHR_CIN_NUMBER FROM com_m_branch a , com_m_company b ,com_m_country c, com_m_state d, com_m_district d1, com_m_city e ";
    		ssql = ssql +" WHERE  a.INT_CITYID = e.INT_CITYID ";
    		ssql = ssql +" AND a.INT_COUNTRYID = c.INT_COUNTRYID ";
    		ssql = ssql +" AND a.INT_STATEID =d.INT_STATEID AND a.INT_DISTRICTID =d1.INT_DISTRICTID  ";
    		ssql = ssql +" AND a.INT_COMPANYID = b.INT_COMPANYID  ";
    		ssql = ssql +" AND a.INT_BRANCHID = "+ branchid;
    		System.out.println("JDBC :"+jdbc);
    		Map<String,Object> m =jdbc.queryForMap(ssql);
    		content = content + "<strong>"+m.get("cpyname")+"</strong><br> ";
    		if(!"-".equals(m.get("street")))
    				 content = content + ""+m.get("street")+" | ";
    		if(!"-".equals(m.get("city")))
				 content = content + ""+m.get("city")+" | ";
    		if(!"-".equals(m.get("district")))
				 content = content + ""+m.get("district")+" | ";
    		if(!"-".equals(m.get("state")))
				 content = content + ""+m.get("state")+"<br> ";
			content = content + "<br>Note :- This is an automatically generated email. Please do not reply to this message.<br><br> ";
			
    	}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return content;
	}
	
	public static String  footerContent( )
    {
    	String content="";
    	try
    	{
    		
    		String ssql = " SELECT a.CHR_NAME name,a.CHR_ADDRESS1 ad1,a.CHR_ADDRESS2 ad2 ,e.CHR_CITYNAME city ,d.CHR_STATENAME state,a.INT_PINCODE pincode, a.CHR_PHONE phone, ";  
    		ssql = ssql + " a.CHR_FAX fax ,a.CHR_EMAIL email, d1.CHR_DISTRICT  district ";
    		ssql = ssql + " FROM m_company a,com_m_country c, com_m_state d, com_m_district d1, com_m_city e ";  
    		ssql = ssql + "  WHERE  a.INT_CITYID = e.INT_CITYID  ";
    		ssql = ssql + "  AND a.INT_COUNTRYID = c.INT_COUNTRYID "; 
    		ssql = ssql + "  AND a.INT_STATEID =d.INT_STATEID AND a.INT_DISTRICTID =d1.INT_DISTRICTID ";  
    		Map<String,Object> m =jdbc.queryForMap(ssql);
    		
    		content = content + "<strong>"+m.get("name")+"</strong><br> ";
			if(!"-".equals(m.get("ad1")))
			 content = content + ""+m.get("ad1")+" | ";
			if(!"-".equals(m.get("ad2")))
				 content = content + ""+m.get("ad2")+" | ";
			if(!"-".equals(m.get("city")))
				 content = content + ""+m.get("city")+" | ";
			if(!"-".equals(m.get("district")))
				 content = content + ""+m.get("district")+"<br> ";
			if(!"-".equals(m.get("state")))
				 content = content + ""+m.get("state")+"<br> ";
			 content = content + "<br>Note :- This is an automatically generated email. Please do not reply to this message.<br><br> ";
    	}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return content;
	}
}
