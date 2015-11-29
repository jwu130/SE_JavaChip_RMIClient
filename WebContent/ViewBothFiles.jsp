<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL="stylesheet" TYPE="text/css" HREF="webapp/css/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>View Both Files</title>
</head>
<body>
<h1> View Both Files</h1>

<div id = "leftbox">Left Side: FILE 1 Contents</div>
<p> <%=request.getAttribute("fileUploaded")%> </p>
<div id = "rightbox"> Right Side: FILE 2 Contents </div>
<p><%=request.getAttribute("fileFromServer")%> </p>
<button onclick = "location.href ='${pageContext.request.contextPath}/MergeFiles'">Complete Merge! </button>
</body>
</html>