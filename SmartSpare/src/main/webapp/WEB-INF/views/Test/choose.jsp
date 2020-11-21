<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>c:choose, c:when and c:otherwise Tag Example</title>
</head>
<body>
<c:set var="number1" value="${222}"/>
<c:set var="number2" value="${12}"/>
<c:set var="number3" value="${10}"/>
<c:choose>
 <c:when test="${number1 < number2}">
     ${"number1 is less than number2"}
 </c:when>
 <c:when test="${number1 <= number3}">
     ${"number1 is less than equal to number2"}
 </c:when>
 <c:otherwise>
     ${"number1 is largest number!"}
 </c:otherwise>
</c:choose>
</body>
</html>