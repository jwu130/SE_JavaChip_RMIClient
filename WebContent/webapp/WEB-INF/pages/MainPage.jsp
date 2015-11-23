<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL="stylesheet" TYPE="text/css" HREF="css/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<title> File Merge Initial Page </title>
</head>
<body>

<h1> Welcome! </h1>
<p> Simply Select two Files you would like to Merge. </p>
<div id ="leftbox"><p>Step 1: Choose a file from Depository.</p> 

<%-- Populate drop down with filenames --%>

<%@ page import="java.util.LinkedList" %>
<%@ page import = "com.javachip.util" %>
<%@ page import = "java.util.ListIterator" %>
<% LinkedList <String> fileNames = com.javachip.util.getAvailableFiles(); %>
<% ListIterator <String> listIterator = fileNames.listIterator(); %>
<% String filename; %>

<form>
 <select name = "fileName" id = "dropdown"> 
<%while(listIterator.hasNext()){%>
	<option id = "fileSelected"> <%=listIterator.next()%></option> 
<%} %>

 </select> 
</form>

<button <%--onclick = "getFileContents()"--%>> Submit </button></div>
<div id="rightbox"> File 1 Preview </div>
<div id = "leftbox"> <p>Step 2: Upload a File </p></div>
<div id="rightbox"> File 2 Preview</div>

<button> Merge the Two Files Please! </button>

<div id = "centerbox"> Merged File </div>

<script> 

<%--
function getFileContents(){
	var fileSelected = getElementById("fileSelected");
	<% String fileName = %> fileSelected;
	String contents = <%= util.setFileRequest(fileName)%>
	getElementById("rightbox").innerHTML = contents;	
};
--%>
</script>

</body>
</html>