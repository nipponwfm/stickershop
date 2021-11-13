<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin dashboard</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body id="admin">
	<%@include file="admin_header.jsp"%>
	<table>
		<thead>
			<tr>
				<td>Họ tên</td>
				<td>Ngày mua</td>
				<td>Số lượng mua</td>
				<td>Tên sách</td>
				<td>Số lượng</td>
				<td>Giá</td>
				<td>Tình trạng</td>
				<td colspan="2"></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sessionScope.order }" var="order">
				<tr ${order[7]==1?"class='order-confirmed'":""}>
					<td>${order[1]}</td>
					<td>${order[6]}</td>
					<td>${order[9]}</td>
					<td>${order[11]}</td>
					<td>${order[12]}</td>
					<td>${order[13]}</td>
					<td>${order[7]==1?"Đã giao":"Đang chờ"}</td>
					<td><button onclick="showDetail(event)">Chi tiết hóa đơn</button></td>
					<td><button onclick="handleConfirmBtn('${order[10]}')" ${order[7]==1?"disabled":""}>Xác nhận</button></td>
				</tr>
				<tr ${order[7]==1?"class='details order-confirmed'":"class='details'"}>
					<td colspan="9">
						<ol>
							<li>Mã hóa đơn: ${order[10]}</li>
							<li>Ngày mua: ${order[6]}</li>
							<li>Khách hàng: ${order[0]} - ${order[1]}</li>
							<li>Địa chỉ: ${order[2]}</li>
							<li>Số điện thoại: ${order[3]}</li>
							<li>Email: ${order[4]}</li>
							<li>Sách: ${order[8]} - ${order[11]}</li>
							<li>Loại: ${order[14]} - ${order[15]}</li>
							<li>Số lượng mua: ${order[9]}</li>
							<li>Tồn kho: ${order[12]}</li>
							<li>Giá: ${order[13]}</li>
							<li>Tổng tiền: ${order[9]*order[13]}</li>
						</ol>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		const showDetail = (event) => {
			const $ele = event.target.parentNode.parentNode.nextElementSibling
			document.querySelectorAll("tr:nth-child(even)").forEach((e) => {
				if(e==$ele) $ele.classList.toggle("active")
				else e.classList.remove("active")
			})
		}
		
		const handleConfirmBtn = (mahoadon) => {
			let r = confirm("Bạn có chắc chắn muốn xác nhận hóa đơn này?")
			if (r === true) {
				alert("Xác nhận thành công")
				location.href = "/Java/admin_home?mahoadon=" + mahoadon
			}
		}
	</script>
</body>
</html>