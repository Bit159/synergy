<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../resources/css/join.css">
<script defer type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer type="text/javascript" src="../resources/js/join.js"></script>
</head>
<body>
<div class="nav">
	<nav>
		<div id="logoDiv">
		<a href="/synergy/index"><img src="../resources/image/testLogo.png" /></a>
		</div>
		<div id="menuButtons">
			<span>프로젝트소개</span>
			<span>이용 방법</span>
			<span>자주 묻는 질문</span>
			<span>로그인</span>
			<span>회원가입</span>
		</div>
		<div id="menuButtonsM">
		<a href="javascript:mobileMenu()">
			<span></span>
		</a>
		</div>
	</nav>
</div>
<section class="join-form">
	<h1>Let's Synergy</h1>
	<form action="/synergy/join" method="post">
		<div class="join-area">
			<input type="text" name="username" id="username" autocomplete="off" required>
			<label for="username">E-MAIL</label>
		</div>
		
		<div class="join-area">
			<input type="text" name="nickname" id="nickname" autocomplete="off" required>
			<label for="nickname">NICKNAME</label>
		</div>
		
		<div class="join-area">
			<input type="password" name="password" id="password" autocomplete="off" required>
			<label for="password">PASSWORD</label>
		</div>
		
		<div class="join-area">
			<input type="password" name="repwd" id="repwd" autocomplete="off" required>
			<label for="repwd">RE-PASSWORD</label>
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
</body>
</html>