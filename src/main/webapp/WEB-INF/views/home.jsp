<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.huynguyen.musiclibrary.dao.Album" %>
<html>
<head>
 <title>Home</title>

<link href="<c:url value="/resources/css/test.css" />" rel="stylesheet">
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="<c:url value="/resources/js/audio.js" />"></script>

</head>
<body>

	<div class="container">
		
		<%
			String sentEmailNotice = (String)request.getAttribute("sentemailnotice");
			if(sentEmailNotice != null){
		%>
			<script type="text/javascript">
				
				alert("Your account has create successfully! Please chech your email to activate your account");
			
			</script>
	
		<%}%>
		
	<div id="container">
		<h1>
			Hello world!  
		</h1>
	
	</div>
	
	</div>

	
	
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>
