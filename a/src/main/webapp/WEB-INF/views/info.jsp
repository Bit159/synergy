<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="include/head_include.jsp"%>
<script async defer src="/a/resources/js/index.js"></script>
<title>Synergy1
</title>

<style>
div[id="firstBanner"] {
	width: 1024px;
	height: 720px;
	margin: 0 auto;
	margin-top: 50px;
	border: 1px solid black;
}

div[id="firstBanner"]>img[id="firstBannerImage"] {
	width: 90%;
	height: 90%;
	left: -15%;
	position: relative;
	float: left;
}

div[id="firstBanner"]>span[id="slogan"] {
	position: absolute;
	top: 28%;
	left: 62%;
	font-size: 1.8rem;
	font-weight: 600;
	color: #32be78;
}

.indexButton {
	background-color: #32be78;
	border-radius: 15px;
	color: white;
	width: 200px;
	height: 50px;
	display: block;
	font-size: 1.1rem;
	font-weight: 800;
	position: absolute;
}

.indexButton:hover {
	cursor: pointer;
}

button[id="loginButton"] {
	top: 45%;
	left: 65%;
}

button[id="joinButton"] {
	top: 52%;
	left: 65%;
}

@media ( max-width :1024px) {
	div[id="firstBanner"] {
		width: 100%;
	}
	img[id="firstBannerImage"] {
		width: 20%;
		height: 20%;
	}
}

@media ( max-width :768px) {
	img[id="firstBannerImage"] {
		position: absolute;
		top: -18%;
		left: 10%;
		width: 20%;
		height: 20%;
	}
	span[id="slogan"] {
		clear: both;
	}
	.indexButton {
		clear: both;
	}
}
</style>
</head>

<body>
	<%@ include file="include/nav_include.jsp"%>

	<div id="firstBanner">
		<img src="/a/resources/image/firstBanner.svg" id="firstBannerImage">
		<span id="slogan">내 생애 첫 스터디는<br>Synergy와 함께!
		</span>
		<button type="button" class="indexButton" id="loginButton">로그인</button>
		<button type="button" class="indexButton" id="joinButton">회원가입</button>
	</div>


	<div>
		<img src="/a/resources/image/firstBanner.svg"> <span
			id="slogan">내 생애 첫 스터디는<br>Synergy와 함께!
		</span>
	</div>
	<a id="copyright" href="https://stories.freepik.com/technology">Illustration
		by Freepik Stories</a>

</body>
</html>
