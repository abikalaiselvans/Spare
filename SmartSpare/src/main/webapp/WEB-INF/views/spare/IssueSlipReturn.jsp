 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file = "sparemenu.jsp" %>
<script src = "resources/js/jquery.js"></script>
<script src = "resources/js/jquery-ui.js"></script>
<script src="resources/js/spare/partcodeDescription.js"></script>
<script src="resources/js/commonfunctions.js"></script>
 
<link rel="stylesheet" type="text/css" href="resources/js/jquery-ui.css">


<title>ISSUE SLIP RETURN</title>
<style>
	#th{
	background-color:green;
	color:white;
	}
	
	.id_td{
		background-color: #778899;
		color:white;  
	}
	
	 
#alertBox 
{
	position: absolute;
	left: 425px;
	top: 100px;
	z-index : 6000;
	width : auto;

}
	
.container-flud
 {
 	width:95%;
 	margin:10px auto;
 }
 
 
</style>
</head>
<body id = "homeBody">
<div class='container-flud'>
<form  class="form-horizontal" autocomplete="off"  id ="AddEditFormModel" action = "IssueSlipeReturnUpdate" method="post" name="frm"  onSubmit="return valid()">
	<div class="panel panel-primary"> 
  			<div class="panel-heading"><h5 class="panel-title text-center">ISSUE SLIP RETURN</h5></div>
  		 		<div class="panel-body well"> 
  		 		
	  		 		<sql:query var="rsdemand" dataSource="jdbc/spring">
					SELECT 
					a.INT_DEMANDID id,a.CHR_DEMANDNO demandnos,a.CHR_STORAGELOCATION storagelocaton,a.INT_LOCATIONID locationid,
					c.CHR_OFFICENAME officename, a.CHR_CUSTOMERNAME customername,a.CHR_ADDRESS address,a.CHR_DOCUMENTTYPE documenttype,
					a.CHR_CONTACTPERSON contactname,a.CHR_CONTACTNO contactnumber,  a.CHR_CALLNUMBER callnumber,a.CHR_MACHINESERIALNUMBER machinenumber,
					b.CHR_ISSUENO issuenumber,DATE_FORMAT(DAT_ISSUEDDATE,'%d-%b-%Y %H:%i:%s %p') issuedate,b.CHR_RETURNSTATUS returnstatus,b.INT_ITEMROWID itemrowid
					 FROM spare_t_demand a, spare_t_demanditem b ,  com_m_office c
					 WHERE a.CHR_DEMANDNO =b.CHR_DEMANDNO  AND a.INT_LOCATIONID =c.INT_OFFICEID
					 AND b.INT_ITEMROWID = ?
					 
					<sql:param value="${param.issuesliprowid}" />
					</sql:query>
					<c:forEach var="row" items="${rsdemand.rowsByIndex}">
						 
						<div class='row'>
							<div class='col-md-6'>
								<div class='row'>
									<div class='col-md-6'><label>Demand No</label></div>
									<div class='col-md-6'>${row["1"]}</div>
									 
								</div>
								<div class='row'>
									<div class='col-md-6'><label>Customer</label></div>
									<div class='col-md-6'>${row["5"]}</div>
								</div>
								<div class='row'>
									<div class='col-md-6'><label>Contact name</label></div>
									<div class='col-md-6'>${row["8"]}</div>
								</div>
								
								<div class='row'>
									<div class='col-md-6'><label>Contact Number</label></div>
									<div class='col-md-6'>${row["9"]}</div>
								</div>
								
								<div class='row'>
									<div class='col-md-6'><label>Call Number</label></div>
									<div class='col-md-6'>${row["10"]}</div>
								</div>
								
								<div class='row'>
									<div class='col-md-6'><label>Issue slip number</label></div>
									<div class='col-md-6'>${row["12"]} <input type='hidden' name='issuenumber' id='issuenumber' value='${row["12"]}' /></div>
								</div>
								
								<div class='row'>
									<div class='col-md-6'><label>Issue Date & Time</label></div>
									<div class='col-md-6'>${row["13"]}</div>
								</div>
								
								
							</div>
							
							<div class='col-md-6'>
								<div class='row'>
									<div class='col-md-6'><label>Storage Location</label></div>
									<div class='col-md-6'>${row["2"]}</div>
								</div>
								<div class='row'>
									<div class='col-md-6'><label>Location</label></div>
									<div class='col-md-6'>${row["4"]}</div>
								</div>
								<div class='row'>
									<div class='col-md-6'><label>Document Type</label></div>
									<div class='col-md-6'>${row["7"]}</div>
								</div>
								<div class='row'>
									<div class='col-md-6'><label>Machine Number</label></div>
									<div class='col-md-6'>${row["11"]}</div>
								</div>
								<div class='row'>
									<div class='col-md-6'><label>Address</label></div>
									<div class='col-md-6'>${row["6"]}</div>
								</div>
							</div>
							</div>
							<div class='row'></div>
							<div class='row'>
								<div class='col-md-12'>
									<div class="table-responsive"><br><br>
								       	<table id="mytable" border='1' class="table table-bordered"  width='100%' >
								        <thead id="th">
								            <tr>
								                <td align='center' class='id_td' ><b>S.NO</b></td>
								                <td align='center' class='id_td' ><b>Issue Part Code</b></td>
								                <td align='center' class='id_td' ><b>Issue Description</b></td>
								                <td align='center' class='id_td' ><b>Defective Serialnumber</b></td>
								                <td align='center' class='id_td' ><b>Issue Serial Number</b></td>
								                <td align='center'><b>Return Partcode</b></td>
								                
								                
								                <%-- 
								                <td align='center'><b>Return Description</b></td>
								                <td align='center'><input type='text'name='idescription${counter.count}' id='idescription${counter.count}' required /> </td>
								                   onChange="loadpartCodeDescription('${counter.count}')"
								                    --%>
								                   
								                <td align='center'><b>Return Quantity</b></td>
								                <td align='center'><b>Return Serialnumber</b></td>
								                <td align='center'><b>Status</b></td>
								                <td align='center'><b>Return</b></td>
								            </tr>
									    </thead>
									    <sql:query var="rsdemanditem" dataSource="jdbc/spring">
											SELECT a.INT_ITEMROWID itemrowid,b.CHR_PARTNO itemcode,a.CHR_DESC1 dess,a.CHR_SERIALNUMBER sonumber
											,a.INT_QTY qty,a.DOU_VALUE value,a.CHR_ISSUED issued, a.INT_PARTMASTERID1 partcodeid, 
											a.INT_GENERALGROUPID generalgroupid, a.CHR_DEFECTIVESERIALNUMBER defective
											FROM spare_t_demanditem a , spare_m_partmaster b
											WHERE a.INT_PARTMASTERID1 =b.INT_PARTMASTERID  AND a.CHR_RETURNSTATUS='N'
											AND a.CHR_DEMANDNO = ? 		
											<sql:param value="${row['1']}" />
											
										</sql:query>
										<%-- 
										AND a.INT_ITEMROWID = ? <sql:param value="${param.issuesliprowid}" />
									 --%>	
										
									   <c:set var='itemrowcount' value='0'/>	
									   <c:forEach items="${rsdemanditem.rowsByIndex}" var="row"  varStatus="counter">
										 <tr >
								                <td class='id_td' >${counter.count}.</td>
								                <td class='id_td'>${row["1"]}
								                <input type='hidden' name='itemrowid${counter.count}' id='itemrowid${counter.count}' value='${row["0"]}'   />
								                </td>
								                <td class='id_td'>${row["2"]}</td>
								                <td class='id_td'><b>${row["9"]}</b></td>
								                <td class='id_td'><b>${row["3"]}</b>
								                <input type='hidden' name='iserialnumber${counter.count}' id='iserialnumber${counter.count}' value='${row["3"]}'   />
								                
								                </td>
								                <td>
								                	<sql:query var="rspartcode" dataSource="jdbc/spring">
								                	SELECT INT_PARTMASTERID id ,CHR_PARTNO code  FROM spare_m_partmaster WHERE INT_GENERALGROUPID  =?
								                	<sql:param value="${row['8']}" />
													</sql:query>
													<select name='partcode${counter.count}' id='partcode${counter.count}' >
								                		<option value=''>select Partcode</option>
								                		<c:forEach items="${rspartcode.rowsByIndex}" var="part">
								                			<option value="${part['0']}">${part['1']}</option>
								                		</c:forEach>
								                	</select>
								                </td>
								                
								                <td align='center'><input type='text'name='iqty${counter.count}' id='iqty${counter.count}'  value='${row["4"]}'   maxlength='12' onKeyPress="return numeric_only(event,'iqty${counter.count}','15')" required/> </td>
								                <td>
								                <input type='text' name='iserialnumber${counter.count}' id='iserialnumber${counter.count}' class='form-control uCaptilized' placeholder ='Enter the Serialnumber' maxlength='100' required/>
								            	</td>
								            	
								            	<td>
								            	<sql:query var="rsreturntype" dataSource="jdbc/spring">
								                	SELECT INT_RETURNTYPEID,CHR_RETURNTYPE FROM spare_m_returntype ORDER BY CHR_RETURNTYPE
								                </sql:query>
								            	<select name='returntatus${counter.count}' id='returntatus${counter.count}' required   >
								                		<option value=''>Select Status</option>
								                		<c:forEach items="${rsreturntype.rowsByIndex}" var="returntype">
								                			<option value="${returntype['0']}">${returntype['1']}</option>
								                		</c:forEach>
								                </select>
								            	</td>
								            	<td><input type='checkbox' name='return${counter.count}' id='return${counter.count}' value='Y'   /></td>
								               
								            </tr>
								            <c:set var='itemrowcount' value='${counter.count}'/>
										</c:forEach>
  
									   
									    <tfoot>
									    </tfoot>
								    	
								    	</table>
								    	 
								    	<input type='hidden' name='itemrowcount' id='itemrowcount' value="${itemrowcount}"   />
								    	 
								    </div>	
								</div>
								
							</div>
	
							<div class="col-md-offset-5">
								<button type='submit' id='submit' class="btn btn-primary btn-md"><span class="glyphicon glyphicon-ok"></span> Return</button>
								<a href='IssueHome'><div class="btn btn-danger btn-md"><span class="glyphicon glyphicon-remove"></span> Close</div></a>
							</div>
					
			  			     
			   		</c:forEach>	   
  	
  			</div>
 
  	</div>	 		

 			     
	
                   
