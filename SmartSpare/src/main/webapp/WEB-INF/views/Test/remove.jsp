<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Example of c:remove tag</title>
</head>
<body>
<c:set var="Site" scope="session" value="BeginnersBook.com"/>
<c:set var="author" scope="session" value="Chaitanya"/>
<c:remove var="author"/>
<a href="Test1?id=6"">check attributes</a>
</body>
</html>