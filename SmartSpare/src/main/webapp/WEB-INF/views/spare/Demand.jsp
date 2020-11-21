<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<c:catch var="exception">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<title>DEMAND</title>
 
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="resources/js/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/js/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="resources/css/smartspare.css">


  
  
</head>
<body id = "homeBody" >
<%@include file = "sparemenu.jsp" %>
<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/common/uniqueAutocompleteAjax.js" ></script> 
<script src="resources/js/jquery.datetimepicker.js"></script>

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

.modal-dialog{width:90%;margin:30px auto}


#alertBox 
{
	position: absolute;
	left: 425px;
	top: 70px;
	z-index : 6000;
	width : 25%;

}

 .container-flud
 {
 	width:98%;
 	margin:10px auto;
 }
 
 
</style>

  
       
<form id = "homeForm" action = "" method = "POST" autocomplete="off" >

 
 <sql:query var="rsmodel" dataSource="jdbc/spring">SELECT INT_GENERALGROUPID,CHR_GENERALGROUP  FROM spare_m_generalgroup ORDER BY CHR_GENERALGROUP</sql:query>
 <c:forEach items="${rsmodel.rows}" var="currentItem">
  <c:set var="myVar" value='${myVar}<option value="${currentItem.INT_GENERALGROUPID}">${currentItem.CHR_GENERALGROUP}</option>' />
</c:forEach>	

<input type='hidden' name='generalgroupids' id='generalgroupids' value='<c:out value="${myVar}"/>' />
 
	 
					                 