</form>
</div>
<br>

       		
<div class="alert  fade in" id = "alertBox" style="display: none">
	    <strong id = "alert-title"></strong><span id = "alert-content">&nbsp;</span>
</div>
      
      
      
<footer>
	<%@include file = "../footer.jsp" %>
</footer>
<script>



function alertBox(title,content,bgClass)
{
	$('#alert-title').html(title+"!");
	$('#alert-content').html(" "+content);
	$('#alertBox').addClass(bgClass);
	$('#alertBox').show();
	$('#alertBox').delay(3000).fadeOut('slow');
	
}


var position="";
function searchSerialNumberIssueslip(idposition) 
{
	try
	{
		 
		var partcode = $("#partcode"+idposition).val();
		var serial = $("#iserialnumber"+idposition).val();  
		position = idposition;
		if(partcode == "") 
		{ 
			alert("Kindly select the part code..."); 
			$("#partcode"+idposition).focus();
			
		}
		else
		{	
			$('#iserialnumber'+position).autocomplete(
				{
					source: function( request,response ) 
					{
						$.ajax(
								{
								  	url:"loadSerialnumberIssueslip",
									type:"GET",
									data:{"partcode":partcode,"serial":serial},
									success:function(result)
									{
										SearchResult(result,response,'#iserialnumber'+position);
									} 
								});
					},
					
		            error: function (msg) 
		            {
		                   alert(msg.status + ' ' + msg.statusText);
		            },
		            
		            select: function(event, ui) 
					{
						 
		            	$("#ivalue"+position).val(parseFloat(ui.item.price)*2);
					},
					
		            change: function( event, ui ) 
		            {
			            if ( !ui.item ) {
			                  alert("Kindly select valid serial number");
			                  $('#iserialnumber'+position).val("");
			            }
			      	} 
		             
					
					});
		}
	}
	catch(err)
	{
		alert(err);
	}
}	




