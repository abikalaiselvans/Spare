  <!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>


 <form class="form-horizontal" >
 
 <div class='row'>

 	<div class='col-md-5'>
 		<div class='row'>
		 	<div class="form-group">
		    	<label for = "storageLocation" class = "control-label col-md-6">Storage Location<span class = "star"> *</span></label>
		    	<div class="col-md-6">
		      	<input class = "form-control uCaptilized" id = "storagelocation" placeholder = "Enter the storage location" name="storagelocation" maxlength='100' required>
		    	</div>
	 	 	</div>
	 	 	
	 	 	<div class="form-group">
	 	 		<label for = "serviceordernumber" class = "control-label col-md-6">Service order number<span class = "star"> *</span></label>
	    		<div class="col-md-6">
	      		<input class = "form-control uCaptilized" id = "serviceordernumber" placeholder = "Enter the serviceordernumbern" name="serviceordernumber" maxlength='20' required>
	    		</div>
	 		</div>
	 		
	 		<div class="form-group">
	 	 		<label for = "fordocumentdate" class = "control-label col-md-6">Document date<span class = "star"> *</span></label>
	    		<div class="col-md-6">
	      		 <input type='text' class = "form-control uCaptilized" id = "documentdate" placeholder = "Enter the document date" name="documentdate" maxlength='100' required>
                                <script>
                                
                               /*
                                $(function() {
                                    $( "#documentdate" ).datepicker({ minDate: "-2D", maxDate: "+1D " });
                                  }); 
                                */
                                
                                $('#documentdate').datetimepicker({
                                	dayOfWeekStart : 1,
                                	lang:'en',
                                	 
                                	});

                                </script>
	    		</div>
	 		</div>
	 		
	 		<div class="form-group">
	 	 		<label for = "customername" class = "control-label col-md-6">Customer Name<span class = "star"> *</span></label>
	    		<div class="col-md-6">
	      		<input class = "form-control uCaptilized" id = "customername" placeholder = "Enter the customer name" name="customername" maxlength='100' required>
	    		</div>
	 		</div>
	  </div>
	 </div>
 
 	<div class='col-md-6'>
	 	<div class='row'>
			 	<div class="form-group">
			    	<label for = "location" class = "control-label col-md-6">Location<span class = "star"> *</span></label>
			    	<div class="col-md-6">
			      	<sql:query var="rsoffice" dataSource="jdbc/spring">SELECT INT_OFFICEID,CHR_OFFICENAME  FROM com_m_office ORDER BY CHR_OFFICENAME</sql:query>
						 <select  class = "form-control" id = "location" placeholder = "Enter the location" name="location"   required>
							<option value=''>Select Location</option>						 
						 	<c:forEach items="${rsoffice.rows}" var="currentItem">
						  		<option value="${currentItem.INT_OFFICEID}">${currentItem.CHR_OFFICENAME}</option>
						  	</c:forEach>	
 						</select>
			    	</div>
		 	 	</div>
		 	 	
		 	 	<div class="form-group">
		 	 		<label for = "documenttype" class = "control-label col-md-6">Document Type<span class = "star"> *</span></label>
		    		<div class="col-md-6">
		      		<select class = "form-control uCaptilized" id = "documenttype"   name="documenttype" required >
                        <option value=''>Select the document Type</option>
                        <option value='AMC'>AMC</option>
                        <option value='Warranty'>Warranty</option>
                        <option value='Sales'>Sales</option>
                        <option value='Others'>Others</option>
                        </select>
		    		</div>
		 		</div>
		 		
		 		<div class="form-group">
			    	<label for = "customeraddress" class = "control-label col-md-6">Customer Address<span class = "star"> *</span></label>
			    	<div class="col-md-6">
			      	<textarea class="form-control  uCaptilized" rows="5"  cols="50" maxlength="500" id = "customeraddress" name = "customeraddress"  placeholder = "Enter the customer address" required ></textarea>
			    	</div>
		 	 	</div>
		  </div>
 	</div>
 
 </div>
 
  
   
</form>