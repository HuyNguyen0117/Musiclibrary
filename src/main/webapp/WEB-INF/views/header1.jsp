<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="javax.servlet.http.Cookie" %>

<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<div class="header">

	<div class="header1_container">
		<div class="home">
			<a href="/musiclibrary">Musiclibrary</a>
		</div>
		
		<div class="search">
			 <form method="post" action="/musiclibrary/searchhandler">
			 	<input type="text" id="search" name="search" required placeholder="Enter an album, artist, or a song">
			 	<input type="submit" id="submit" name="submit" value="Search">	
			 </form>
		</div>
		
		<%
        	String name = null;
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
            	for(Cookie cookie: cookies){
            		if(cookie.getName().equals("name"))
            			name = cookie.getValue();
            	}
            }
       		if(name == null){ %>
            	<div class="login">
					<a href="/musiclibrary/login">Login</a>
		
				</div>
		
				<div class="register">
					<a href="/musiclibrary/register">Register</a>
		
				</div>
         <% }else{ %>
      			<div class="account">
      			   <ul>
      			   		<li><a href="#">Account</a>
      			   			<ul>
      			   				<li><a href="#">Message</a><li>
      			   				<li><a href="#">My Playlist</a><li>
      			   				<li><a href="#">My Upload</a><li>
      			   				<li><a href="#">Change Password</a><li>
      			   				<li><a href="#">Change My Information</a><li>
      			   				<li><a href="/musiclibrary/logout">Logout</a><li>
      			   			</ul>
      			   		</li>
      			   </ul>   
				   
				</div> 
				            	
        
         <%} %>

		
	</div>
	
	<div class="header2_container">
		<ul>
			<li><a href="/musiclibrary/albums/all">Albums</a>
				<ul>
					<li><a href="/musiclibrary/albums/album_nhacviet">Viet Nam</a></li>
					<li><a href="/musiclibrary/albums/album_usa">USA</a></li>
					<li><a href="/musiclibrary/albums/album_korea">Korea</a></li>
					<li><a href="/musiclibrary/albums/album_china">China</a></li>
				</ul>
			</li>
			
			<li><a href="/musiclibrary/artists/all">Artists</a>
				<ul>
					<li><a href="/musiclibrary/artists/artist_viet">Viet Nam</a></li>
					<li><a href="/musiclibrary/artists/artist_usa">USA</a></li>
					<li><a href="/musiclibrary/artists/artist_korea">Korea</a></li>
					<li><a href="/musiclibrary/artists/artist_china">China</a></li>
				</ul>
			</li>
			
			<li><a href="/musiclibrary/genres/all">Genres</a>
				<ul>
					<li><a href="/musiclibrary/genres/rock">Rock</a></li>
					<li><a href="/musiclibrary/genres/blue">Blue</a></li>
					<li><a href="/musiclibrary/genres/country">Country</a></li>
					<li><a href="/musiclibrary/genres/r&b">R&B</a></li>
					<li><a href="/musiclibrary/genres/reggaefusion">Reggae Fusion</a></li>
					<li><a href="/musiclibrary/genres/other">Other</a></li>
				</ul>
			</li>
				
			<li><a href="/musiclibrary/tops/all">Top</a>
				<ul>
					<li><a href="/musiclibrary/tops/top_viet">Viet Nam</a></li>
					<li><a href="/musiclibrary/tops/top_usa">USA</a></li>
					<li><a href="/musiclibrary/tops/top_korea">Korea</a></li>
					<li><a href="/musiclibrary/tops/top_china">China</a></li>
				</ul>
			
			
			</li>
		</ul>
		
		<div class="playlist">
			
			<a href="#">Create Playlist</a>
		</div>
		
		<div class="upload">
			
			<a href="/musiclibrary/upload">Upload</a>
		</div>
		
	</div>

</div>

</body>
</html>
