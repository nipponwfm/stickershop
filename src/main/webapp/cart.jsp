<%@page import="bean.Sach"%>
<%@page import="bo.SachBO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.GioHang"%>
<%@page import="bo.GioHangBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your cart</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<%
	GioHangBO ghbo = (GioHangBO) session.getAttribute("ghbo");
	ArrayList<GioHang> ds = ghbo.getGioHang();
	SachBO sbo = new SachBO();
	%>

	<div id="container-function">
		<button onclick="handleClickBtn('d_selected')">DELETE
			SELECTED</button>
		<button onclick="handleClickBtn('d_all')">DELETE ALL</button>
	</div>
	<div id="container-cart">
		<table>
			<thead>
				<tr>
					<td width="40%" colspan="2">Name</td>
					<td width="20%">Price</td>
					<td width="10%">Amount</td>
					<td width="15%">Total</td>
					<td width="15%" colspan="2"></td>
				</tr>
			</thead>
			<tbody>
				<%
				for (GioHang gh : ds) {
				%>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="ms"><%=gh.getMaSach()%></td>
					<td><%=gh.getTenSach()%></td>
					<td><%=gh.getGia()%></td>
					<td><input type="number" min="1" max="<%=sbo.TimSach(gh.getMaSach()).getSoLuong()%>"
						placeholder="<%=gh.getSoLuong()%>" onchange="handleInputchange(event)"></td>
					<td><%=gh.getThanhtien()%></td>
					<td><button onclick="handleClickBtn('u_amount')" disabled id="btn-update">UPDATE</button></td>
					<td><button onclick="handleClickBtn('d_row')">DELETE</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4"></td>
					<td><%=ghbo.Tong()%></td>
					<td colspan="2"><button style="width: 100%" onclick="handleToggleModalBox()" <%=ds.size()==0?"disabled":""%>>Purchase</button></td>
				</tr>
			</tfoot>
		</table>
	</div>
	<script>
		const handleClickBtn = (type) => {
			let msContainer = []
			let sl = 1
			switch(type) {
				case 'd_selected': {
					document.querySelectorAll("tbody tr").forEach(e => {
					    if (e.children[0].children[0].checked == true) msContainer.push(e.children[1].textContent)
					})
					break;
				}
				case 'd_row': {
					msContainer.push(event.target.parentNode.parentNode.children[1].textContent)
					break;
				}
				case 'd_all': {
					break;
				}
				case 'u_amount': {
					let $parent = event.target.parentNode.parentNode;
					msContainer.push($parent.children[1].textContent)
					
					let $input = $parent.children[4].children[0]
					
					sl = $input.value
					
					if (sl - $input.max > 0) {
						alert("Hiện tại sách trong kho không đủ so với lượng bạn yêu cầu: " + $input.max)
						return
					}
					
					break;
				}
				case 'purchase': {
					break;
				}
			}
			location.href = "/Java/cart?type=" + type + "&ms=" + msContainer + "&sl=" + sl
		}
		
		const handleToggleModalBox = () => {
			let r = confirm("Bạn chắn chắn muốn đặt đơn hàng này?");
			if (r === true) {
				if (document.querySelector("#user-name").textContent.includes('Login')) {
					alert("You need to login first!")
					document.querySelector("#user-name").click()
				} else {
					handleClickBtn('purchase')
				}
			}
		}
		
		const handleInputchange = (event) => {
			const $btn = document.getElementById("btn-update")
			if (event.target.value.length==0) $btn.disabled = true
			else $btn.disabled = false
		}
	</script>
</body>
</html>