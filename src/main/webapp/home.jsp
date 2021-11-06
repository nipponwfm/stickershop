<%@page import="bean.Loai"%>
<%@page import="bean.Sach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.SachBO"%>
<%@page import="bo.LoaiBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<%
	ArrayList<Sach> dsSach = (ArrayList<Sach>) request.getAttribute("dsSach");
	ArrayList<Loai> dsLoai = (ArrayList<Loai>) request.getAttribute("dsLoai");
	%>
	<div id="container-book">
		<div id="left"></div>
		<div id="center">
			<%
			for (Sach s : dsSach) {
			%>
			<div class="book">
				<img src="<%=s.getUrl()%>" />
				<p><%=s.getTenSach()%></p>
				<p><%=s.getTacGia()%></p>
				<p><%=s.getGia()%></p>
				<a href="/Java/home?add=<%=s.getMaSach()%>">ADD</a>
			</div>
			<%}%>
		</div>
		<div id="right"></div>
	</div>
</body>
</html>