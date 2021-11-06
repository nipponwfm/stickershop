<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<%
	ArrayList<String> ls = (ArrayList<String>) request.getAttribute("ls");
	%>
	<table id="user-history">
		<thead>
			<tr>
				<td>Book name</td>
				<td>Author</td>
				<td>Price</td>
				<td>Time</td>
				<td>Status</td>
				<td>Amount</td>
				<td>Total</td>
			</tr>
		</thead>
		<tbody>
			<%
			for (String s : ls) {
				String[] ssplit = s.split(":");
				String tensach = ssplit[0];
				String tacgia = ssplit[1];
				String gia = ssplit[2];
				String ngaymua = ssplit[3];
				String damua = ssplit[4];
				String SoLuongMua = ssplit[5];
				String total = ssplit[6];
			%>
			<tr>
				<td><%=tensach %></td>
				<td><%=tacgia %></td>
				<td><%=gia %></td>
				<td><%=ngaymua %></td>
				<td><%=damua.equals("1")?"Success":"Pending" %></td>
				<td><%=SoLuongMua %></td>
				<td><%=total %></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>