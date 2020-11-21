<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>JSTL c:catch Core Tag Example</title>
</head>
<body>
<%! 
int num1=10;
int num2=0; %>
<c:catch var ="errormsg">
  <% int res = num1/num2;
  out.println(res);%>
</c:catch>
<c:if test = "${errormsg != null}">
  <p>There has been an exception raised in the above
  arithmetic operation. Please fix the error.
  Exception is: ${errormsg}</p>
</c:if>
<c:out value="kalaiselvan"></c:out><br>
<c:out value="{5+6}"></c:out><br>
<c:set var="age" value="26"/>
<c:if test="${age >= 18}">
 <c:out value="You are eligible for voting!"/>
</c:if>
</body>
</html>