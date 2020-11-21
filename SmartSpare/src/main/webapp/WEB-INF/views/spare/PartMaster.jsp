<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<title>Part Master</title>
 
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/css/smartspare.css">
<style>
#contentTable > #tbody>tr:nth-child(odd) 
{
   	background-color : #bdf4f4;
   	color:#000000;
	 
}

#contentTableTable > #tbody>tr:nth-child(even) 
{
  	background-color : #f7f7f7;
   	color:#000000;
	 
}
  .container-flud
 {
 	width:98%;
 	margin:10px auto;
 }
</style>

</head>
<body id = "homeBody">
<%@include file = "sparemenu.jsp" %>

<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/common/uniqueAutocompleteAjax.js" ></script>
  
 
       
<form id = "homeForm" action = "" method = "POST" autocomplete="on">
       
<div class="container-flud"><!--container for table -->
	<div class="panel panel-primary"><!-- panel start -->
  			<div class="panel-heading"><h5 class="panel-title">PART MASTER</h5></div>
  		 		<div class="panel-body"> <!-- panel body start -->
  					<!-- content start -->
  					<table class = "table" id = "contentTable" width='99%'>
       							<thead>
       							
       							<tr>
       							<td colspan='7'>
       							<div class='row'>
       								 
       								<div class="col-md-1">Model</div>
       								<div class="col-md-2">
       								<sql:query var="rsmodel" dataSource="jdbc/spring">SELECT INT_INT_EQUIPEMENTMODELID,CHR_MODEL FROM spare_m_equipement_model ORDER BY CHR_MODEL</sql:query>
    
                                <select id='smodelid' name='modelid' required class="form-control"    onChange ="loadPartMaster('0')">
                                <option value='0'>All</option>
                                <c:forEach var="data" items="${rsmodel.rows}">
					                <option value='<c:out value="${data.INT_INT_EQUIPEMENTMODELID}" />'><c:out value="${data.CHR_MODEL}" /></option>
					            </c:forEach>
					            
                                </select>
       								</div>
       								<div class="col-md-2">Generic Group</div>
       								<div class="col-md-2">
       								<sql:query var="rsgenericgroup" dataSource="jdbc/spring">SELECT INT_GENERALGROUPID,CHR_GENERALGROUP FROM spare_m_generalgroup ORDER BY CHR_GENERALGROUP</sql:query>
    
                                <select id='sgeneralgroupid' name='generalgroupid' required class="form-control"    onChange ="loadPartMaster('0')">
                                <option value='0'>All</option>
                                <c:forEach var="data" items="${rsgenericgroup.rows}">
					                <option value='<c:out value="${data.INT_GENERALGROUPID}" />'><c:out value="${data.CHR_GENERALGROUP}" /></option>
					            </c:forEach>
					            </select>
					            
       								</div>
       								<div class="col-md-2">Technical Group</div>
       								<div class="col-md-2">
       								
       								<sql:query var="rstechnicalgroup" dataSource="jdbc/spring">SELECT INT_TECHNICALROUPID, CHR_TECHNICALGROUP  from v_spare_m_technicalgroup ORDER BY CHR_TECHNICALGROUP</sql:query>
                                	<select id='stechnicalgroupid' name='technicalgroupid' required class="form-control"    onChange ="loadPartMaster('0')">
                                		<option value='0'>All</option>
                                		<c:forEach var="data" items="${rstechnicalgroup.rows}">
					                		<option value='<c:out value="${data.INT_TECHNICALROUPID}" />'><c:out value="${data.CHR_TECHNICALGROUP}" /></option>
					                    </c:forEach>
					                </select>
       								</div>
      							</div>
       							</td>
       							</tr>
       							
       							 	<tr>
       									<th>S.No.</th>
       									<th>Part Number</th>
       									<th>OEM Part Number</th>
       									<th>Description</th>
       									<th>Technical Description</th>
       									<th>Model</th>
       									<th>General Group</th>
       									 
       								</tr>
       							</thead>
       							<tbody id = "tbody">
       								<c:forEach var="row" items="${result}"  varStatus="counter">
     									<tr>
								    		<td><c:out value="${counter.count}"/>.&nbsp;<input type="checkbox"  name="rowid" class ="checkList" value= "${row.rowid}"></td>
      										<td>${row.name}</td>
      										<td>${row.oemname}</td>
      										<td>${row.description}</td>
      										<td>${row.technicaldescription}</td>
      										<td>${row.modelname}</td>
      										<td>${row.generalgroupname}</td>
      										
      									</tr>
									</c:forEach>
       							</tbody>
       									
       						</table>
  					<!-- content end -->
  				
  				<div class="row">
  				 <div class="col-md-12 text-center" >
  				 		<ul class="pagination" >
						    <li><a href="#" onclick="loadPartMaster('0')">All</a></li>
						    <li><a href="#" onclick="loadPartMaster('A')">A</a></li>
						    <li><a href="#" onclick="loadPartMaster('B')">B</a></li>
						    <li><a href="#" onclick="loadPartMaster('C')">C</a></li>
						    <li><a href="#" onclick="loadPartMaster('D')">D</a></li>
						    <li><a href="#" onclick="loadPartMaster('E')">E</a></li>
						    <li><a href="#" onclick="loadPartMaster('F')">F</a></li>
						    <li><a href="#" onclick="loadPartMaster('G')">G</a></li>
						    <li><a href="#" onclick="loadPartMaster('H')">H</a></li>
						    <li><a href="#" onclick="loadPartMaster('I')">I</a></li>
						    <li><a href="#" onclick="loadPartMaster('J')">J</a></li>
						    <li><a href="#" onclick="loadPartMaster('K')">K</a></li>
						    <li><a href="#" onclick="loadPartMaster('L')">L</a></li>
						    <li><a href="#" onclick="loadPartMaster('M')">M</a></li>
						    <li><a href="#" onclick="loadPartMaster('N')">N</a></li>
						    <li><a href="#" onclick="loadPartMaster('O')">O</a></li>
						    <li><a href="#" onclick="loadPartMaster('P')">P</a></li>
						    <li><a href="#" onclick="loadPartMaster('Q')">Q</a></li>
						    <li><a href="#" onclick="loadPartMaster('R')">R</a></li>
						    <li><a href="#" onclick="loadPartMaster('S')">S</a></li>
						    <li><a href="#" onclick="loadPartMaster('T')">T</a></li>
						    <li><a href="#" onclick="loadPartMaster('U')">U</a></li>
						    <li><a href="#" onclick="loadPartMaster('V')">V</a></li>
						    <li><a href="#" onclick="loadPartMaster('W')">W</a></li>
						    <li><a href="#" onclick="loadPartMaster('X')">X</a></li>
						    <li><a href="#" onclick="loadPartMaster('Y')">Y</a></li>
						    <li><a href="#" onclick="loadPartMaster('Z')">Z</a></li>
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
 
 
 <%@include file = "PartMasterModals.jsp" %>
 
  <script src = "resources/js/spare/PartMasterOperations.js"></script>     	

     	
 



 <script>
$(document).ready(function()
		{
	 $('#contentTable').dataTable({
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