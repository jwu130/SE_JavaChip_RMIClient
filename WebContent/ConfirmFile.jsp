<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>XML Merger - File Confirm</title>
</head>

<body bgcolor="white">

	<h1>Hello Hello! Got your choice</h1>
	<%=request.getAttribute("confirmFile")%>
	<form action="UploadFile">
		Is this the file you wanted? 
		<input type="submit" value="yes" name="confirm" >
		<input type="submit" value="no" name="confirm" >
	</form>


</body>
</html>
