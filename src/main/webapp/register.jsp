<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<div id="container-register">
		<h2 style="text-align: center">ĐĂNG KÝ THÀNH VIÊN</h2>
		<table align="center">
			<tbody>
				<form action="/Java/register" method="post">
					<tr style="height: 40px">
						<td>Họ tên khách hàng:</td>
						<td><input id="HotenKH" name="HotenKH" type="text" value="${requestScope.HotenKH!=null?requestScope.HotenKH:''}"
							required maxlength="50" minlength="5"></td>
					</tr>
					<tr style="height: 40px">
						<td>Tên đăng nhập:</td>
						<td><input id="TenDN" name="TenDN" type="text" value="${requestScope.TenDN!=null?requestScope.TenDN:''}"
							required maxlength="20" minlength="3"></td>
					</tr>
					<tr style="height: 40px">
						<td>Mật khẩu:</td>
						<td><input id="Matkhau" name="Matkhau" type="password"
							required maxlength="20" minlength="3"></td>
					</tr>
					<tr style="height: 40px">
						<td>Mật khẩu nhập lại:</td>
						<td><input id="Matkhaunhatrlai" name="Matkhaunhatrlai"
							type="password" required maxlength="20" minlength="3"></td>
					</tr>
					<tr style="height: 40px">
						<td>Email:</td>
						<td><input id="Email" name="Email" type="email" value="${requestScope.Email!=null?requestScope.Email:''}"
							required maxlength="20" minlength="5"></td>
					</tr>
					<tr style="height: 40px">
						<td>Địa chỉ:</td>
						<td><input id="Diachi" name="Diachi" type="text" value="${requestScope.Diachi!=null?requestScope.Diachi:''}"
							required maxlength="20" minlength="5"></td>
					</tr>
					<tr style="height: 40px">
						<td>Điện thoại:</td>
						<td><input id="Dienthoai" name="Dienthoai" type="text" value="${requestScope.Dienthoai!=null?requestScope.Dienthoai:''}" 
							required maxlength="20" minlength="7"></td>
					</tr>
					<tr style="height: 40px">
						<td></td>
						<td><input type="Submit" value="Đăng ký"></td>
					</tr>
				</form>
			</tbody>
		</table>
		<div id="result-container">
			<%
			if (session.getAttribute("error") != null)
				out.println("<p id='error'>" + session.getAttribute("error") + "</p>");
			else if (session.getAttribute("success") != null)
				out.println("<p id='success'>Đăng kí tài khoản thành công</p>");
			%>
		</div>
	</div>
</body>
</html>