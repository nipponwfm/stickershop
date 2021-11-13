<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%out.print(request.getAttribute("tb")) ;%>
<c:out value="${tb }"></c:out>
cp ngan:
${tb }


Dung java:
<%  String[] ds=(String[])session.getAttribute("ht");
    for(String h:ds ){
    	out.print(h);
    }
%>

   <c:forEach items="${sessionScope.ht }" var="h">
       ${h}
   </c:forEach>       
   
   
<c:forEach items="${dsloai }" var="L">
        ${L.getMaLoai() } 
        ${L.getTenLoai() }
   </c:forEach>
</body>
</html>