<div class="container-flud" ><!--container for table -->
	<div class="panel panel-primary"><!-- panel start -->
  			<div class="panel-heading"><h5 class="panel-title">SPARE DEMAND </h5></div>
  		 		<div class="panel-body"> <!-- panel body start -->
  					
  					
  					<!-- content start -->
   					<table class = "table table-bordered" id = "contentTable"  >
       							<thead>
       							
       							<tr>
       							<th colspan='12'>
       							<div class='row'>
       								 
       								<div class="col-md-1">Status</div>
       								<div class="col-md-2">
       									<select id='status' name='status'  class="form-control"  onchange="loadDemand('0')" >
              								<option value='0'>All</option>
              								<option value='N'>Pending</option>
              								<option value='P'>Partially Issued</option>
              								<option value='Y'>Issued</option>
              							</select>
       								</div>
       								<div class="col-md-1">Day</div>
       								<div class="col-md-2">
       									<select id='day' name='day'  class="form-control"  onchange="loadDemand('0')" >
              								<option value='0'>All</option>
              								<jsp:include page="/WEB-INF/views/common/oDay.jsp"></jsp:include>
                                		</select>
       								</div>
       								<div class="col-md-1">Month</div>
       								<div class="col-md-2">
       									<select id='month' name='month'  class="form-control" onchange="loadDemand('0')">
                               				<option value='0'>All</option>
                               				<jsp:include page="/WEB-INF/views/common/oMonth.jsp"></jsp:include>
	                               		</select>
					            
       								</div>
       								<div class="col-md-1">Year</div>
       								<div class="col-md-2">
       								<select id='year' name='year'  class="form-control" onchange="loadDemand('0')">
                               				<option value='0'>All</option>
                               				<jsp:include page="/WEB-INF/views/common/oYear.jsp"></jsp:include>
	            					</select>
					            <script >
					            var d=new Date()
								var month1=d.getMonth() + 1
								if(month1<10) month1="0"+month1;
								var year1=d.getFullYear();
								var day=d.getDate();
								setOptionValue('day',day);	
								setOptionValue('month',month1);
								setOptionValue('year',year1);	
								</script>
       								</div>
      							</div>
       							</th>
       							</tr>
       							<tr>
  									<th class='text-center'>S.No.</th>
  									<th class='text-center'>Demand.No</th>
  									<th class='text-center'>Call number </th>
  									<th class='text-center'>Customer Name </th>
  									<th class='text-center'>Customer Address </th>
  									<th class='text-center'>Demand Raised Date</th>
  									<th class='text-center'>Cancel</th>
  									<th class='text-center'>Status</th>
  									<th class='text-center'>Entry By</th>
  									<th class='text-center'>&nbsp;</th>
  								</tr>
  								</thead>
       							<tbody id = "tbody">
       							 
       							 
       							 
       								 <c:forEach var="row" items="${result}"  varStatus="counter">
     									
     									<c:set var="can" value="${row.cancel}" />
     									<c:choose>
								        	<c:when test='${(can eq "Cancel")  ||  (row.status ne "N")}'>
								        		<tr  class='danger'>
								        		<td><c:out value="${counter.count}"/></td>
								        		</c:when>
								        	<c:otherwise>
								        		<tr>
								            	<td><c:out value="${counter.count}"/>.&nbsp;<input type="checkbox"  name="rowid" class ="checkList" value= "${row.rowid}"></td>
								        	</c:otherwise>
								    	</c:choose>
								    	 
								    	<td>${row.demandno} </td>
    									<td>${row.callnumber}</td>
    									<td>${row.customername}</td>
    									<td>${row.customeraddress}</td>
    									<td>${row.creationtime}</td>
    									<td>${row.cancel} </td>
    									
    									 
    									<c:choose>
								        	<c:when test='${row.status eq "Y"}'>
								        		<td class='text-success'><b>Issued</b></td>
								        	</c:when>
								        	<c:when test='${row.status eq "P"}'>
								        		<td class='text-success'><b>Partially Issued</b></td>
								        	</c:when>
								        	
								        	<c:otherwise>
								        		<td class='text-danger'><b>Pending</b></td>
								        	</c:otherwise>
								    	</c:choose>
    									 
    									<td>${row.createdby}</td>
    									<td>
    										<span class="badge">${row.demandage}-days ago</span>&nbsp;&nbsp; 
    										<a target='_blank' href='DemandPrint/${row.rowid}'><span class="glyphicon glyphicon-print"></span>&nbsp;Print</a> &nbsp;&nbsp; 
    										<c:if test='${can ne "Cancel"  && (row.status eq "N")}'>
											    <a href='DemandCancel/${row.rowid}'><span class="glyphicon glyphicon-trash"></span>&nbsp;Cancel</a> 
											</c:if>
											
										 
     									</td>	
    									</tr>
     									
									</c:forEach>
       							</tbody>
       							
       							<!-- <tfoot>
       								<tr>
       									<th>S.No.</th>
       									<th>Demand.No</th>
       									<th>Call number</th>
       									<th>Customer Name</th>
       									<th>Customer Address</th>
       									<th>Demand Raised Date</th>
       									<th>Cancel</th>
       									<th>Status</th>
       									<th>Entry By</th>
       								</tr>
								</tfoot>	 -->
								
       						</table>
       			 
       						
  					<!-- content end -->
  				
  				
  			 
                                
  				<div class="row">
  				 <div class="col-md-12 text-center" >
  				 		<ul class="pagination" >
						    <li><a href="#" onclick="loadDemand('0')">All</a></li>
						    <li><a href="#" onclick="loadDemand('A')">A</a></li>
						    <li><a href="#" onclick="loadDemand('B')">B</a></li>
						    <li><a href="#" onclick="loadDemand('C')">C</a></li>
						    <li><a href="#" onclick="loadDemand('D')">D</a></li>
						    <li><a href="#" onclick="loadDemand('E')">E</a></li>
						    <li><a href="#" onclick="loadDemand('F')">F</a></li>
						    <li><a href="#" onclick="loadDemand('G')">G</a></li>
						    <li><a href="#" onclick="loadDemand('H')">H</a></li>
						    <li><a href="#" onclick="loadDemand('I')">I</a></li>
						    <li><a href="#" onclick="loadDemand('J')">J</a></li>
						    <li><a href="#" onclick="loadDemand('K')">K</a></li>
						    <li><a href="#" onclick="loadDemand('L')">L</a></li>
						    <li><a href="#" onclick="loadDemand('M')">M</a></li>
						    <li><a href="#" onclick="loadDemand('N')">N</a></li>
						    <li><a href="#" onclick="loadDemand('O')">O</a></li>
						    <li><a href="#" onclick="loadDemand('P')">P</a></li>
						    <li><a href="#" onclick="loadDemand('Q')">Q</a></li>
						    <li><a href="#" onclick="loadDemand('R')">R</a></li>
						    <li><a href="#" onclick="loadDemand('S')">S</a></li>
						    <li><a href="#" onclick="loadDemand('T')">T</a></li>
						    <li><a href="#" onclick="loadDemand('U')">U</a></li>
						    <li><a href="#" onclick="loadDemand('V')">V</a></li>
						    <li><a href="#" onclick="loadDemand('W')">W</a></li>
						    <li><a href="#" onclick="loadDemand('X')">X</a></li>
						    <li><a href="#" onclick="loadDemand('Y')">Y</a></li>
						    <li><a href="#" onclick="loadDemand('Z')">Z</a></li>
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
 
 
 <%@include file = "DemandModals.jsp" %>
 
  
  <script src = "resources/js/spare/DemandOperations.js"></script>     	

     	
 



 <script>
$(document).ready(function()
		{
	 $('#contentTable').dataTable({
	    	"order": [[ 1, "asc" ]],
	    	"scrollX": true,	
	    "sScrollY":"400px"}); 
    	
    });
    
 

</script>    	 	

     	
     	
     	
     		
     	
     	

     
     	
 <%@include file = "../footer.jsp" %>
    
       		
       		
       		
<div class="alert  fade in" id = "alertBox" style="display: none">
	    <strong id = "alert-title"></strong><span id = "alert-content">&nbsp;</span>
	     
</div>
       		
     
       
</body>
</html>

</c:catch>
<c:if test="${exception != null}">
	Exception thrown is <c:out value="${exception}"/> 
</c:if>