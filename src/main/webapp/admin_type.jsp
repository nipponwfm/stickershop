<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book type manager</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body id="admin-type">
	<%@include file="admin_header.jsp"%>
	<div>
		<button data-toggle="modal" data-target="#myModal">Thêm loại</button>
	</div>
	<table>
		<thead>
			<tr>
				<td width="20%">Mã loại</td>
				<td width="50%">Tên loại</td>
				<td width="10%">Tổng</td>
				<td colspan="2"></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sessionScope._type }" var="order">
				<tr>
					<td>${order[0]}</td>
					<td><input type="text" value="${order[1]}" disabled></td>
					<td>${order[2]}</td>
					<td><button onclick="handleEditClick(event, '${order[0]}')">Sửa</button></td>
					<td><button onclick="handleDeleteClick('${order[0]}', '${order[1]}')">Xóa</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thêm loại</h4>
				</div>
				<div class="modal-body">
					<form action="/Java/admin_type" method="post">
						<table align="center">
							<tbody>
								<tr style="height: 40px">
									<td>Mã loại:</td>
									<td><input name="ml" type="text" required></td>
								</tr>
								<tr style="height: 40px">
									<td>Tên loại:</td>
									<td><input name="tl" type="text" required></td>
								</tr>
								<tr>
									<td colspan="2"><input type="Submit" value="Thêm" onclick="Thêm loại thành công"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		const handleEditClick = (event, ml) => {
			const $input = event.target.parentNode.parentNode.children[1].children[0]
			
			if (event.target.textContent == "Sửa") {
				event.target.textContent = "Cập nhật"
				$input.disabled = false
			} else {
				if ($input.value.length == 0) {
					alert("Không được để trống tên sách")
					return
				}
				event.target.textContent = "Sửa"
				$input.disabled = true
				
				alert("Sửa tên loại thành công")
				location.href = "/Java/admin_type?ml=" + ml + "&value=" + $input.value
			}
		}
	
		const handleDeleteClick = (ml, tl) => {
			let r = confirm("Bạn có chắc chắn muốn xóa loại: " + tl);
			if (r === true) {
				let w = confirm("Bạn có THỰC SỰ chắc chắn muốn xóa loại: " + tl);
				if (w === true) {
					alert("Xóa thành công!");
					location.href = "/Java/admin_type?mlbx=" + ml
				}
			}
		}
	</script>
</body>
</html>