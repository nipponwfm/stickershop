<!-- NAVBAR -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Book Store</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/Java/home">Home</a></li>
      <li><a href="/Java/cart">Cart</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-log-in"></span> <%=session.getAttribute("name")!=null?session.getAttribute("name"):"Login"%></a></li>
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
      <div class="modal-body">
      	<form action="/Java/header" method="post">
      		User <input type="text" name="user"/>
      		Password <input type="text" name="pwd"/>
      		<input type="submit" value="Login"/>
      	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<!-- SCRIPT -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>