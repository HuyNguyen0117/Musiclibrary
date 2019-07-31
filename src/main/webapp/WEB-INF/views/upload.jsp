<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="header.jsp" %>
<html>
<head>
	<title>Upload</title>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

	<%
		String genres[] = {"Blue", "Country", "A&B", "Reggae Fusion", "Other"};
	
	%>

	<div class="upload_container">
		<div class="upload_form">
			<form method="post" action="/musiclibrary/uploadhandler" enctype="multipart/form-data">
				Title: <input type="text" id="title" name="title" required /> <br>
				Artist: <input type="text" id="artist" name="artist" required /> <br> 
				Album: <input type="text" id="album" name="album" required />  <br> 
				Country: <input type="text" id="country" name="country" required /> <br>
				Duration: <input type="text" id="duration" name="duration" required pattern = "^[\+\-]{0,1}([ 0-9]+\.){0,1}[ 0-9]+$" /> <br>
				Date: <input type="date" id="date" name="date" required pattern="0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\d\d" placeholder="mm/dd/yyyy"/> <br> 
				
				Genre:
				<select id="genre" name="genre">
				
					<%
						for(int i = 0; i < genres.length; i++){
					%>
						<option value="<%=genres[i]%>"><%=genres[i]%></option>
				
					<%}%>
				</select>
				
				<br>
				
            	Select mp3 file to upload: <input type="file" id="file" name="uploadFile" /> 
            	<br/><br/>
            	<input type="submit" id="submit" value="Upload" />
            	<br>
            	<h2>${message}</h2>
        	</form>
        	
		</div>
	
	</div>
	

</body>
</html>
<%@include file="footer.jsp"%>