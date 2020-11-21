<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: SPARE  ::</title>
<%@include file = "../menus/frameworks.jsp" %>
 <sql:query var="rsoffice" dataSource="jdbc/spring">SELECT INT_OFFICEID,CHR_OFFICENAME FROM com_m_office </sql:query>
    
<style>
 .centerDiv 
 { width: 60%; height:200px; margin: 0 auto;  } 

</style>
<style type="text/css"> 
.exactCenter 
{ 
position: fixed; width:500px;  
top: 50%; 
left: 50%; 
margin-top: -150px; 
margin-left: -200px; } 
</style> 



</head>
<body>


<div class="exactCenter"> 
	<div class="panel panel-info">
	  	<div class="panel-heading text-center"><h1>SPARE</h1></div>
	  	<div class="panel-body">
		  <form class = "form-horizontal" action = "SpareController" method="post" name="frm">
		  	<div class='row'>
		   		<div class='col-md-2'></div>
		   		<div class='col-md-4 text-right' >Select Office
		   		<input type='hidden' id ='sessionbranch'  name='sessionbranch' value='${BRANCHID}'/>
		   		</div>
		   		<div class='col-md-5'>
			   		<select id='office' name='office' required   class="form-control" onchange="loadBranch() " >
			   			<option value=''>Select Office</option>
			        	<c:forEach var="data" items="${rsoffice.rows}">
			          		<option value='<c:out value="${data.INT_OFFICEID}" />'><c:out value="${data.CHR_OFFICENAME}" /></option>
			        	</c:forEach>
			        </select>
		   		</div>
		   		<div class='col-md-1'></div>
	 		</div>
	 			
	 		 
					<div class='row'><br>
						<div id='branchcontent'></div>
					</div>
					
					<div align='center'>
		    	<button class="btn btn-primary showAction" type="submit" id = "formButton"><span class="glyphicon glyphicon-ok"></span>&nbsp;Submit </button>
		    	<a href = "home" type = "button" class = "btn btn-primary madalClose" ><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</a>
	        
	 	</form>
	  </div>
</div>
</div>

<script type="text/javascript">

function callAjaxMethod(type,url,param,successMethod) 
{
	
	$.ajax({
		url:url,
		type:type,
		data:param,
		success:function(result)
		{
			successMethod(result);
			
		}
	});
}



function loadBranch() 
{
	var param = { "officeid":document.getElementById("office").value };
	callAjaxMethod("POST","loadBranchDetails",param,loadBranchDetailsMessage);
}

function loadBranchDetailsMessage(result) 
{
	try
	{
		
		var rowLength =  result.length;
		var sessionbranch = $('#sessionbranch').val();
		if(rowLength === 0) 
		{
			$("#branchcontent").val("Data not found...");
		}	
		else 
		{
			$("#branchcontent").val("");
			var str="";
			str=str+"<table class='table' style='width=90%;'>";
			str=str+" <thead>";
			str=str+"<tr>";
			str=str+"<th class='success text-center'>Company Name</th>";
			str=str+"<th class='success text-center'>Branch Name</th>";
			str=str+"</tr>";
			for (var u = 0;u<rowLength;u++) 
	   		{
				str=str+"<tr>";
				str=str+"<td class='success text-center'>";
				if(sessionbranch == result[u].bid)
					str=str+"<input required  name='branchid' checked='checked'  id='branchid'   type='radio' value='"+result[u].bid+"' /> "+result[u].cpyname+"</td>";
				else
					str=str+"<input required  name='branchid'  id='branchid'   type='radio' value='"+result[u].bid+"' /> "+result[u].cpyname+"</td>";
				str=str+"<td class='success text-center'>"+result[u].bname+"</td>";
				str=str+"<tr>";
			}
			str=str+"</table>";
			$("#branchcontent").html(str);
	   	}
		
	}
	catch(err)
	{
		alert(err.desc);
	}
	
}

</script>
	  	
</body>
</html>