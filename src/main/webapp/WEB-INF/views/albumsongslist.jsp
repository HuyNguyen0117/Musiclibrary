<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
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
	    
	    <div class="left">
	    
	    
	    </div>
	   
	    <div class="middle">
	    
	    
	    	<div class="songs">
		    	<%
		    		String albumName = (String)session.getAttribute("albumName");
		    		List<Song> lst = (List<Song>) session.getAttribute("songs");
		    	
		    	%>
		    	
		    	<h1>Album: <%=albumName%></h1>
		    	<div class="audio_player">
					<audio id="audio" preload="auto" tabindex="0" controls="" type="audio/mpeg">
	        			<%
	        				if(lst.size() > 0){
	        			%>
	        				<source src="<%=lst.get(0).getUrl()%>">
	        			<%} %>
	    			</audio>
			
					<ul id="playlist">
			    		<%
							for(Song s: lst){	
								String url = s.getUrl();
			    		%>
			    			
			    		<li class="active">
			    			<a href="<%=url%>"><%=s.getName()%></a> 
			    		</li>
			    		
			    			
			    		<%} %>
		    		</ul>
	    		</div>
	    		
		    
	    	
	    	</div>
	    		
	    
	    </div>
	    
	    <div class="right">
	    
	    </div>
        
  
		
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>