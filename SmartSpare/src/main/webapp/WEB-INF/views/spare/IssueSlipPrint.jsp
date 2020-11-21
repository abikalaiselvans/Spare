<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<c:catch var ="catchException">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet" type="text/css" href="resources/js/jquery-ui.css">
 
<title>DEMAND PRINT</title>
 
 
</head>
<body id = "homeBody" style="margin-top: 2px;">
 <table width="950" border="1" align="center" cellpadding="0" cellspacing="0"  id="tbl_display">
  <tr>
    <td>  
	 
	  		 		<sql:query var="rsdemand" dataSource="jdbc/spring">
					SELECT a.INT_DEMANDID id,a.CHR_DEMANDNO demandnos,a.CHR_STORAGELOCATION storagelocaton,a.INT_LOCATIONID locationid,b.CHR_OFFICENAME officename,
					a.CHR_CUSTOMERNAME customername,a.CHR_ADDRESS address,a.CHR_DOCUMENTTYPE documenttype,
					CHR_CONTACTPERSON contactname,CHR_CONTACTNO contactnumber,  CHR_CALLNUMBER callnumber,CHR_MACHINESERIALNUMBER machinenumber
					FROm spare_t_demand a , com_m_office b
					WHERE a.INT_LOCATIONID =b.INT_OFFICEID
					AND a.CHR_DEMANDNO = ?
					 
					<sql:param value="${demandrefno}" />
					</sql:query>
					<c:forEach var="row" items="${rsdemand.rowsByIndex}">
						 
						 
						 <table width="90%" border="0" align="center" cellpadding="2" style=" font-family:Times New Roman; font-size:16px;" >
						  <tr>
						    <td colspan="5" valign='middle' height='30'><center><h3>SPARE ISSUE SLIP </h3></center></td>
						  </tr>
						  
						  <tr>
						    <td width='15%' valign='top'><b>Demand No</b></td>
						    <td width='25%' valign='top'>${row["1"]}</td>
						    <td width='10%' valign='top'>&nbsp;</td>
						    <td width='20%' valign='top'><b>Date & Time</td>
						    <td width='30%' valign='top'><b>${issuetime}</td>
						  </tr>
						  <tr>
						    <td valign='top'><b>Customer</b></td>
						    <td valign='top'>${row["5"]}</td>
						    <td valign='top'>&nbsp;</td>
						    <td valign='top'><b>Storage Location</b></td>
						    <td valign='top'>${row["2"]}</td>
						  </tr>
						  <tr>
						    <td valign='top'><b>Contact name</b></td>
						    <td valign='top'>${row["8"]}</td>
						    <td valign='top'>&nbsp;</td>
						    <td valign='top'><b>Location</b></td>
						    <td valign='top'>${row["4"]}</td>
						  </tr>
						  <tr>
						    <td valign='top'><b>Contact Number</b></td>
						    <td valign='top'>${row["9"]}</td>
						    <td valign='top'>&nbsp;</td>
						    <td valign='top'><b>Document Type</b></td>
						    <td valign='top'>${row["7"]}</td>
						  </tr>
						  <tr>
						    <td valign='top'><b>Call Number</b></td>
						    <td valign='top'>${row["10"]}</td>
						    <td valign='top'>&nbsp;</td>
						    <td valign='top'><b>Machine Number</b></td>
						    <td valign='top'>${row["11"]}</td>
						  </tr>
						   <tr>
						    <td valign='top'><b>Address</b></td>
						    <td valign='top' colspan='4'>${row["6"]}<br></td>
						  </tr>
						   <tr>
						    <td colspan='5'>
								
								
								<table width="100%" border='1'  cellspacing='0' cellpadding="0"  align="center" style=" font-family:Times New Roman; font-size:14px;" >
								  <tr   height='25'>
								    <td align='center'><b>S.NO</b></td>
								    <td align='center'><b>Part Code</b></td>
								    <td align='center'><b>Description</b></td>
								    <td align='center'><b>Serial Number</b></td>
								    <td align='center'><b>Quantity</b></td>
								    <td align='center'><b>Value</b></td>
								  </tr>
								    <sql:query var="rsdemanditem" dataSource="jdbc/spring">
											SELECT a.INT_ITEMROWID itemrowid,b.CHR_PARTNO itemcode,a.CHR_DESC1 dess,a.CHR_SERIALNUMBER sonumber
											,a.INT_QTY qty,a.DOU_VALUE value,a.CHR_ISSUED issued, a.INT_PARTMASTERID partcodeid, a.INT_GENERALGROUPID generalgroupid
											FROM spare_t_demanditem a , spare_m_partmaster b
											WHERE a.INT_PARTMASTERID1 =b.INT_PARTMASTERID  AND a.CHR_ISSUED='Y'
											AND a.CHR_ISSUENO = ?
											<sql:param value="${issueno}" />
										</sql:query>
									   <c:set var='itemrowcount' value='0'/>	
									   <c:forEach items="${rsdemanditem.rowsByIndex}" var="row"  varStatus="counter">
										 <tr   height='35'>
								                <td  align='center'>${counter.count}.</td>
								                <td >&nbsp;&nbsp;${row["1"]}</td>
								                <td >&nbsp;&nbsp;${row["2"]}</td>
								                <td align='center'>${row["3"]}</td>
								                <td align='center' >${row["4"]}</td>
								                <td align='center'  >${row["5"]}</td>
								            </tr>
								      </c:forEach>
  								</table>
  								 
							</td>
						  </tr>
						 
  							
						  <tr>
						    <td colspan='5'>
						    	<table width='100%' border='0' cellspacing='3'   style=" font-family:Times New Roman; font-size:14px;">
						          <tr>
						            <td align='center' valign='top'><div><strong>Issued By</strong></div></td>
						            <td align='center' valign='top'><div><strong>Authorized By</strong></div></td>
						            <td align='center' valign='top'><div><strong>Received By</strong></div></td>
						          </tr>
						        </table><br>
						    </td>
						  </tr>
  							
						  
						   <tr>
						    <td colspan='5'>
						    <table width='100%' border='0' cellspacing='0' cellpadding='0' style="  font-family:Times New Roman; font-size:14px;">
					         <tr>
					            <td ><b>DECLARATION :&nbsp;&nbsp;&nbsp;TO WHOMSOEVER IT MAY CONCERN</b><br></td>
					          </tr>
					          <tr>
					            <td style=" text-align: justify; line-height: 1.5; " >This is certify that the items/goods mentioned here in above on this   delivery note are supplied to the Customer/ Wipro Service Partner        	as free replacement to repair of computers under   warranty/maintenance contract. The said supply is not for sale and hence        	has no commercial/sales value. Value declared is for statutory   purpose wherever applicable after replacement the defective spares are   to be returned to <b>CARE IT SOLUTION [ P ] LTD...</b></td>
					          </tr>
					        </table>
						    </td>
						  </tr>
						  
						  
						   <tr>
						    <td colspan='5'>
						    
						    <table width='98%' border='0' align='center' cellpadding='5' style=" font-family:Times New Roman; font-size:14px;">
					          <tr>
					            <td width='209' valign='middle' ><b>Acknowledgement By Customer</b> </td>
					            <td width='9'>:</td>
					            <td width='157'>&nbsp;</td>
					            <td width='104'  valign='middle' ><b>Signature</b></td>
					            <td width='10'>:</td>
					            <td width='156'>&nbsp;</td>
					          </tr>
					         
					          <tr>
					            <td  valign='middle' ><b>Name</b></td>
					            <td>:</td>
					            <td>&nbsp;</td>
					            <td  valign='middle' ><b>Time</b></td>
					            <td>:</td>
					            <td>&nbsp;</td>
					          </tr>
					          
					          <tr>
					            <td  valign='middle' ><b>Company Stamp</b> </td>
					            <td>:</td>
					            <td>&nbsp;</td>
					            <td  valign='middle' ><div><b>Contact Number</b> </div></td>
					            <td>:</td>
					            <td>&nbsp;</td>
					          </tr>
					        </table>
						    </td>
						  </tr>
						  
						  
						</table>
   						
   						
  
  
        
        
  				</c:forEach>	   
  	
  		 </td>
  	</tr>
</table>


<center><br><input type="button" value="Print" onclick="tablePrint();"></center>
<br><br><br><br>
<script language="javascript" type="text/javascript">
function tablePrint()
{
var display_setting="toolbar=yes,location=no,directories=yes,menubar=yes,";
display_setting+="scrollbars=yes,width=750, height=600, left=100, top=25";
var content_innerhtml = document.getElementById("tbl_display").innerHTML;
var document_print=window.open("","",display_setting);
document_print.document.open();
document_print.document.write('<html><head><title>SPARE</title></head>');
document_print.document.write('<body style="margin-top: 1.5px; font-family:Times New Roman; font-size:8px;" onLoad="self.print();self.close();" >');
document_print.document.write("<center><table width='950' border='1' align='center' cellpadding='0' cellspacing='0' >");
document_print.document.write("<tr><td>");
document_print.document.write(content_innerhtml);
document_print.document.write("</td></tr>");
document_print.document.write("</table></center>");
document_print.document.write('</body></html>');
document_print.print();
document_print.document.close();
return false;
}
</script>
  
</body>
</html>
 
</c:catch>

<c:if test = "${catchException != null}">
   <p>The exception is : ${catchException} <br />
   There is an exception: ${catchException.message}</p>
</c:if>