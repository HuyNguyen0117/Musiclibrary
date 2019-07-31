<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@include file="header.jsp" %>
<html>
<head>
	<title>Change Password</title>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="changepassword">       
			<form method="POST" action="/musiclibrary/changepasswordprocess">
				Email: <input type="email" name="email" required placeholder="Enter your email address"> <br>
				Old Password: <input type="password" id="oldpassword" required pattern="\w+" name="oldpassword"> <br>
				New Password: <input title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" 
					type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="newpassword" onchange=" 
					this.setCustomValidity(this.validity.patternMismatch ? this.title : ''); 
					if(this.checkValidity()) form.pwd2.pattern = this.value; "> <br>
				
				<input type="submit" value="Login">
				
			
			</form>
			<h1>${error}</h1>
		</div>
	
	</div>
	
</body>
</html>
<%@include file="footer.jsp" %>