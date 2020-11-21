<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<title>fn:contains example</title>
</head>
<body>
<c:set var="oldPassword" value="HelloPass"/>
<c:set var="newPassword" value="HelloPassNew" />
<c:if test="${fn:contains(newPassword, oldPassword)}">
 <c:out value="New Password should not contain old password as substring"/>
</c:if>
</body>
</html>