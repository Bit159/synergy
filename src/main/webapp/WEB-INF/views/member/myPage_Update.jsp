<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/myPage_Update.css"/>
</head>
<body>
<div class="nav">
	<nav>
		<div id="logoDiv">
			<a href="index.html"><img src="../image/testLogo.png" /></a>
		</div>
		<div id="menuButtons">
			<span></span>
			<span></span>
			<span></span>
			<span>프로젝트소개</span>
			<span>이용 방법</span>
			<span>자주 묻는 질문</span>
			<span></span>
			<span><a href="../login/myPage.html"><img src="../image/my.png" width="30" height="30"></a></span>
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

<div id="wrap">
	<div id="frame">
		<h1>마이페이지</h1>
		<label id="info" style="border-bottom: 1px solid gray;">수정 페이지</label><br>
		<div class="infoDiv"><label>NAME&emsp;</label><input type="text" readonly></div>
		<div class="infoDiv"><label>USER NAME&emsp;</label><input type="text"></div>
		<div class="infoDiv"><label>PASSWORD&emsp;</label><input type="password"></div>
		<div class="infoDiv"><label>REPASSWORD&emsp;</label><input type="password"></div>
		<div class="infoDiv"><label>EMAIL&emsp;</label><input type="text"></div>
		<div class="infoDiv"><label>PHONE&emsp;</label><input type="text"></div>
		<div class="infoDiv"><label>BIRTH&emsp;</label><input type="date"></div>
	</div>
	
</div>
<div id="buttonArea" align="center">
	<button id="update">UPDATE</button>
	<button id="back" onclick="location='javascript:history.go(-1)'">BACK</button>
</div>
</body>
</html>