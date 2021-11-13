<!-- NAVBAR -->
<%@page import="bean.KhachHang"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/Java/admin_home">Dashboard</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/Java/admin_book">Book</a></li>
			<li><a href="/Java/admin_type">Type</a></li>
			<li><a href="/Java/admin_user">User</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%if (session.getAttribute("user") == null) {%>
			<li><a href="/Java/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a id="user-name" data-toggle="modal" data-target="#myModal">
				<span class="glyphicon glyphicon-log-in"></span> Login</a>
			</li>
			<% } else { %>
			<li><a href="/Java/login?signout=true"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
			<li><a id="user-name">
				<span class="glyphicon glyphicon-log-in"></span> <%=((KhachHang) session.getAttribute("user")).getTendn()%></a>
			</li>
			<%}%>
		</ul>
	</div>
</nav>

<!-- SCRIPT -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>