<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
<hr>
<br>
 <sql:query var="rsfooter" dataSource="jdbc/spring">SELECT CHR_WEBSITE,CHR_NAME FROM m_company  WHERE INT_CPYID=1</sql:query>
 <c:forEach items="${rsfooter.rows}" var="currentItem">
 <p class="text-center">&copy; 2015 <a href='${currentItem.CHR_WEBSITE}' class = "active"> ${currentItem.CHR_NAME}</a> </p>
 
<!-- &nbsp;&nbsp;  All rights reserved. &nbsp;&nbsp;This site best viewed with Microsoft Internet Explorer 
or Mozilla Firefox @ 1024 x 768 resolution setting. </div>	-->  
 </c:forEach>	
 
 </div>
 </div>
