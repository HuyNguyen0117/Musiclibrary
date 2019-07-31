<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
	    	<div class="tops">
		    	<%
		    		String country = (String)session.getAttribute("country");
		    		String top_country = (String)session.getAttribute("top_country");
		    		List<Song> songs = (List<Song>) session.getAttribute("songs");
		    	%>
		    
		    		<h1><%=country%> Top Songs</h1>
		    	
		    	<%
					List<Song> topVietSongs = new ArrayList<Song>();
					List<Song> topUsaSongs = new ArrayList<Song>();
		    		if(country.equals("All")){
		    			for(Song s: songs){
		    				if(s.getCountry().getIdcountry() == 1 && topVietSongs.size() < 11){
		    					topVietSongs.add(s);
		    				}
		    				
		    				if(s.getCountry().getIdcountry() == 2 && topUsaSongs.size() < 11){
		    					topUsaSongs.add(s);
		    				}
		    			}
		    			
		    		
		    	%>
		    	
		    	<div class="top_viet">
		    		<a id="top_viet" href="/musiclibrary/tops/top_viet">VietNam Top Songs</a>
		    	</div>
		    	<ul id="top">	
		    	<%	
		    		for(Song s: topVietSongs){
		    			String songName = s.getName();
		    			String songNameWithoutSpace = songName.replaceAll(" ", "_");
		    	%>
		    		<li><a href="/musiclibrary/songs/song/<%=songNameWithoutSpace%>"><%=s.getName()%></a></li>
		    		
		    	<%}%>
		    	</ul>
		    	<div class="top_usa">
		    		<a id="top_usa" href="/musiclibrary/tops/top_usa">USA Top Songs</a>
		    	</div>
		    	<ul id="top">
		    	<%
		    		for(Song s: topUsaSongs){
		    			String songName = s.getName();
		    			String songNameWithoutSpace = songName.replaceAll(" ", "_");
		    	%>
		    		<li><a href="/musiclibrary/songs/song/<%=songNameWithoutSpace%>"><%=s.getName()%></a></li>
		    		
		    	<%}%>
		    	</ul>
		    	
		    	<%} else{%>
		    	
		    	<ul id="top_country">
		    	<%
		    			for(Song s: songs){
		  					String songName = s.getName();
			    			String songNameWithoutSpace = songName.replaceAll(" ", "_");
		    		
		    		%>
		        		<li><a href="/musiclibrary/songs/song/<%=songNameWithoutSpace%>"><%=s.getName()%></a></li>
			    	
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