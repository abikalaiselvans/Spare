<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<title>Problems</title>
 
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/css/smartspare.css">
<style>
#contentTable > #tbody>tr:nth-child(odd) 
{
   	background-color : #bdf4f4;
   	color:#000000;
	 
}--

#contentTableTable > #tbody>tr:nth-child(even) 
{
  	background-color : #f7f7f7;
   	color:#000000;
	 
}
</style>

</head>
<body id = "homeBody">
<%@include file = "sparemenu.jsp" %>
<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="resources/js/jquery-ui.js"></script>
 <script type="text/javascript" src="resources/js/common/uniqueAutocompleteAjax.js" ></script>
 
     
<form id = "homeForm" action = "" method = "POST" autocomplete="on">
       
<div class="container"><!--container for table -->
	<div class="panel panel-primary"><!-- panel start -->
  			<div class="panel-heading"><h5 class="panel-title">PROBLEM </h5></div>
  		 		<div class="panel-body"> <!-- panel body start -->
  					<!-- content start -->
  					<table class = "table" id = "contentTable" width='99%'>
       							<thead>
       							
       							
       							<tr>
       									<th  colspan='3'>
       									 
       									 <div class='row'>
       									 	<div class="col-sm-5"></div>
       									 	<div class="col-sm-1">Group </div>
       									 	<div class="col-sm-2">
	       									 	<select name='groupid' id='groupid'  class="form-control" onChange ="loadEquipementModelStartwith('0')">
	       										<option value='0' > All</option>
	       											<c:forEach var="row" items="${ProblemGroup}"  varStatus="counter">
	     												<option value='${row.rowid}' >${row.name} </option>
	     											</c:forEach>
	       										</select>
       									 	</div>
       									 	<div class="col-sm-4"></div> 
       									 
       									 </div>
       									 
       									 
       									</th>
       								</tr>
       							 	<tr>
       									<th width='10%'><center>S.No.</center></th>
       									<th width='25%'><center>Group</center></th>
       									<th width='65%'><center>Problem</center></th>
       									 
       								</tr>
       							</thead>
       							<tbody id = "tbody">
       								<c:forEach var="row" items="${result}"  varStatus="counter">
     									<tr>
								    		<td width='10%'><c:out value="${counter.count}"/>.&nbsp;<input type="checkbox"  name="rowid" class ="checkList" value= "${row.rowid}"></td>
      										<td width='25%'>${row.groupname}</td>
      										<td width='65%'>${row.name}</td>
      									</tr>
									</c:forEach>
       							</tbody>
       									
       						</table>
  					<!-- content end -->
  				
  				<div class="row">
  				 <div class="col-md-12 text-center" >
  				 		<ul class="pagination " >
						    <li><a href="#" onclick="callSort('0')">All</a></li>
						    <li><a href="#" onclick="callSort('A')">A</a></li>
						    <li><a href="#" onclick="callSort('B')">B</a></li>
						    <li><a href="#" onclick="callSort('C')">C</a></li>
						    <li><a href="#" onclick="callSort('D')">D</a></li>
						    <li><a href="#" onclick="callSort('E')">E</a></li>
						    <li><a href="#" onclick="callSort('F')">F</a></li>
						    <li><a href="#" onclick="callSort('G')">G</a></li>
						    <li><a href="#" onclick="callSort('H')">H</a></li>
						    <li><a href="#" onclick="callSort('I')">I</a></li>
						    <li><a href="#" onclick="callSort('J')">J</a></li>
						    <li><a href="#" onclick="callSort('K')">K</a></li>
						    <li><a href="#" onclick="callSort('L')">L</a></li>
						    <li><a href="#" onclick="callSort('M')">M</a></li>
						    <li><a href="#" onclick="callSort('N')">N</a></li>
						    <li><a href="#" onclick="callSort('O')">O</a></li>
						    <li><a href="#" onclick="callSort('P')">P</a></li>
						    <li><a href="#" onclick="callSort('Q')">Q</a></li>
						    <li><a href="#" onclick="callSort('R')">R</a></li>
						    <li><a href="#" onclick="callSort('S')">S</a></li>
						    <li><a href="#" onclick="callSort('T')">T</a></li>
						    <li><a href="#" onclick="callSort('U')">U</a></li>
						    <li><a href="#" onclick="callSort('V')">V</a></li>
						    <li><a href="#" onclick="callSort('W')">W</a></li>
						    <li><a href="#" onclick="callSort('X')">X</a></li>
						    <li><a href="#" onclick="callSort('Y')">Y</a></li>
						    <li><a href="#" onclick="callSort('Z')">Z</a></li>
						  </ul>
					 </div>
				 </div>
				
 		</div>	<!-- panel body end -->
 	</div><!-- end panel -->
 </div><!-- end container -->
  
 
<!-- Deleted from add buttondata-toggle = "modal" data-target="#addNewEquipmentType" -->
<div class = "container">

	<div class='row'>
		 
		<div class="text-center" >
		
			<sql:query var="rsprevillege" dataSource="jdbc/spring">
				SELECT CHR_ADD_SPR,CHR_EDIT_SPR,CHR_DELETE_SPR FROM m_user_privilege WHERE CHR_USRNAME =?
			<sql:param value="${USERID}" />
			</sql:query>
			<c:forEach var="row" items="${rsprevillege.rowsByIndex}">
				
				<c:if  test='${(row["0"] eq "Y")}'>
				 	<button type="button" class ="btn btn-success"  id = "Add"><span class="glyphicon glyphicon-plus"></span>&nbsp;Add</button>&nbsp;
				</c:if>
				
				<c:if  test='${(row["1"] eq "Y")}'>
					<button type="button" class="btn btn-info" id = "Edit"><span class="glyphicon glyphicon-edit"></span>&nbsp;Edit</button>&nbsp;
				</c:if>
				
				<c:if  test='${(row["2"] eq "Y")}'>
					<button type="button" class="btn btn-danger" id = "Delete" ><span class="glyphicon glyphicon-trash"></span>&nbsp;Delete</button>&nbsp;
				</c:if>
				 
			</c:forEach>
			<button type="button" class="btn btn-warning" id = "Close"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</button>&nbsp;
		</div>
		 
	</div>		
</div>
 
 <br>
 <br>
 </form>  
 
 
 <%@include file = "ProblemsModals.jsp" %>
 
  <script src = "resources/js/spare/ProblemsOperations.js"></script>     	

     	
 



 <script>
$(document).ready(function()
		{
	 $('#contentTable').dataTable({
	    	"order": [[ 1, "asc" ]],	
	    "sScrollY":"400px"}); 
    	
    });

function callSort(letter)
{
	var groupid = $('#groupid').val();
	alert("ProblemsSort/"+letter+"/"+groupid)
	window.location.href = "ProblemsSort/"+letter+"/"+groupid;
}

</script>    	 	

     	
     	
     	
     		
     	
     	

     
     	
 <%@include file = "../footer.jsp" %>
    
       		
       		
       		
<div class="alert  fade in" id = "alertBox" style="display: none">
	    <strong id = "alert-title"></strong><span id = "alert-content">&nbsp;</span>
</div>
       		
     
       
</body>
</html>