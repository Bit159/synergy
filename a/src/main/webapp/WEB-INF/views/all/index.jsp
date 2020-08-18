<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head_include.jsp"%>
<script async defer src="/resources/js/index.js"></script>
<title>Synergy</title>

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
<meta name="google-signin-scope" content="profile email" />
<meta name="google-site-verification"
	content="fb6rIsh8WzJKvF5SCARFAzqdWF95ZEKdhPfXX2lLTzw" />
<meta name="google-signin-client_id"
	content="752749290235-0lrjurm4fdk31il80d87i99knklc9650.apps.googleusercontent.com" />
<script src="https://apis.google.com/js/platform.js" async defer></script>
</head>

<body>
	<%@ include file="../include/nav_include.jsp"%>
	<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
	<script>
		function onSignIn(googleUser) {
			// Useful data for your client-side scripts:
			var profile = googleUser.getBasicProfile();
			console.log("ID: " + profile.getId()); // Don't send this directly to your server!
			console.log("Full Name: " + profile.getName());
			console.log("Given Name: " + profile.getGivenName());
			console.log("Family Name: " + profile.getFamilyName());
			console.log("Image URL: " + profile.getImageUrl());
			console.log("Email: " + profile.getEmail());

			// The ID token you need to pass to your backend:
			var id_token = googleUser.getAuthResponse().id_token;
			console.log("ID Token: " + id_token);
		}
	</script>
	<div id="firstBanner">
		<img src="/resources/image/firstBanner.svg" id="firstBannerImage">
		<span id="slogan">내 생애 첫 스터디는<br>Synergy와 함께!
		</span>
		<button type="button" class="indexButton" id="loginButton">로그인</button>
		<button type="button" class="indexButton" id="joinButton">회원가입</button>
	</div>


	<div>
		<img src="/resources/image/firstBanner.svg"> <span id="slogan">내
			생애 첫 스터디는<br>Synergy와 함께!
		</span>
	</div>
	<a id="copyright" href="https://stories.freepik.com/technology">Illustration
		by Freepik Stories</a>

</body>
</html>
