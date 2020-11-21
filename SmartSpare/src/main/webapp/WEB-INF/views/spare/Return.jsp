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

.modal-dialog{width:60%;margin:30px auto}


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
  			<div class="panel-heading"><h5 class="panel-title">QC RECEIVED </h5></div>
  		 		<div class="panel-body"> <!-- panel body start -->
  					
  					
  					<!-- content start -->
  					<table class = "table" id = "contentTable" width='99%' border='0'>
       							<thead>
       							
       							<tr>
       							<td colspan='9'>
       							<div class='row'>
       								 
       								<div class="col-md-1">Status</div>
       								<div class="col-md-2">
       									<select id='status' name='status'  class="form-control"  onchange="loadReturn('0')"  >
              								<option value='0'>All</option>
              								<option value='N'>Pending</option>
              								<option value='Y'>Return</option>
              								 
              							</select>
       								</div>
       								<div class="col-md-1">Day</div>
       								<div class="col-md-2">
       									<select id='day' name='day'  class="form-control"  onchange="loadReturn('0')" >
              								<option value='0'>All</option>
              								<jsp:include page="/WEB-INF/views/common/oDay.jsp"></jsp:include>
                                		</select>
       								</div>
       								<div class="col-md-1">Month</div>
       								<div class="col-md-2">
       									<select id='month' name='month'  class="form-control" onchange="loadReturn('0')">
                               				<option value='0'>All</option>
                               				<jsp:include page="/WEB-INF/views/common/oMonth.jsp"></jsp:include>
	                               		</select>
					            
       								</div>
       								<div class="col-md-1">Year</div>
       								<div class="col-md-2">
       								<select id='year' name='year'  class="form-control" onchange="loadReturn('0')">
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
       									<th class='text-center'>Return No </th>
       									<th class='text-center'>Issue.No</th>
       									<th class='text-center'>Issue serial number </th>
       									<th class='text-center'>Return Serial Number </th>
       									<th class='text-center'>Return  Date</th>
       									<th class='text-center'>Received Status</th>
       									<th class='text-center'>&nbsp;</th>
       									<th class='text-center'>Quality Status</th>
       									<th class='text-center'>&nbsp;</th>
       								</tr>
       							</thead>
       							<tbody id = "tbody">
       								 <c:forEach var="row" items="${result}"  varStatus="counter">
     									<tr>
								        	<td><c:out value="${counter.count}"/></td>
								        	<td>${row.returnno} </td>
    										<td>${row.issuenumber}</td>
    										<td>${row.issueserialnumber}</td>
    										<td>
    											<c:if test='${row.serialnumber eq "null"}'><a href=''><span class='label label-danger'>Need SerialNumber</span></</a></c:if>
    											<c:if test='${row.serialnumber ne "null"}'>
    												<c:if test='${row.issueserialnumber ne row.serialnumber}'>
    													<span class='label label-danger'>${row.serialnumber}</span>
    												</c:if>	
    											</c:if>
    										</td>
    										<td>${row.returndate}  </td>
    										<c:if test='${row.receivedstatus eq "N"}'><td><span class='label label-danger'>Pending</span></td></c:if>
		   									<c:if test='${row.receivedstatus eq "Y"}'><td><span class='label label-success'>Received</span></td></c:if>
    											
    										<td>
    											<c:if test='${row.receivedstatus eq "N"}'>
    										 		<a  href='ReturnserialNumberreceived/${row.rowid}'><span class="glyphicon glyphicon-resize-vertical"></span>&nbsp;Receive</a> &nbsp;&nbsp;
    										 	</c:if>	
    										</td>	
    										
    										<td>&nbsp;</td>
    										<td>&nbsp;</td>
    									</tr>
     								</c:forEach>
       							</tbody>
       									
       						</table>
  					<!-- content end -->
  				
  				
  			 
                                
  				<div class="row">
  				 <div class="col-md-12 text-center" >
  				 		<ul class="pagination" >
						    <li><a href="#" onclick="loadReturn('0')">All</a></li>
						    <li><a href="#" onclick="loadReturn('A')">A</a></li>
						    <li><a href="#" onclick="loadReturn('B')">B</a></li>
						    <li><a href="#" onclick="loadReturn('C')">C</a></li>
						    <li><a href="#" onclick="loadReturn('D')">D</a></li>
						    <li><a href="#" onclick="loadReturn('E')">E</a></li>
						    <li><a href="#" onclick="loadReturn('F')">F</a></li>
						    <li><a href="#" onclick="loadReturn('G')">G</a></li>
						    <li><a href="#" onclick="loadReturn('H')">H</a></li>
						    <li><a href="#" onclick="loadReturn('I')">I</a></li>
						    <li><a href="#" onclick="loadReturn('J')">J</a></li>
						    <li><a href="#" onclick="loadReturn('K')">K</a></li>
						    <li><a href="#" onclick="loadReturn('L')">L</a></li>
						    <li><a href="#" onclick="loadReturn('M')">M</a></li>
						    <li><a href="#" onclick="loadReturn('N')">N</a></li>
						    <li><a href="#" onclick="loadReturn('O')">O</a></li>
						    <li><a href="#" onclick="loadReturn('P')">P</a></li>
						    <li><a href="#" onclick="loadReturn('Q')">Q</a></li>
						    <li><a href="#" onclick="loadReturn('R')">R</a></li>
						    <li><a href="#" onclick="loadReturn('S')">S</a></li>
						    <li><a href="#" onclick="loadReturn('T')">T</a></li>
						    <li><a href="#" onclick="loadReturn('U')">U</a></li>
						    <li><a href="#" onclick="loadReturn('V')">V</a></li>
						    <li><a href="#" onclick="loadReturn('W')">W</a></li>
						    <li><a href="#" onclick="loadReturn('X')">X</a></li>
						    <li><a href="#" onclick="loadReturn('Y')">Y</a></li>
						    <li><a href="#" onclick="loadReturn('Z')">Z</a></li>
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
		<button type="button" class="btn btn-warning" id = "BulkReceived"><span class="glyphicon glyphicon-plus"></span>&nbsp;Bulk Received</button>&nbsp;
		<button type="button" class="btn btn-warning" id = "Close"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</button>&nbsp;
		</div>
		 
	</div>		
