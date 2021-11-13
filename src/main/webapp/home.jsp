<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div id="container-book">
		<div id="left">
			<p class="lead"> CHỦ ĐỀ SÁCH</p>
			<div class="list-group">
				<c:forEach items="${sessionScope.dsLoai}" var="l">
					<a class="list-group-item" href="/Java/home?query=${l.getMaLoai()}">${l.getTenLoai()}</a>
				</c:forEach>
			</div>
		</div>
		<div id="center">
			<c:forEach items="${sessionScope.dsSach}" var="s">
				<div class="book">
					<img src="${s.getUrl()}" />
					<p>${s.getTenSach()}</p>
					<p>${s.getGia()}đ</p>
					<p><a href="/Java/home?add=${s.getMaSach()}">
						<img id="cart-btn" src="image_sach/addtocart.JPG">
					</a></p>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>