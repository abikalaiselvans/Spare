<%@ page import="java.sql.Connection,java.sql.Statement,java.sql.ResultSet" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.Context,javax.naming.InitialContext" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
HttpServletResponse httpres = (HttpServletResponse) response;
httpres.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
httpres.setHeader("Pragma", "no-cache"); // HTTP 1.0.
httpres.setDateHeader("Expires", 0); // Proxies.
%>
<c:if test="${USERID  == null }">
   <c:redirect url="logout"/>
</c:if>

<%@include file = "../menus/frameworks.jsp" %>
 
 
<nav class = "navbar navbar-inverse navbar-static-top">
	<div class = "container">
		<div class="navbar-header">
			<a href = "http://www.careind.net/" class = "navbar-brand">Care IT Solution</a>
			<button type = "button" class = "navbar-toggle collapsed" data-toggle = "collapse" data-target = "#bs-example-navbar-collapse-1">
        		<span class = "sr-only">Toggle navigation</span>
        		<span class = "icon-bar"></span>
        		<span class = "icon-bar"></span>
        		<span class = "icon-bar"></span>
      		</button>
     	</div>	  
		<div class = "collapse navbar-collapse" id = "bs-example-navbar-collapse-1">
			<ul class = "nav navbar-nav navbar-right">
			
			<li><a href = "#">Welcome to ${USERNAME}</a></li>
			<li>&nbsp;</li>
			<%
			String sql,menuType,menuName;
			Boolean currentFlag,nextFlag;
			Connection con;
			Statement mst;
			ResultSet rs = null;
			Context context = new InitialContext();
			DataSource dataSource;
			try 
			{
					dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spring");
					con = dataSource.getConnection();
					mst = con.createStatement();
					sql="SELECT CHR_MENUHRM from  m_user  where CHR_USRNAME='"+session.getAttribute("USERID")+"'" ;
					rs=mst.executeQuery(sql); 
				    if(rs.next())
				    {
				    	String  mnuList=rs.getString("CHR_MENUHRM");
				    	sql = "Select * from  m_menu_hrm WHERE INT_MENUID >0 AND INT_MENUID in ("+mnuList+") order by INT_MENUID  ";
						rs = mst.executeQuery(sql);
						while(rs.next())
						{
							menuType = rs.getString("CHR_MENUDES");
							currentFlag = true;
							nextFlag = true;
							menuName=(rs.getString("CHR_MENUNAME"));
							if(menuType.equals("Root"))
								currentFlag = true;
							else 
								currentFlag = false;
							
							if(!rs.isLast()) 
							{
								rs.next();
								menuType = rs.getString("CHR_MENUDES");
								if(menuType.equals("Root"))
									nextFlag = true;
								else
									nextFlag = false;
								rs.previous();	
							}
						
							if(currentFlag && nextFlag)
							{
								if(rs.getString("CHR_MENUNAME").equals("Home"))
									out.println("\n <li class = 'active'><a href='"+rs.getString("CHR_URL")+"'>"+menuName+"</a></li>");
								else
									out.println("\n <li><a href='"+rs.getString("CHR_URL")+"'>"+menuName+"</a></li>");
							}
							else if(currentFlag && nextFlag == false)
							{
								out.println("<li class = 'dropdown'>");
								out.println("<a href = '#' class = 'dropdown-toggle' data-toggle = 'dropdown'>"+menuName+" <b class = 'caret'></b></a>");
								out.println("<ul class = 'dropdown-menu'>");
							}
							else if(currentFlag == false && nextFlag == false)
							{
								out.println("<li><a href = '"+rs.getString("CHR_URL")+"'>"+menuName+"</a></li>");
							}
							else 
							{
								out.println("<li><a href = '"+rs.getString("CHR_URL")+"'>"+menuName+"</a></li></ul>");
							}
						}	
				    }
				 
				}
				catch(Exception e)
				{
					System.out.println("Error : "+e.getMessage());
				}
				
			%>
			</ul>
		</div>
	</div>
</nav>			
			  
				 	
				