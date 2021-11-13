<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book manager</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body id="admin_book">
	<%@include file="admin_header.jsp"%>
	<div>
		<button data-toggle="modal" data-target="#myModal"
			onclick="handleEditAddClick('add','null','null','null','null','null','null','null')">Thêm sách</button>
	</div>
	<table>
		<thead>
			<tr>
				<td width="20%">Tên sách</td>
				<td width="15%">Tác giả</td>
				<td width="10%">Loại</td>
				<td width="10%">Số lượng</td>
				<td width="10%">Giá</td>
				<td width="25%">URL</td>
				<td colspan="2"></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sessionScope.book }" var="order">
				<tr>
					<td>${order[0]}</td>
					<td>${order[6]}</td>
					<td>${order[4]}</td>
					<td>${order[1]}</td>
					<td>${order[2]}</td>
					<td>${order[5]}</td>
					<td><button data-toggle="modal" data-target="#myModal"
							onclick="handleEditAddClick('edit','${order[0]}', '${order[6]}', '${order[4]}', '${order[1]}', '${order[2]}', '${order[5]}', '${order[7]}')">Sửa</button></td>
					<td><button onclick="handleDeleteClick('${order[7]}')">Xóa</button></td>
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
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form action="/Java/admin_book" method="post">
						<table align="center">
							<tbody>
								<tr style="height: 40px">
									<td>Tên sách:</td>
									<td><input class="fill" name="name" type="text" required></td>
								</tr>
								<tr style="height: 40px">
									<td>Tác giả:</td>
									<td><input class="fill" name="author" type="text" required></td>
								</tr>
								<tr style="height: 40px">
									<td>Loại:</td>
									<td><select name="maloai">
											<c:forEach items="${sessionScope.type}" var="L">
												<option value="${L.getMaLoai()}">${L.getTenLoai()}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr style="height: 40px">
									<td>Số lượng:</td>
									<td><input class="fill" name="amount" type="number"
										required></td>
								</tr>
								<tr style="height: 40px">
									<td>Giá:</td>
									<td><input class="fill" name="price" type="number"
										required></td>
								</tr>
								<tr style="height: 40px">
									<td>URL:</td>
									<td><input class="fill" name="url" type="text" required></td>
								</tr>
								<tr style="display: none">
									<td><input class="fill" name="masach" type="text"></td>
									<td><input class="fill" name="typeclick" type="text"></td>
								</tr>
								<tr>
									<td colspan="2"><input type="Submit" onclick="alert('Thao tác thành công')"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		const handleEditAddClick = (typeclick, name, author, type, amount, price, url, masach) => {
			document.querySelector("h4.modal-title").textContent = typeclick=="edit"?"Sửa sách":"Thêm sách"
			document.querySelector(".modal-body input[type='submit']").value = typeclick=="edit"?"Sửa":"Thêm"	
			
			const value = [name, author, amount, price, url, masach, typeclick]
			
			document.querySelectorAll(".fill").forEach((e,idx) => {
				e.value = value[idx]!="null"?value[idx]:""
			})
			
			document.querySelectorAll("select option").forEach(e => {
				if (e.textContent == type) {
					e.selected = "selected"
					return
				}
			})
		}
		const handleDeleteClick = (masach) => {
			let r = confirm("Bạn có muốn xóa mã sách: " + masach);
			if (r === true) {
				alert("Đã xóa!")
				location.href = "/Java/admin_book?typeclick=delete&masach=" + masach			
			}
		}
	</script>
</body>
</html>