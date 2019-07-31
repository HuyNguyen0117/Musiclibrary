<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Album" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Song" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Artist" %>
<%@include file="header.jsp" %>
<html>
<head>
 <title>Play Music</title>

<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

</head>
<body>

	<div class="container">
	    
	    <div class="left">
	    
	    
	    </div>
	   
	    <div class="middle">
	    
	    	<div class="result">
	    	
				<h1>Results Page</h1>
				
				<%
					List<Song> songs = (List<Song>)session.getAttribute("songs");
					List<Album> albums = (List<Album>) session.getAttribute("albums");
					List<Artist> artists = (List<Artist>) session.getAttribute("artists");
					
					if(songs.size() > 0){ %>
					
						<h4>Results for Song</h4>
						<div class="song">
							<ul>		
					<%
				
						for(Song s : songs){
							String songName = s.getName();
			    			String songNameWithoutSpace = songName.replaceAll(" ", "_");
							
						%>
							<li>
								<a id="song" href="/musiclibrary/songs/song/<%=songNameWithoutSpace%>"><%=s.getName()%></a>
							</li>
						
						<%}%>
						</ul>
					</div>
				 <%} %>
				 
				 <%
					if(albums.size() > 0){ %>
					
						<h4>Results for Album</h4>
						<div class="album">
						<ul>
					
					<%
						for(Album a : albums){
							String albumName = a.getName();
			    			String link = "/musiclibrary/albums/album/" + albumName;
			    			String link1 = link.replaceAll(" ", "_");
							
					%>
						
						<li>
							<a href="<%=link1%>"><%=a.getName()%></a>
						</li>
						
						<%} %>
						
					</ul>
					</div>
				 <%} %>
				 
				 
				  <%
					if(artists.size() > 0){ %>
					
						<h4>Results for Artist</h4>
						
						<div class="artist">
						<ul>
					
					<%
				
						for(Artist a : artists){
							String artistName = a.getName();
			    			String link = "/musiclibrary/artists/artist/" + artistName;
			    			String link1 = link.replaceAll(" ", "_");
						%>
						    <li><a href="<%=link1%>"><%=artistName%></a></li>
						
						<%} %>
						
						</ul>
						
						</div>
				 <%} %>
				
			</div>
	    
	    </div>
	    
	    <div class="right">
	    
	    </div>
        
  
		
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>