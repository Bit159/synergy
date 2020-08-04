<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="로그아웃" />
	</form>
	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