function SearchResult(result,response,componentId) 
{
	try
	{
	 
		var searchArr = [];
		var response_length = result.length;
		if(response_length != null && response_length > 0) 
		{
			$('#submit').show() ;
			for (var index = 0 ; index < response_length ; index++) 
			{
				searchArr.push({label: result[index].iserial , value : result[index].iserial, price : result[index].iprice });
			}
			 
		}
		else 
		{
			$('#submit').hide();
			alertBox("Sorry","Record Not found for your searched "+"<b>"+$(componentId).val()+" or "+$(componentId).val().toUpperCase()+"</b>","alert-danger");
			$(componentId).val("");
			
 			
		}
		response(searchArr);
	}
	catch(err)
	{
		alert(err);
	}

}


function valid() 
{
	try
	{

		return true;
		
	}
	catch(err)
	{
		alert(err);
		return false;
	}

}

function checkAtleastoneCheckbox()
{
	try
	{
		var len=$('#itemrowcount').val();
		var acount=0;
		for(var i=0;i<len;i++)
		 if(document.getElementById("issued"+(i+1)).checked)	  	
		 	acount=acount+1;
		
		 
		if((acount<=0) )
		{
			alert("Select Atleast One Partcode");
			return false;
		}	
		else
		{
			return true;
		}	  
	}
	catch(err)
	{
		alert(err);
		return false;
	}
}



