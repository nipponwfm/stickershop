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
					<td colspan="2">Name</td>
					<td>Price</td>
					<td colspan="2">Amount</td>
					<td></td>
					<td></td>
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
					<td><input type="number" min="1"
						placeholder="<%=gh.getSoLuong()%>"></td>
					<td><%=gh.getThanhtien()%></td>
					<td><button onclick="handleClickBtn('u_amount')">UPDATE</button></td>
					<td><button onclick="handleClickBtn('d_row')">DELETE</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5" style="text-align: right">TOTAL</td>
					<td><%=ghbo.Tong()%></td>
					<td colspan="2"><button onclick="handleToggleModalBox('block')" <%=ds.size()==0?"disabled":""%>>Purchase</button></td>
				</tr>
			</tfoot>
		</table>
		<div id="modal-box" style="display: none">
			<button onclick="handleClickBtn('purchase')">Yes</button>
			<button onclick="handleToggleModalBox('none')">No</button>
		</div>
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
					sl = $parent.children[4].children[0].value
					break;
				}
				case 'purchase': {
					break;
				}
			}
			location.href = "/Java/cart?type=" + type + "&ms=" + msContainer + "&sl=" + sl
		}
		
		const handleToggleModalBox = (value) => {
			if (document.querySelector("#user-name").textContent === 'Login') {
				alert("You need to login first!")
			} else document.querySelector("#modal-box").style.display = value;
		}
	</script>
</body>
</html>