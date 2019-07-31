<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Genre" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Song" %>
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
		
		<div class="audio_player">
			
	    </div>
	    
	    <div class="left">
	    
	    
	    </div>
	   
	    <div class="middle">
	    
	    	<div class="genres">
	    
		    	<%
		    		String type = (String)session.getAttribute("type");
		    	%>
		    		
		
		    	
		    	<% 
		    		if(session.getAttribute("type").equals("All")){
		    			List<Genre> genres = (List<Genre>) session.getAttribute("genres");
		    	%>
		    			 <h1><%=type%> Genres List</h1>	
		    	<%
			    		for(Genre g : genres){
			    			List<Song> songs = g.getSongs();
			    			String genreUrl = g.getType().replaceAll(" ", "");
			    %>
			    	
				    
				   <ul id="genre">
				    		
				    	<li>
				    		<a href="/musiclibrary/genres/<%=genreUrl.toLowerCase()%>"><%=g.getType()%></a>
				    	</li>
				    	
				    	
				    </ul>
				    
				    <ul id="playlistall">
				    	<%
				    		for(Song s : songs){
				    			String songName = s.getName();
				    			String songNameWithoutSpace = songName.replaceAll(" ", "_");
				    	%>
				    	
				    		<li>
								<a id="song" href="/musiclibrary/songs/song/<%=songNameWithoutSpace%>"><%=s.getName()%></a>
							</li>
											
				    	
				    	<%} %>
				    
				    </ul>
							
					<%} %>
					
				<%} else{
					
							Genre genre = (Genre)session.getAttribute("genre");
							List<Song> songs = genre.getSongs();
					
				%>
				
							<h1><%=type%></h1>
	    					
	    					
									
						    	
						    	<ul id="playlist">
								
									<%
										for(Song s : songs){
											String songName = s.getName();
							    			String songNameWithoutSpace = songName.replaceAll(" ", "_");
									%>
								
										<li>
											 <a id="song" href="/musiclibrary/songs/song/<%=songNameWithoutSpace%>"><%=s.getName()%></a>
										 </li>
											
									
									
								<%} %>
									
									
								</ul>
					
						<%} %>
			
			</div>
	    
	    </div>
	    
	    <div class="right">
	    
	    </div>
        
  
		
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>