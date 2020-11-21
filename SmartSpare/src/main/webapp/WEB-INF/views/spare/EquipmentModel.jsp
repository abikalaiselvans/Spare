<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<title>Model</title>
 
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
 <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/css/smartspare.css">
<style>
#EquipmentModelTable > #tbody>tr:nth-child(odd) 
{
   	background-color : #bdf4f4;
   	color:#000000;
	 
}--

#EquipmentModelTable > #tbody>tr:nth-child(even) 
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
  
       
<form id = "homeForm" action = "" method = "POST" >
       
<div class="container"><!--container for table -->
	<div class="panel panel-primary"><!-- panel start -->
  			<div class="panel-heading"><h5 class="panel-title">EQUIPMENT MODEL</h5></div>
  		 		<div class="panel-body"> <!-- panel body start -->
  					<!-- content start -->
  					<table class = "table" id = "EquipmentModelTable" width='99%'>
       							<thead>
       							 
       								<tr>
       									<th  colspan='4'>
       									 
       									 <div class='row'>
       									 <div class="col-sm-3"></div>
       									 	<div class="col-sm-1">Type</div>
       									 	<div class="col-sm-2">
	       									 	<select name='equipementtypeid' id='equipementtypeid'  class="form-control"   onChange ="loadEquipementModelStartwith('0')">
	       										<option value='0' > All</option>
	       											<c:forEach var="row" items="${equipmentType}"  varStatus="counter">
	     												<option value='${row.rowid}' >${row.name} </option>
	     											</c:forEach>
	       										</select>
       									 	</div>
       									 	<div class="col-sm-1">Make</div>
       									 	<div class="col-sm-2">
	       									 	<select name='equipementmakeid' id='equipementmakeid'  class="form-control" onChange ="loadEquipementModelStartwith('0')">
	       										<option value='0' > All</option>
	       											<c:forEach var="row" items="${equipmentMake}"  varStatus="counter">
	     												<option value='${row.rowid}' >${row.name} </option>
	     											</c:forEach>
	       										</select>
											</div>
       									 <div class="col-sm-3"></div>
       									 </div>
       									 
       									 
       									</th>
       								</tr>
       								<tr>
       									<th width='5%'>S.No.</th>
       									<th width='20%'>Type</th>
       									<th width='25%'>Make</th>
       									<th width='50%'>Model</th>
       								</tr>
       							</thead>
       							<tbody id = "tbody">
       								<c:forEach var="row" items="${result}"  varStatus="counter">
     									<tr>
								    		<td width='5%'><c:out value="${counter.count}"/>.&nbsp;<input type="checkbox"  name="rowid" class ="checkList" value= "${row.rowid}"></td>
      										<td width='20%'>${row.equipementtypename}</td>
      										<td width='25%'>${row.equipementmake}</td>
      										<td width='50%'>${row.name}</td>
      									</tr>
									</c:forEach>
       							</tbody>
       									
       						</table>
  					<!-- content end -->
  				
  				<div class="row">
  				 <div class="col-md-12 text-center" >
  				 		<ul class="pagination" >
						    <li><a href="#" onclick="loadEquipementModelStartwith('0')">All</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('A')">A</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('B')">B</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('C')">C</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('D')">D</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('E')">E</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('F')">F</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('G')">G</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('H')">H</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('I')">I</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('J')">J</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('K')">K</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('L')">L</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('M')">M</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('N')">N</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('O')">O</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('P')">P</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('Q')">Q</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('R')">R</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('S')">S</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('T')">T</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('U')">U</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('V')">V</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('W')">W</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('X')">X</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('Y')">Y</a></li>
						    <li><a href="#" onclick="loadEquipementModelStartwith('Z')">Z</a></li>
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
 
 
 <%@include file = "EquipmentModelModals.jsp" %>
 
  <script src = "resources/js/spare/EquipmentModelOperations.js"></script>     	

     	
 



 <script>
$(document).ready(function()
		{
     
    $('#EquipmentModelTable').dataTable({
    	"order": [[ 1, "asc" ]],	
    "sScrollY":"400px"}); 	
    });

</script>    	 	

     	
     	
     	
     		
     	
     	

     
     	
 <%@include file = "../footer.jsp" %>
    
       		
       		
       		
<div class="alert  fade in" id = "alertBox" style="display: none">
	    <strong id = "alert-title"></strong><span id = "alert-content">&nbsp;</span>
</div>
       		
     
       
</body>
</html>