<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<title>Error Page</title>
<%@include file = "../menus/frameworks.jsp" %>

<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/smartspare.css">


</head>
 


<body>
<%@include file = "../menus/homemenu.jsp" %>
<br><br><br><br>
 
 

<div class="container">
  <div class="jumbotron">
    <center><h1>Error Message</h1></center>
    <br>
    <mark><p class='text-danger'> ${errmessage} </p></mark>
  </div>
   
  
</div>

 </body>

</html>
    	
 <%@include file = "../footer.jsp" %>
    
       		
       		
   