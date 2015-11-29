<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL="stylesheet" TYPE="text/css" HREF="webapp/css/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<title> File Merge Initial Page </title>
</head>
<body>
<h1> Welcome! </h1>
<p> Simply Select two Files you would like to Merge. </p>
<div><h3>Step 1: Choose a file from Depository.</h3> </div>

<%-- Populate drop down with filenames --%>

<%@ page import="java.util.LinkedList"%>
	<%@ page import="com.javachip.Util"%>
	<%@ page import="java.util.ListIterator"%>
	
	<% LinkedList<String> fileNames = Util.getAvailableFiles(); %>
	<% ListIterator<String> listIterator = fileNames.listIterator(); %>
	<% String filename1; %>
	

	<form action="${pageContext.request.contextPath}/RetrieveChoices" method="post" enctype="multipart/form-data">
		<select name="fileName" id="dropdown">
			<% while (listIterator.hasNext()) { %>
			<% filename1 = listIterator.next(); %>
			<option value=<%=filename1%>>
				<%=filename1%>
			</option>
			<% } %>
		</select>
		
		 <h3>Step 2: Upload a File </h3>
<p>
		<input type="file" name="file"> 
		<br><br><input type="submit">

	</form>
<%-- Submit Button for File Chosen ---> Confirm the file with the user --%>

<script> 
</script>

</body>
</html>