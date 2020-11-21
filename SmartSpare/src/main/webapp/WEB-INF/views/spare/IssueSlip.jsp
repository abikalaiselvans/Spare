<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<c:catch var="exception">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<title>ISSUE SLIP</title>
 
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="resources/js/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/js/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="resources/css/smartspare.css">


  
  
</head>
<body id = "homeBody">
<%@include file = "sparemenu.jsp" %>
<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/common/uniqueAutocompleteAjax.js" ></script> 
 
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
 	width:95%;
 	margin:10px auto;
 }
 
</style>

  
       
<form id = "homeForm" action = "" method = "POST" >

 
 	 
					                 
<div class="container-flud"><!--container for table -->
	<div class="panel panel-primary"><!-- panel start -->
  			<div class="panel-heading"><h5 class="panel-title">ISSUE SLIP RETURN & PRINT</h5></div>
  		 		<div class="panel-body"> <!-- panel body start -->
  					
  					
  					<!-- content start -->
  					<table class = "table" id = "contentTable" width='99%'>
       							<thead>
       							
       							<tr>
       							<td colspan='9'>
       							<div class='row'>
       								 
       								<div class="col-md-1">Status</div>
       								<div class="col-md-2">
       									<select id='status' name='status'  class="form-control"  onchange="loadIssueSlip('0')"  >
              								<option value='0'>All</option>
              								<option value='N'>Pending</option>
              								<option value='Y'>Return</option>
              								 
              							</select>
       								</div>
       								<div class="col-md-1">Day</div>
       								<div class="col-md-2">
       									<select id='day' name='day'  class="form-control"  onchange="loadIssueSlip('0')" >
              								<option value='0'>All</option>
              								<jsp:include page="/WEB-INF/views/common/oDay.jsp"></jsp:include>
                                		</select>
       								</div>
       								<div class="col-md-1">Month</div>
       								<div class="col-md-2">
       									<select id='month' name='month'  class="form-control" onchange="loadIssueSlip('0')">
                               				<option value='0'>All</option>
                               				<jsp:include page="/WEB-INF/views/common/oMonth.jsp"></jsp:include>
	                               		</select>
					            
       								</div>
       								<div class="col-md-1">Year</div>
       								<div class="col-md-2">
       								<select id='year' name='year'  class="form-control" onchange="loadIssueSlip('0')">
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
       							</td>
       							</tr>
       							
       							
       							 	<tr>
       									<th class='text-center'>S.No.</th>
       									<th class='text-center'>Issue.No</th>
       									<th class='text-center'>Customer </th>
       									<th class='text-center'>Call Number </th>
       									<th class='text-center'>Machine Number </th>
       									<th class='text-center'>Issue Date</th>
       									<th class='text-center'>Status</th>
       									<th class='text-center'>&nbsp;</th>
       								</tr>
       							</thead>
       							<tbody id = "tbody">
       								 <c:forEach var="row" items="${result}"  varStatus="counter">
     									<tr>
								        	<td><c:out value="${counter.count}"/></td>
								        	<td>${row.isueslipno} </td>
    										<td>${row.customer}</td>
    										<td>${row.callnumber}</td>
    										<td>${row.machinenumber}</td>
    										<td>${row.issuedate}</td>
    										<c:if test='${row.returnstatus eq "N"}'><td class='success'><b>Pending</b></td></c:if>
		   									<c:if test='${row.returnstatus eq "Y"}'><td class='danger'><b>Returned</b></td></c:if>
    											
    										<td>
    											<a target='_blank' href='isueslipnoPrint?id=${row.isueslipno}'><span class="glyphicon glyphicon-print"></span>&nbsp;Print</a> &nbsp;&nbsp; 
    										 	<c:if test='${row.returnstatus eq "N"}'>
    										 		<a target='_blank' href='IssueSlipReturn/${row.rowid}'><span class="glyphicon glyphicon-random"></span>&nbsp;Return</a> &nbsp;&nbsp;
    										 	</c:if>	
    										</td>	
    									</tr>
     								</c:forEach>
       							</tbody>
       									
       						</table>
  					<!-- content end -->
  				
  				
  			 
                                
  				<div class="row">
  				 <div class="col-md-12 text-center" >
  				 		<ul class="pagination" >
						    <li><a href="#" onclick="loadIssueSlip('0')">All</a></li>
						    <li><a href="#" onclick="loadIssueSlip('A')">A</a></li>
						    <li><a href="#" onclick="loadIssueSlip('B')">B</a></li>
						    <li><a href="#" onclick="loadIssueSlip('C')">C</a></li>
						    <li><a href="#" onclick="loadIssueSlip('D')">D</a></li>
						    <li><a href="#" onclick="loadIssueSlip('E')">E</a></li>
						    <li><a href="#" onclick="loadIssueSlip('F')">F</a></li>
						    <li><a href="#" onclick="loadIssueSlip('G')">G</a></li>
						    <li><a href="#" onclick="loadIssueSlip('H')">H</a></li>
						    <li><a href="#" onclick="loadIssueSlip('I')">I</a></li>
						    <li><a href="#" onclick="loadIssueSlip('J')">J</a></li>
						    <li><a href="#" onclick="loadIssueSlip('K')">K</a></li>
						    <li><a href="#" onclick="loadIssueSlip('L')">L</a></li>
						    <li><a href="#" onclick="loadIssueSlip('M')">M</a></li>
						    <li><a href="#" onclick="loadIssueSlip('N')">N</a></li>
						    <li><a href="#" onclick="loadIssueSlip('O')">O</a></li>
						    <li><a href="#" onclick="loadIssueSlip('P')">P</a></li>
						    <li><a href="#" onclick="loadIssueSlip('Q')">Q</a></li>
						    <li><a href="#" onclick="loadIssueSlip('R')">R</a></li>
						    <li><a href="#" onclick="loadIssueSlip('S')">S</a></li>
						    <li><a href="#" onclick="loadIssueSlip('T')">T</a></li>
						    <li><a href="#" onclick="loadIssueSlip('U')">U</a></li>
						    <li><a href="#" onclick="loadIssueSlip('V')">V</a></li>
						    <li><a href="#" onclick="loadIssueSlip('W')">W</a></li>
						    <li><a href="#" onclick="loadIssueSlip('X')">X</a></li>
						    <li><a href="#" onclick="loadIssueSlip('Y')">Y</a></li>
						    <li><a href="#" onclick="loadIssueSlip('Z')">Z</a></li>
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
		<button type="button" class="btn btn-warning" id = "Close"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</button>&nbsp;
		</div>
		 
	</div>		
</div>
 
 <br>
 <br>
 </form>  
 
  <script src = "resources/js/spare/IssueSlipOperations.js"></script>     	

     	
 



 <script>
$(document).ready(function()
		{
	 $('#contentTable').dataTable({
		 
	    	"order": [[ 1, "asc" ]],
	    	"info":     true,
	    	"paging":   true,
	        
	    	"sScrollY":"400px"}); 
    	
    });

</script>    	 	

     	
      	

     
     	
 <%@include file = "../footer.jsp" %>
    
       		
       		
       		
<div class="alert  fade in" id = "alertBox" style="display: none">
	    <strong id = "alert-title"></strong><span id = "alert-content">&nbsp;</span>
</div>
       		


<div id="" class="modal fade in conformDialog"  data-backdrop='static'>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close madalClose" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p id = "content"> </p>
                <p ><small id="subContent"> </small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary proceedId" id = "">Proceed</button>
                <button type="button" class="btn btn-default madalClose" data-dismiss="modal" id = 'equTypeDeleteClose'>Cancel</button>
            </div>
        </div>
    </div>
</div>
     
       
</body>
</html>

</c:catch>
<c:if test="${exception != null}">
	Exception thrown is <c:out value="${exception}"/> 
</c:if>