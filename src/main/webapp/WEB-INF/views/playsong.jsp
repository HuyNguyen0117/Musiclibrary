<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Song" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="com.huynguyen.musiclibrary.service.ServerValidation"%>
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
	    
	    	
	    	<div class="song">
		    	<div class="audio_player">
		    	
		    	
		    	
		    			<%
		    			Song song = (Song)session.getAttribute("songToPlay");
		    			if(song != null){	
		    					String idStr = "";
				    			int userId = -1;
					    		
				    			for(Cookie cookie: cookies){
									if(cookie.getName().equals("id")){
										idStr = cookie.getValue();
										userId = Integer.parseInt(idStr);
									}
							
								}
				    			
				    			ServerValidation svValidation = new ServerValidation();
	    
		   				 %>
		    				
		    				<h3><%=song.getName()%></h3>
		    				<h3>views: <%=song.getView()%></h3>
		    				<h3>Genre: <%=song.getGenre().getType() %></h3>
		    				
		    			
		    			
		    			
							<audio id="audio" preload="auto" tabindex="0" controls="" type="audio/mpeg">
			        				<source src="<%=song.getUrl()%>">
			    			</audio>
		    			
		    				<h3>Lyris: <%=song.getLyris()%></h3>
		    			
		    				<h3>Song Information: <%=song.getDescription() %></h3>
		    				
		    				<%
			    				if(name != null && !svValidation.isThisUserLikedThisSong(userId, song.getIdsong())){%>
		    			
			    				<button type="button" id="like" onclick="updateLike(<%=song.getRate()%>)">Like </button> 
			    				<span id="myDiv"><%=song.getRate()%></span>
		    				
		    					<%} %>
		    					
		    					<%svValidation = null;%>
				
						<%}%>
						
						
				</div>

	    	</div>
	
	    
	    </div>
	    
	    <div class="right">
	    
	    </div>
        
  
		
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>