function checkduplicateArray()
{
	try
	{
		var len=$('#itemrowcount').val();
		var s="";
		for(var i=0;i<len;i++)
			if(document.getElementById('issued'+(i+1)).checked)
				s = s+ document.getElementById("iserialnumber"+(i+1)).value+",";	  	
		
		var arrInput = s.split(",");  
		var sorted_arr = arrInput.sort();   
		var results = [];
		for (var i = 0; i < arrInput.length - 1; i++) 
		{  
		    if (sorted_arr[i + 1] == sorted_arr[i]) 
		    {  
		        results.push(sorted_arr[i]);  
		    }  
		} 
		
		
		if(results.length>0)
		{	
			s = "";
			for (var i = 0; i < results.length; i++)
				s= s+results[i]
			alert("Kindly check the serial numbers  " + s);
			return false;
		}
		else
			return true;
		
	}
	catch(err)
	{
		alert(err);
		return false;
	}
}


function serialCheck()
{
	try
	{
		var len=$('#itemrowcount').val();
		var s="";
		for(var i=0;i<len;i++)
			if(document.getElementById('issued'+(i+1)).checked)
				s = s+ "iserialnumber"+(i+1)+",";
		
		
		var v = s.split(",");
		if(v.length>0)
		{	
			for( i=0; i<v.length-1; i++)
			{
				var ck = "Enter Data..."+v[i];
				if(checkNull(v[i],ck))
				{
					d=1;
					 
				}
				else
				{
					return false;				
				}	
			}
			return true;
		}
		else
		{
			alert("Kin dly select the atleast one item");
			return false;	
		}
		
	}
	catch(err)
	{
		alert(err);
		return false;
	}
}
 

</script>  		
</body>
</html>