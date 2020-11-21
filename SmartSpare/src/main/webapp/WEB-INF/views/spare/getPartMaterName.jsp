<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<sql:query var="rstechnicalgroup" dataSource="jdbc/spring">SELECT CHR_PARTNO  from spare_m_partmaster ORDER BY CHR_PARTNO</sql:query>
<c:forEach var="data" items="${rstechnicalgroup.rows}">
 <c:out value="${data.CHR_PARTNO}" />
</c:forEach>				                                              