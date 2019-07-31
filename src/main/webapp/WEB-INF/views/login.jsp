<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="header.jsp" %>
<html>
<head>
	<title>Log In</title>
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
			<div class="row">
				<div class="col-sm-6 col-md-4 col-md-offset-4">
					<h1 class="login-title">Please sign in </h1>
					<div class="account-wall">
			
						<form class="form-signin" method="post" action="/musiclibrary/loginhandler">
							<input type="text" id="email" name="email" class="form-control" placeholder="Email" required autofocus>
							<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
							<div class="checkbox">
							    <label>
							        <input type="checkbox" value="remember-me"></input>
							         Remember me
							    </label>
						   </div>
						</form>
					</div>
				<a href="/musiclibrary/register" class="text-center new-account">Create an account </a>
				</div>
			</div>
		</div>
	</div>
		
		<%
			String action = null;
			action = (String)request.getAttribute("error");
			System.out.println(action);
			if(action != null && action.equals("notregistered"))
				
		{%>
		    <script>  
	   			 alert("Your email or password is incorrect, Please re_enter");  
	    	</script> 
	    <%}else if(action != null && action.equals("notactivated")){%>
	    	 <script>  
	   			 alert("Your account is not activated! Please visit your email to activate your account");  
	    	</script> 
	    
	    <%} %>
    
   

</body>
</html>
<%@include file="footer.jsp" %>