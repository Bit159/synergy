<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가 정보 입력</title>
<link rel="stylesheet" href="/resources/css/addInfoForm.css">
</head>
<body>
<jsp:include page="../template/header.jsp"/>
<section id="addInfoForm" class="addInfoForm">
	<form action="/all/addInfo" method="post" class="join-form">
		<div class="join-area">
			<input type="text" name="username" id="username" autocomplete="off" value="${username}" required readonly>
			<label for="username">E-MAIL</label>
		</div>
		
		<div class="join-area">
			<input type="text" name="nickname" id="nickname" autocomplete="off" required>
			<label for="nickname">NICKNAME</label>
		</div>
	
		<div class="join-area">
			<input type="text" id="birthYear" name="birthYear" style="width: 45%;" autocomplete="off" required>
			<label for="birthYear">BIRTH YEAR</label>
		</div>
		
		<div class="btn-area">
			<button>JOIN!</button>
			<button type="button" name="back" id="back" onclick="javascript='history.go(-1)'">BACK</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	</form>
</section>
<%-- <jsp:include page="../template/footer.jsp"/> --%>
</body>
</html>