<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>c:out Tag Example</title>
</head>
<body>
<c:out value="${'<b>This is a <c:out> example </b>'}"/>
<c:out value="${'kalaiselvan'}" />
<br>
<c:out value="${'<b>This is a <c:out> example </b>'}" escapeXml="false"/>
<br>
<%! String str = null; %>
<c:out value="${str}" default="default value of c:out"/>
</body>
</html>