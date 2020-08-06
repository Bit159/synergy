<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta id="_csrf" name="_csrf" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<meta charset="UTF-8">
<title>촛됐다</title>
</head>
<body>
${login_id} 님 안녕하세요ㅎㅎ
<br>
<form method="post" id="welcome" action="/synergy/all/loginForm">
<input type="submit" value="logout" id="logoutBtn">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
</form>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
// $(document).ready(function(){
// 	var token = $("meta[name='_csrf']").attr("content");
// 	var header = $("meta[name='_csrf_header']").attr("content");
// 	$(document).ajaxSend(function(e, xhr, options) {
// 		xhr.setRequestHeader(header,token);
// 	});
// });
</script>
</html>