<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Title</title>
</head>

<body bgcolor="white">

	<h1>Hello Hello!</h1>

	<form action="${pageContext.request.contextPath}/saveUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"> 
		<input type="submit">
	</form>

</body>
</html>