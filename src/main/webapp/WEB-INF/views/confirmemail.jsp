<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	
</head>
<body>
	
	<%
		String error = request.getParameter("error");
		if(error == null){
	%>
		<h4>Your account has been activated. Please click on <a href="/musiclibrary/login">here</a> to login</h4>
	
	<%}%>
	
</body>
</html>