<!-- NAVBAR -->
<%@page import="bean.KhachHang"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/Java/home">Book Store</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/Java/cart">Cart</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%if (session.getAttribute("user") == null) {%>
			<li><a href="/Java/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a id="user-name" data-toggle="modal" data-target="#myModal">
				<span class="glyphicon glyphicon-log-in"></span> Login</a>
			</li>
			<% } else { %>
			<li><a href="/Java/login?signout=true"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
			<li><a id="user-name" href="/Java/user">
				<span class="glyphicon glyphicon-log-in"></span> <%=((KhachHang) session.getAttribute("user")).getTendn()%></a>
			</li>
			<%}%>
		</ul>
	</div>
</nav>

<!-- MODAL -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Login</h4>
			</div>
			<div class="modal-body" style="padding-bottom: 50px">
				<table align="center" style="margin: auto">
					<form action="/Java/login" method="post">
					<tbody>
						<tr style="height: 40px">
							<td>User:</td>
							<td><input id="TenDN" name="user" type="text" value=""></td>
						</tr>
						<tr style="height: 40px">
							<td align="right">Password:</td>
							<td><input id="Matkhau" name="pwd" type="password"></td>
						</tr>
						<tr style="height: 40px;">
							<td></td>
							<td><input type="Submit" value="Login"></td>
						</tr>
					</tbody>
					</form>
				</table>
			</div>
		</div>

	</div>
</div>

<!-- SCRIPT -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>