<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="include/head_include.jsp" %>
    <script src="/a/resources/js/login.js" defer></script>
    <link rel="stylesheet" href="/a/resources/css/login.css" />
    <title>로그아웃</title>
  </head>
  <body>
    <%@ include file="include/nav_include.jsp" %> 
    <h1>mylogout</h1>
    <h1>로그 아웃이다 이마리야르</h1>
<form method="POST" action="/a/mylogout">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
<button>로그아웃</button>
</form>
    
  </body>
</html>
