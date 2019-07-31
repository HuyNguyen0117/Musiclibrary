<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Album" %>
<%@include file="header.jsp" %>
<html>
<head>
 	<title>Music Library</title>

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
	
		<div id="container">
	    
		    <div class="left">
		    
		    
		    </div>
		   
		    <div class="middle">
		    	
		    	<div class="albums">
		    	
		    		<%
		    			String country = (String) session.getAttribute("country");
		    		%>
		    		
		    		<h1><%=country%> Albums List</h1>
		    		
		    		<%
		    			int currentPage = (Integer)session.getAttribute("currentPage");
		    			int numberOfPage = (Integer)session.getAttribute("numberOfPage");
		    			List<Album> albums = (List<Album>) session.getAttribute("albums");
		    			
		    		%>
		    		
		    		<ul>
				    	<%
				    		for(Album a: albums){
				    	
				    			String albumName = a.getName();
				    			String link = "/musiclibrary/albums/album/" + albumName;
				    			String link1 = link.replaceAll(" ", "_");
				    	%>	
				    		<div class="album">
					  	 		<a href="<%=link1%>"><img alt="image" src="<%=a.getImage()%>"></a>
					  	 		<li><a href="<%=link1%>"><%=a.getName()%></a></li>
					  	 		<h6><%=a.getDate()%></h6>
					  	 		<p class="minimize"><%=a.getDescription()%></p>
					  	 		
					  	 			
					  	 	</div>
					  	<%} %>
					</ul>
		    		
		    		
		    
		    		 <%--For displaying Previous link except for the 1st page --%>
		    		 <%
		    		 	if(currentPage != 1){
		    		 
		    		 %>
   						<a href="employee.do?page=${currentPage - 1}">Previous</a>
        				
    				<%} %>
			    	
					
				
				</div>
				<%
					if(numberOfPage != 1){
				
				%>
				<nav>
					  <ul class="pagination">
					    <li><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
					    <%
					    	for(int i = 0; i < numberOfPage; i++){
					    %>
					   		 <li><a href="#"><%=i + 1%></a></li>
					    
					    <%} %>
					    <li><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
					  </ul>
				</nav>
				
				<%}%>
		
	    
	    </div>
	    
	    <div class="right">
	    
	    
        
  		</div>
		
		</div>
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>