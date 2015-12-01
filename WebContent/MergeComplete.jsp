<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<LINK REL="stylesheet" TYPE="text/css"
	HREF="${pageContext.request.contextPath}/webapp/WEB-INF/pages/css/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<title>Merge Complete</title>
</head>

<body>
	<h1>Merge Complete</h1>
	<p>MERGED FILE RIGHT HERE</p>

	<div id="centerbox"><%=request.getAttribute("mergedFile")%>
		<br> <br>
		<%
			String file_name = (String) request.getAttribute("mergedFileName");
		%>
	</div>
	<!-- Send "merge" as "download" when download button is clicked -->
	<center>
		<form method="post"
			action="${pageContext.request.contextPath}/MergeFiles/">
			<input type="hidden" name="mergedFileName" value="<%=file_name%>" />
			<input type="submit" name="merge" value="download">
		</form>

	</center>
</body>
</html>