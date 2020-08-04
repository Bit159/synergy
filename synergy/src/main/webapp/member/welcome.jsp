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
${memId} 님 안녕하세요ㅎㅎ
<br>
<input type="button" value="logout" id="logoutBtn" onclick="location.href='/synergy/member/logout'">
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header,token);
	});
});
</script>
</html>