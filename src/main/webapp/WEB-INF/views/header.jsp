<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="javax.servlet.http.Cookie" %>

<html>
<head>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<div class="container">
		
		<nav class="navbar navbar-default" role="navigation">
		  	<div class="container-fluid">
		  		<!-- Brand and toggle get grouped for better mobile display -->
		    	<div class="navbar-header">
			      	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      	</button>
			     	<a class="navbar-brand" href="/musiclibrary">Home</a>
		    	</div>
		    	
		    	<!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		      	 <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Albums <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="/musiclibrary/albums/album_nhacviet">Viet Nam</a></li>
					<li><a href="/musiclibrary/albums/album_usa">USA</a></li>
					<li><a href="/musiclibrary/albums/album_korea">Korea</a></li>
					<li><a href="/musiclibrary/albums/album_china">China</a></li>
					<li><a href="/musiclibrary/albums/all">All</a></li>
		          </ul>
		        </li>
		        
		         <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Artists <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="/musiclibrary/artists/artist_viet">Viet Nam</a></li>
					<li><a href="/musiclibrary/artists/artist_usa">USA</a></li>
					<li><a href="/musiclibrary/artists/artist_korea">Korea</a></li>
					<li><a href="/musiclibrary/artists/artist_china">China</a></li>
					<li><a href="/musiclibrary/artists/all">All</a></li>
		          </ul>
		        </li>
		        
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Genres <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="/musiclibrary/genres/rock">Rock</a></li>
					<li><a href="/musiclibrary/genres/blue">Blue</a></li>
					<li><a href="/musiclibrary/genres/country">Country</a></li>
					<li><a href="/musiclibrary/genres/r&b">R&B</a></li>
					<li><a href="/musiclibrary/genres/reggaefusion">Reggae Fusion</a></li>
					<li><a href="/musiclibrary/genres/other">Other</a></li>
					<li><a href="/musiclibrary/genres/all">All</a></li>
		          </ul>
		        </li>
		     
		      
			      <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Top <span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            	<li><a href="/musiclibrary/tops/top_viet">Viet Nam</a></li>
						<li><a href="/musiclibrary/tops/top_usa">USA</a></li>
						<li><a href="/musiclibrary/tops/top_korea">Korea</a></li>
						<li><a href="/musiclibrary/tops/top_china">China</a></li>
						<li><a href="/musiclibrary/tops/all">All</a></li>
			          </ul>
			        </li>
		      </ul>
		      <form class="navbar-form navbar-left" role="search" method="post" action="/musiclibrary/searchhandler">
		        <div class="form-group">
		          <input type="text" id="search" name="search" class="form-control" placeholder="Enter an album, artist, or a song">
		        </div>
		        <button type="submit" class="btn btn-default">Submit</button>
		      </form>
		      <ul class="nav navbar-nav navbar-right">
		       
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Account <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            
		            <%
			            String name = null;
			            Cookie[] cookies = request.getCookies();
			            if(cookies != null){
			            	for(Cookie cookie: cookies){
			            		if(cookie.getName().equals("name"))
			            			name = cookie.getValue();
			            	}
			            }
		       		if(name == null){%>
		       			<a href="/musiclibrary/login">Login</a>
		       		<%}else{ %>
		       		
		       			<h3>Hello <%=name %></h3>
		       			<li><a href="#">Message</a><li>
      			   		<li><a href="#">My Playlist</a><li>
      			   		<li><a href="#">My Upload</a><li>
      			   		<li><a href="#">Change Password</a><li>
      			   		<li><a href="#">Change My Information</a><li>
      			   		<li><a href="/musiclibrary/logout">Logout</a><li>
		       		<%}%>
		            
		          </ul>
		        </li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  
		 	</div>
		 </nav>

</div>

</body>
</html>
