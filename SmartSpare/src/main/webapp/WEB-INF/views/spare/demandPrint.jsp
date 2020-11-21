 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet" type="text/css" href="resources/js/jquery-ui.css">


<title>DEMAND PRINT</title>
 <style>
 .print {
    font-family: "Times New Roman";
    font-size: 12px;
}
 </style>
 
</head>
<body id = "homeBody">
 <table width="720" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td> 
	
	  		 		<sql:query var="rsdemand" dataSource="jdbc/spring">
					SELECT a.INT_DEMANDID id,a.CHR_DEMANDNO demandnos,a.CHR_STORAGELOCATION storagelocaton,a.INT_LOCATIONID locationid,b.CHR_OFFICENAME officename,
					a.CHR_CUSTOMERNAME customername,a.CHR_ADDRESS address,a.CHR_DOCUMENTTYPE documenttype,
					CHR_CONTACTPERSON contactname,CHR_CONTACTNO contactnumber,  CHR_CALLNUMBER callnumber,CHR_MACHINESERIALNUMBER machinenumber
					FROm spare_t_demand a , com_m_office b
					WHERE a.INT_LOCATIONID =b.INT_OFFICEID
					AND a.INT_DEMANDID = ?
					 
					<sql:param value="${param.demandrowid}" />
					</sql:query>
					<c:forEach var="row" items="${rsdemand.rowsByIndex}">
						 
						 
						 <table width="700" border="0" align="center" cellpadding="2" cellspacing="1" class='print'>
						  <tr>
						    <td colspan="5"><center><h3>SPARE DEMAND</h3></center></td>
						  </tr>
						  <tr>
						    <td>Demand No</td>
						    <td>${row["1"]}</td>
						    <td>&nbsp;</td>
						    <td>Storage Location</td>
						    <td>${row["2"]}</td>
						  </tr>
						  <tr>
						    <td>Customer</td>
						    <td>${row["5"]}</td>
						    <td>&nbsp;</td>
						    <td>Location</td>
						    <td>${row["4"]}</td>
						  </tr>
						  <tr>
						    <td>Contact name</td>
						    <td>${row["8"]}</td>
						    <td>&nbsp;</td>
						    <td>Document Type</td>
						    <td>${row["7"]}</td>
						  </tr>
						  <tr>
						    <td>Contact Number</td>
						    <td>${row["9"]}</td>
						    <td>&nbsp;</td>
						    <td>Machine Number</td>
						    <td>${row["11"]}</td>
						  </tr>
						   <tr>
						    <td>Call Number</td>
						    <td>${row["10"]}</td>
						    <td>&nbsp;</td>
						    <td>Address</td>
						    <td>${row["6"]}</td>
						  </tr>
						   <tr>
						    <td colspan='5'>
								
								<br>
								<table width="100%" border="1" align="center" cellpadding="1" cellspacing="2">
								  <tr>
								    <td align='center'><b>S.NO</td>
								    <td align='center'><b>Part Code</td>
								    <td align='center'><b>Description</td>
								    <td align='center'><b>Defective Serial Number</td>
								  </tr>
								    <sql:query var="rsdemanditem" dataSource="jdbc/spring">
											SELECT a.INT_ITEMROWID itemrowid,b.CHR_PARTNO itemcode,a.CHR_DESC dess,a.CHR_DEFECTIVESERIALNUMBER sonumber
											,a.INT_QTY qty,a.DOU_VALUE value,a.CHR_ISSUED issued, a.INT_PARTMASTERID partcodeid, a.INT_GENERALGROUPID generalgroupid
											FROM spare_t_demanditem a , spare_m_partmaster b
											WHERE a.INT_PARTMASTERID =b.INT_PARTMASTERID  
											AND a.CHR_DEMANDNO = ?
											<sql:param value="${row['1']}" />
										</sql:query>
									   <c:set var='itemrowcount' value='0'/>	
									   <c:forEach items="${rsdemanditem.rowsByIndex}" var="row"  varStatus="counter">
										 <tr >
								                <td >${counter.count}.</td>
								                <td >${row["1"]}
								                <input type='hidden' name='itemrowid${counter.count}' id='itemrowid${counter.count}' value='${row["0"]}'   />
								                </td>
								                <td >${row["2"]}</td>
								                <td >${row["3"]}</td>
								            </tr>
								      </c:forEach>
  								</table>
  								<br><br>
							</td>
						  </tr>
						</table>
  
  				</c:forEach>	   
  	
  		 </td>
  	</tr>
</table>



 
</body>
</html>