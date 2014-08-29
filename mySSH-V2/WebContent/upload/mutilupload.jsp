<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/SSH-Start/servlet/mutilupload" method="post" enctype="multipart/form-data">
		<input type="file" name="myfile" multiple> 
		<input name="age" value="18">
		<input type="submit">
	</form>
</body>
</html>