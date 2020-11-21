<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
 
<div class = "container">
<script src="resources/js/spare/partcodeDescription.js"></script>
<script src="resources/js/spare/serialnumberinIssueSlip.js"></script>

<!-- Model For Add and Edit  --> 
<div class = "modal fade clear_form_elements" data-backdrop='static' id = "showAddEditModel" role = "dialog" >
	<div class = "modal-dialog">
		<div class = "modal-content">
            <form  class="form-horizontal"  autocomplete="off" id ="AddEditFormModel" action = "" method="post" name="frm"  onSubmit="return valid()">
                <div class = "modal-header">
				    <h4 class="modal-title">SPARE DEMAND
				    <input type='hidden' name='rowcount' id='rowcount' value='0' />
				    <input type='hidden' name='erowid' id='erowid' value='0' />
				    <input type='hidden' name='demandno' id='demandno' value='' />
				    </h4>
			     </div>
			     <div class = "modal-body">
			     
			     <div class='row'>
			     	<div class='col-md-12'>
			     		<span class = "star"><p class="text-right">* Mandatory</p></span>
			     	</div>	
			     </div>
			     
			       <div class='row'>
					 	<div class='col-md-5'>
					 		<div class='row'>
							 	<div class="form-group">
							    	<label for = "storageLocation" class = "control-label col-md-6">Storage Location<span class = "star"> *</span></label>
							    	<div class="col-md-6">
							      	<input class = "form-control uCaptilized" id = "storagelocation" placeholder = "Enter the storage location" name="storagelocation" maxlength='100' required value='TOS'>
							    	</div>
						 	 	</div>
						 	 	
						 	 	 
						 		 
						 		<div class="form-group">
						 	 		<label for = "customername" class = "control-label col-md-6">Customer Name<span class = "star"> *</span></label>
						    		<div class="col-md-6">
						      		<input   class = "form-control uCaptilized" id = "customername" placeholder = "Enter the customer name" name="customername" maxlength='100' required>
						    		</div>
						 		</div>
						 		
						 		<div class="form-group">
						 	 		<label for = "customername" class = "control-label col-md-6">Contact Name<span class = "star"> *</span></label>
						    		<div class="col-md-6">
						      		<input   class = "form-control uCaptilized" id = "contactname" placeholder = "Enter the contact name" name="contactname" maxlength='100' required>
						    		</div>
						 		</div>
						 		
						 		<div class="form-group">
						 	 		<label for = "contactnumber" class = "control-label col-md-6">Contact Number <span class = "star"> *</span></label>
						    		<div class="col-md-6">
						      		<input   class = "form-control uCaptilized" id = "contactnumber" placeholder = "Enter the contact number" name="contactnumber" maxlength='12' required onKeyPress="return numeric_only(event,'contactnumber','15')">
						    		</div>
						 		</div>
						 		
						 		<div class="form-group">
						 	 		<label for = "callnumber" class = "control-label col-md-6">Call Number <span class = "star"> *</span></label>
						    		<div class="col-md-6">
						      		<input maxlength='15'  class = "form-control " id = "callnumber" placeholder = "Enter the call number" name="callnumber"  required onkeypress="lengthEqualCheck('callnumber', '15', '15 digit required','errmsg')">
						    		<span id='errmsg'></span> 
						    		</div>
						 		</div>
						  </div>
						 </div>
					 
					 	<div class='col-md-6'>
						 	<div class='row'>
								 	<div class="form-group">
								    	<label for = "location" class = "control-label col-md-6">Location<span class = "star"> *</span></label>
								    	<div class="col-md-6">
								    	<input  type='hidden' name='sessionofficeid' id='sessionofficeid' value='${SPROFFICEID}' /> 
								      	<sql:query var="rsoffice" dataSource="jdbc/spring">SELECT INT_OFFICEID,CHR_OFFICENAME  FROM com_m_office ORDER BY CHR_OFFICENAME</sql:query>
											 <select  class = "form-control" id = "location" name="location"   required>
												<option value=''>Select Location</option>
											 	<c:forEach items="${rsoffice.rows}" var="currentItem">
											        		<option value="${currentItem.INT_OFFICEID}" >${currentItem.CHR_OFFICENAME}</option>
											    </c:forEach>	
					 						</select>
					 						 
								    	</div>
							 	 	</div>
							 	 	
							 	 	
							 	 	<div class="form-group">
						 	 		<label for = "machineserialno" class = "control-label col-md-6">Machine Serial Number <span class = "star"> *</span></label>
						    		<div class="col-md-6">
						      		<input   class = "form-control uCaptilized" id = "machineserialnumber" placeholder = "Enter the machine serial no" name="machineserialnumber" maxlength='30' required>
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
					 
                   
                      <div class="row"> 
                     	 <div class = "col-md-1"></div>
                     	 <div class = "col-md-10">
						<table width='100%' >
						<tr><td>                   	
 				       	<table id="mytable" border='1' class="display"  width='100%' >
					        <thead>
					            <tr>
					                <td align='center'>General Group</td>
					                <td align='center'>Part Code</td>
					                <td align='center'>Description</td>
					                <td align='center'>Defective Serial Number</td>
					                
					            </tr>
					        </thead>
					        <tfoot>
					           
					        </tfoot>
				    	</table>
				    	</td>
				    	</tr>
				    	<tr>
				    	<td><br><br>
				    	 <button type="button" class ="btn btn-success"  id = "anc_add" onclick="addTr()"><span class="glyphicon glyphicon-plus"></span>&nbsp;Add </button> &nbsp;&nbsp; <button type="button" class ="btn btn-success"  id = "anc_rem"> <span class="glyphicon glyphicon-trash"></span>&nbsp;Remove</button>
				    	</td>
				    	</tr>
				    	</table>
				    	 
				    <script>
				    
			    		var irow = 0;
			    		var isize=0;
			    	    var s="";
				    	var message = '<c:out value="${myVar}"/>';
				    	 function addTr()
				    	{
				    		try
				    		{
				    			 
				    			
				    			s = "<tr class='info'>";
				    			s = s + "<td><select  class='form-control' id='generalgroup"+irow+"'  name='generalgroup"+irow+"' required  onChange=\"loadpartCodedropdown(\'"+irow+"\')\"><option value=''>select general group</option>"; 
					    		s = s + $("#generalgroupids").val();
					    		s = s + " </select></td> ";
					    		
					    		s = s + "<td><select  class='form-control' id='partcode"+irow+"'  name='partcode"+irow+"' required  onChange=\"loadpartCodeDescription(\'"+irow+"\')\"><option value=''>select part code</option>"; 
					    		
					    		s = s + " </select></td> ";
					    		
					    		s = s + " <td><input type='text' name='idescription"+irow+"' id='idescription"+irow+"' class='form-control uCaptilized' placeholder ='Enter the Material description' maxlength='100' required /></td>"; 
			    				s = s + " <td><input type='text'   name='idefectiveserialnumber"+irow+"' id='idefectiveserialnumber"+irow+"' class='form-control uCaptilized ' placeholder ='Enter the defective number' maxlength='15' value=''    />  " ;
			    				s = s + " </td> ";
			    				s = s + " </tr>";
			    				//onkeyup onkeypress onkeydown onblur
		    					$('#mytable tr').last().after(s);
					    		irow++;
					    		document.getElementById('rowcount').value = $('#mytable tr').size()-1;
					    			
				    		}
				    		catch(err)
				    		{
				    			alert(err);
				    		}
				    	}
				    	
					    	 
				    	 
						   $("#anc_rem").click(function()
							    {
						   if($('#mytable tr').size()>1)
						   {
						    $('#mytable tr:last-child').remove();
						      document.getElementById('rowcount').value = $('#mytable tr').size()-1;
						    }
						   else
						   {
						    alert('no more delete in table');
						    }
						    });

						   
						   function removeTr()
					    	 {
					    		 try
					    		 {
					    			 
					    			 if($('#mytable tr').size()>1)
									   {
									    $('#mytable tr:last-child').remove();
									      document.getElementById('rowcount').value = $('#mytable tr').size()-1;
									    }
									   else
									   {
									    alert('no more delete in table');
									    }

					    		 }
					    		 catch(err)
					    		 {
					    			 alert(err);
					    		 }
					    	 }
					    	 
						   
				  
						   function lengthEqualCheck(ctr, len, msg,ctr1)
						   {
							   try
							   {
								   
								   var txtval = (document.getElementById(ctr).value);
									if (txtval.length != len)
									{
										
										document.getElementById(ctr).focus();
										$("#"+ctr1).html("<p class='text-danger'>"+msg+"</p>").show().fadeOut(200);
										//
									}
									else
									{
										$("#"+ctr1).html("");
									}
									
							   }
							   catch(err)
							   {
								   alert(err)
							   }
						   }

						    
				    
				    
				    
				    function isNumber(ctr) 
				    {
				        var myData = ctr.value;
				    	var decimalOnly = /^\s*-?[1-9]\d*(\.\d{1,2})?\s*$/;
				      	if(myData!=='')
				    	{
				       		if(decimalOnly.test(myData))
				    		{
				        		ctr.value= ctr.value; 
				       		}
				    		else
				    		{
				        		alert('Kindly check your data');
				    			ctr.value= "";
				    			ctr.focus();
				       		}
				      	}
				    	else
				    	{
				       		alert('Please enter data!');
				      	}
				      	return;
				         
				     }
				    </script>
				    </div>
				    <div class = "col-md-1"></div>
                    </div>
                    
                  
				    
                      
			     </div>
			     
			     
			     
			      
			     
			    <div class = "modal-footer">
				    <button class="btn btn-primary showAction" type="submit" id = "formButton" ><span class="glyphicon glyphicon-ok"></span>&nbsp; </button>
				    <a href = "#" type = "button" class = "btn btn-default madalClose" data-dismiss = "modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</a>
                    
			    </div>
            </form>    
                    
		</div>
	</div>
</div>


<script type="text/javascript">

function valid()
{
	try
	{
		var v = parseFloat(document.getElementById('rowcount').value);
		 
		if(v>0)
			return true;
		else
		{
			alert("Kindly check issue slip item details....");
			return false;
		}
		
	}
	catch(err)
	{
		alert(err)
	}
}
</script>




<!-- Modal for  delete  -->
<div id="" class="modal fade in conformDialog"  data-backdrop='static'>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close madalClose" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p id = "content">Are you really want to delete ?</p>
                <p ><small id="subContent">If you delete, you loss the selected records</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary proceedId" id = "">Proceed</button>
                <button type="button" class="btn btn-default madalClose" data-dismiss="modal" id = 'equTypeDeleteClose'>Cancel</button>
            </div>
        </div>
    </div>
</div>

 
 
 
  
  
  
 
  

</div>