</div>
 
 <br>
 <br>
 </form>  
 
 


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
    
       		
     
     
     
     
     
     
     
     
     
     
     
     
     
<div class = "modal fade clear_form_elements" data-backdrop='static' id = "showAddEditModel" role = "dialog" >
	<div class = "modal-dialog">
		<div class = "modal-content">
            <form  class="form-horizontal"  autocomplete="off" id ="AddEditFormModel" action = "" method="post" name="frm"  onSubmit="return valid()">
                <div class = "modal-header">
				    <h4 class="modal-title">RETURN SERIAL NUMBER RECEIVED</h4>
			     </div>
			     <div class = "modal-body">
			     
			     <div class='row'>
			     	<div class='col-md-12'>
			     		<p class="text-right"><span class = "star">* Mandatory</span></p>
			     	</div>	
			     </div>
			     
			       <div class="form-group">
					 	 <div class='row'>
					 	 	<div class="col-md-1"></div>
					 	 	<div class="col-md-4">
					 	 		<label for = "storageLocation" class = "control-label col-md-6">Serial NUmber<span class = "star"> *</span></label>
					 	 	</div>
					 	 	
					 	 	<div class="col-md-4">
					 	 		<textarea class="form-control  uCaptilized" rows="5"  cols="50" maxlength="5000" id = "serialnumber" name = "serialnumber"  placeholder = "Enter the Serial Number" required ></textarea>
					 	 	</div>
					 	 	<div class="col-md-1"></div>
							 	
	 			  		 </div>
	 			  	</div>
			     
			    <div class = "modal-footer">
				    <button class="btn btn-primary showAction" type="submit" id = "formButton" ><span class="glyphicon glyphicon-ok"></span>&nbsp; </button>
				    <a href = "#" type = "button" class = "btn btn-default madalClose" data-dismiss = "modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</a>
 			    </div>
 			    </div>
            </form>    
                    
		</div>
	</div>
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
     
    
           		
<div class="alert  fade in" id = "alertBox" style="display: none">
	    <strong id = "alert-title"></strong><span id = "alert-content">&nbsp;</span>
</div>
       		
 <script src = "resources/js/spare/ReturnOperations.js"></script>     	

     	
    
       
</body>
</html>


 


</c:catch>
<c:if test="${exception != null}">
	Exception thrown is <c:out value="${exception}"/> 
</c:if>