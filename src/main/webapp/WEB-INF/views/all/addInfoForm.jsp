<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가 정보 입력</title>
<link rel="stylesheet" href="../resources/css/addInfoForm.css">
</head>
<body>
<div class="nav">
    <nav>
        <div id="logoDiv">
            <a href="/synergy/index"><img src="../resources/image/logo.png" /></a>
        </div>
        <div id="menuButtons">
            <span>프로젝트소개</span>
            <span>이용 방법</span>
            <span>자주 묻는 질문</span>
            <span>로그인</span>
            <span>회원가입</span>
            <span><a href="/synergy/member/myPage_Update"><img src="../resources/image/my.png" width="30" height="30"></a></span>
        </div>
        <div id="menuButtonsM">
            <a href="javascript:mobileMenu()">
                <div class="line"></div>
                <div class="line"></div>
                <div class="line"></div>
            </a>
        </div>
    </nav>
</div>
<form action="/synergy/all/addInfo" method="post" class="join-form">
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
</body>
</html>