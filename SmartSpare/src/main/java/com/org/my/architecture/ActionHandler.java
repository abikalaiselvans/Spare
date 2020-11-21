package com.org.my.architecture;


import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public interface ActionHandler 
{

	public abstract String  handle(Locale locale, Model model,@RequestParam Map<String,String> req,HttpSession session);
    public abstract void setPath(String path);
    public abstract void setSql(String sql);
    public abstract void setEncodeKey(String sql);
    public abstract void setAction(String action);
    public abstract void setSession(HttpSession session);
    public abstract void setUserid(String auserid);
    public abstract void setDatasource(JdbcTemplate jdbctemplate);
   
}
