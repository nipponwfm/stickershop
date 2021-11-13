<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User manager</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body id="admin-user">
	<%@include file="admin_header.jsp"%>
	<table>
		<thead>
			<tr>
				<td>Họ tên</td>
				<td>Địa chỉ</td>
				<td>Số điện thoại</td>
				<td>Tổng số tiền đã mua</td>
				<td>Số đơn hàng đã đặt</td>
				<td>Số đơn hàng đang xử lý</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sessionScope._user }" var="order">
				<tr>
					<td>${order[0]}</td>
					<td>${order[1]}</td>
					<td>${order[2]}</td>
					<td>${order[3]=='null'?0:order[3]}</td>
					<td>${order[4]}</td>
					<td>${order[5]}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>