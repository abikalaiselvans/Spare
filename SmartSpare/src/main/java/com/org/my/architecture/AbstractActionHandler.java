package com.org.my.architecture;


import javax.servlet.http.HttpSession;
import org.springframework.jdbc.core.JdbcTemplate;


public abstract  class AbstractActionHandler   implements ActionHandler
{
	 
    
    protected String Path  ;
    protected String asql  ;
    protected String EncodeKey;
    protected String action;
    protected HttpSession asession;
    protected String  auserid;
    protected JdbcTemplate  jdbctemplate;
     
    public AbstractActionHandler()
    {
    }
    
    
    public void setPath(String Path)
    {
        this.Path = Path;
    }
    
    
     
    public void setSql(String asql)
    {
        this.asql = asql;
    }
    
    public void setEncodeKey(String EncodeKey)
    {
        this.EncodeKey = EncodeKey;
    }
    
    public void setAction(String action)
    {
        this.action = action;
    }
    
    public void setSession(HttpSession session)
    {
        this.asession = session;
    }
    
    
    public void setUserid(String auserid)
    {
        this.auserid = auserid;
    }
 	
    public void setDatasource(JdbcTemplate jdbctemplate)
    {
        this.jdbctemplate = jdbctemplate;
    }
    
	
	 
}
