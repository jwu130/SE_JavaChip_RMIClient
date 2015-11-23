<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Choose File</title>
</head>
<body>

	<h1>Step 1: Choose File From Server</h1>
	<p>File from Depository:</p>

	<%@ page import="java.util.LinkedList"%>
	<%@ page import="com.javachip.util"%>
	<%@ page import="java.util.ListIterator"%>
	
	<% LinkedList<String> fileNames = util.getAvailableFiles(); %>
	<% ListIterator<String> listIterator = fileNames.listIterator(); %>
	<% String filename; %>

	<form action="confirmFile">
		<select name="fileName" id="dropdown">
			<% while (listIterator.hasNext()) { %>
			<% filename = listIterator.next(); %>
			<option value=<%=filename%>>
				<%=filename%>
			</option>
			<% } %>
		</select>
		<input type="submit">
	</form>
	
</body>
</html>