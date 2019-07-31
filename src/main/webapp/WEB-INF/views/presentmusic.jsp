<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="header.jsp" %>
<html>
<head>
 <title>Play Music</title>

<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="<c:url value="/resources/js/audio.js" />"></script>

</head>
<body>

	<div class="container">
		
		<div class="audio_player">
		
			<audio id="audio" preload="auto" tabindex="0" controls="" type="audio/mpeg">
	        	
	    	</audio>
	    </div>
	    
	    <div class="left">
	    
	    
	    </div>
	   
	    <div class="middle">
	    
		    <ul id="playlist">
		    	
		    	<c:forEach items="${urls}" var="item">
		        	<li class="active">
		        		<a href="${item.value}">${item.key}</a> 
		        	</li>
		        </c:forEach>
		    </ul>
	    
	    </div>
	    
	    <div class="right">
	    
	    </div>
        
       
		<div style="clear: both;"></div>
	</div>
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>