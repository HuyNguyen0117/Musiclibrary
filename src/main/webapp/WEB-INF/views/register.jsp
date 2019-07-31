<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="header.jsp" %>
<html>
<head>
	<title>Register</title>
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

	

	<%
		final int DAYS = 31;
		final int MONTHS = 12;
		final int YEARS = 1950;
		String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	%>

	<div class="container">
		<div id="container"> 
		
			<div class="row">
				<div class="col-sm-6 col-md-4 col-md-offset-4">
					<h1 class="login-title">Register </h1>
					<div class="account-wall">
						
						<form class="form-sigup" name="signup" method="POST" onsubmit="return formValidation();" action="/musiclibrary/registervalidation">
							<input type="text" id="firstname" name="firstname" class="form-control" placeholder="First Name" required autofocus>
							<input type="text" id="lastname" name="lastname" class="form-control" placeholder="Last Name" required> 
							<input type="email" id="email" name="email" class="form-control" required placeholder="Enter a valid email address"> 
							<input title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" 
								type="password" id="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password" class="form-control"
								placeholder="Password" onchange=" 
								this.setCustomValidity(this.validity.patternMismatch ? this.title : ''); 
								if(this.checkValidity()) form.pwd2.pattern = this.value; ">
								<input title="Please enter the same Password as above" type="password"
								 required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="confirm_password"
								 class="form-control" placeholder="Confirm Passowrd"
								  onchange=" this.setCustomValidity(this.validity.patternMismatch ? this.title : ''); "> 
							
							<br>
							Gender: <input type="radio" id="gender" name="gender" value="Male"> Male
									<input type="radio" id="gender" name="gender" value="Female"> Female <br>
							
							<br>		
						 	Day of birth: 
						 		<div class="dob">
						 			Day: 
						 			<select id="day" name="day">
						 				<%
						 					for(int i = 0; i <= DAYS; i++){ %>
						 							<option value="<%=Integer.toString(i + 1)%>"><%=i + 1%></option>
						 							
						 				<% }%>
						 				
						 			</select>
						 			
						 			Month: 
						 			<select id="month" name="month">
						 				<%
						 					for(int i = 0; i < MONTHS; i++){ %>
						 						<option value="<%=i+1%>"><%=months[i]%></option>
						 				<% }%>  	 	
						 				
						 			</select>
						 			
						 			Year: 
						 			<select id="year" name="year">
						 				<%
						 					for(int i = 2014; i > YEARS; i--){ %>
						 						<option value="<%=Integer.toString(i)%>"><%=i%></option>
						 				<% }%>
						 				
						 			</select>
						 	
						 		</div>
						 	
						 	<br>
						 	<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>					 	
							</form>
					</div>
				</div>
			</div>
		
				<h1>${error}</h1>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>
<%@include file="footer.jsp" %>