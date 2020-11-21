 <!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>

<%
GregorianCalendar cal = new GregorianCalendar();
int endyear= cal.get(Calendar.YEAR);
%>


 <c:forEach begin="2010" end="<%=endyear+2%>" step="1" var="yr">
    <option value='<c:out value="${yr}"/>'><c:out value="${yr}"/></option>
</c:forEach>