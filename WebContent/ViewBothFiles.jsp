<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL="stylesheet" TYPE="text/css" HREF="${pageContext.request.contextPath}/webapp/WEB-INF/pages/css/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>View Both Files</title>
<script>
function ConfirmDialog(){
	   
	location.href ='${pageContext.request.contextPath}/MergeFiles?file1=<%=request.getAttribute("file1")%>&file2=<%=request.getAttribute("file2")%>';
	
}
</script>
</head>
<body>
<h1> View Both Files and Start Merge </h1>
<center>
<div id = "leftbox"><u>FILE 1 Contents</u>
<p> <%=request.getAttribute("fileUploaded")%> </p>
</div>

<div id = "rightbox"><u>FILE 2 Contents </u>
<p><%=request.getAttribute("fileFromServer")%> </p>
</div>
</center>
<br>
<br>

<div id = "centerbox"> Would you like to keep Repeats? 
<form action="${pageContext.request.contextPath}/MergeFiles?file1=<%=request.getAttribute("file1")%>&file2=<%=request.getAttribute("file2")%>" method="post">
		<select name="keepRepeats" id="dropdown">
			<option value=true> Yes </option>
			<option value=false> No </option>
		</select>

		<button type="submit" name="merge" value="submit" class="button2">Start Merge</button>
		
</form>
</div>
</body>
</html>