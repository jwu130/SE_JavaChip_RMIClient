<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL="stylesheet" TYPE="text/css" HREF="/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>View Both Files</title>
<script>
function ConfirmDialog(){
	   
	location.href ='${pageContext.request.contextPath}/MergeFiles?file1=<%=request.getAttribute("file1")%>&file2=<%=request.getAttribute("file2")%>';
	
}
</script>
</head>
<body>
<h1> View Both Files</h1>

<div id = "leftbox">Left Side: FILE 1 Contents</div>
<p> <%=request.getAttribute("fileUploaded")%> </p>
<div id = "rightbox"> Right Side: FILE 2 Contents </div>
<p><%=request.getAttribute("fileFromServer")%> </p>

<div> Would you like to keep Repeats? </div>
<form action="${pageContext.request.contextPath}/MergeFiles?file1=<%=request.getAttribute("file1")%>&file2=<%=request.getAttribute("file2")%>" method="post">
		<select name="keepRepeats" id="dropdown">
			<option value=false>false </option>
			<option value=true>true</option>
		</select>
		<input type = "submit" name="merge" value="submit" >
</form>

</body>
</html>