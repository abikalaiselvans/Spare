<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title> JSTL c:import Tag Example</title>
</head>
<body>
<c:import var="mydata" url="/Test1?id=9"/>
<c:out value="${mydata}"/>
<br>
<br>
<c:url value="/Test1?id=9" />
<c:redirect url="/Test1?id=5" ></c:redirect>
</body>
</html>