<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="/resources/js/login.js" defer></script>
    <link rel="stylesheet" href="/resources/css/login.css" />
    <title>로그아웃</title>
  </head>
  <body>
    <%@ include file="/template/header.jsp" %> 
<form method="POST" action="/mylogout">
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
		<button>로그아웃</button>
</form>
<%@ include file="/template/footer.jsp" %>
  </body>
</html>
