<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
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
		
		<div class="audio_player">
			
	    </div>
	    
	    <div class="left">
	    
	    
	    </div>
	   
	    <div class="middle">
	    	
	    		<div class="artists">
	    			
	    			<%
	    				String country = (String) session.getAttribute("country");
	    			%>
	    			
	    			<h1><%=country%> Artists List</h1>
	    			
	    			<ul>
	    				<%
	    					List<Artist> lst = (List<Artist>) session.getAttribute("artists");
	    				
	    					for(Artist a: lst){
	    						
	    						String artistName = a.getName();
				    			String link = "/musiclibrary/artists/artist/" + artistName;
				    			String link1 = link.replaceAll(" ", "_");
				    				
	    				%>
		    				<div class="artist">
		    					<a href="<%=link1%>"><img alt="images" src="<%=a.getImage()%>"></a>
		    					<li><a href="<%=link1%>"><%=artistName%></a></li>
		    					<br>
		    					<p class="minimize"><%=a.getDescription()%></p>
		    				
		    				</div>
	    				<%}%>
	    			</ul>
	    	
				</div>
	
	    
	    </div>
	    
	    <div class="right">
	    
	    </div>
        
  
		
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>