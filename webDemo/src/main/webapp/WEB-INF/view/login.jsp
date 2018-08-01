<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
	<input type="text" name="name" />
	<input type="text" name="passwd" />
	<input type="submit" value="登录" />
</form>
</body>
</html>