<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<c:forEach begin="1" end="31" step="1" var="loop">
    <option value='<c:out value="${loop}"/>'><c:out value="${loop}"/></option>
</c:forEach>