package com.org.my;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class Login 
{
	private static String  sql = null;
	
	private static JdbcTemplate jdbc;
	
	 
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	
	
	public static String  checkLogin( Model model,String user, String pass , HttpSession session)
	{
		try
		{
			sql = "";
			sql = "SELECT a.CHR_TYPE usertype,a.CHR_EMPID empid, b.CHR_STAFFNAME staffname ,b.INT_OFFICEID officeid, ";
			sql = sql + " c.CHR_OFFICENAME officename,b.INT_BRANCHID branchid,b.CHR_EMAILID,b.CHR_MOBILE, b.CHR_PERMAILID,b.INT_DEPARTID deptid,b.CHR_CATEGORY category, ";
			sql = sql + " a.INT_SESSIONTIME sessiontime FROM m_user a ,com_m_staff b , com_m_office  c WHERE a.CHR_EMPID =b.CHR_EMPID   ";
			sql = sql + " AND b.INT_OFFICEID = c.INT_OFFICEID AND b.CHR_TYPE !='T'  AND b.CHR_HOLD!='Y'  ";
			sql = sql + " AND  a.CHR_USRNAME='"+user+"'  AND FUN_GET_PASSWORD(a.CHR_EMPID) = '"+pass+"' ";
			Map<String,Object> m =jdbc.queryForMap(sql);
			session.setAttribute("USERTYPE",m.get("usertype") );
			session.setAttribute("USERID", user);
			session.setAttribute("EMPID",m.get("empid") );
			session.setAttribute("USERNAME",m.get("staffname") );
			session.setAttribute("BRANCHID",m.get("branchid") );
			session.setAttribute("OFFICEID",m.get("officeid") );
			session.setAttribute("DEPARTID",m.get("deptid") );
			session.setAttribute("CATEGORY",m.get("category") );
			session.setAttribute("OFFICENAME",m.get("officename") );
			session.setMaxInactiveInterval(Integer.parseInt(m.get("sessiontime").toString()));
			return "redirect:home";
		
			
		}
		catch(Exception e)
		{
			return "redirect:erlogin";
			
		}
	}
	
	
 	
	public static void Logout(HttpSession session,HttpServletResponse   response,HttpServletRequest request )
	{
		try
		{
			final String refererUrl = request.getHeader("Referer");
		    response.setHeader(refererUrl, "no-cache");
		    response.setHeader("Cache-Control", "no-cache");
		    response.setDateHeader("Expires", 0);
		    session.removeAttribute("USERTYPE");
		    session.removeAttribute("USERID");
		    session.removeAttribute("EMPID");
		    session.removeAttribute("USERNAME");
		    session.removeAttribute("BRANCHID");
		    session.removeAttribute("OFFICEID");
		    session.removeAttribute("DEPARTID");
		    session.removeAttribute("CATEGORY");
		    session.removeAttribute("OFFICENAME");
		    session.invalidate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void resignedLogin()
	{
		try
		{
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
