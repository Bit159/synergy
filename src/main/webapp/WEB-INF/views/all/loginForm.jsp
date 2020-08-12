<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-scope" content="profile email" />
<meta name="google-site-verification" content="fb6rIsh8WzJKvF5SCARFAzqdWF95ZEKdhPfXX2lLTzw"/>
<meta name="google-signin-client_id" content="752749290235-0lrjurm4fdk31il80d87i99knklc9650.apps.googleusercontent.com"/>

<title>로그인</title>
<link rel="stylesheet" href="../resources/css/login.css">
<script defer type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer type="text/javascript" src="../resources/js/login.js"></script>
<script defer src="https://apis.google.com/js/platform.js"></script>
</head>
<body>
	<nav class="navbar">
		<div class="logoDiv">
			<a href="/synergy/index"><img id="logoImg" src="../resources/image/logo.png" /></a>
		</div>
		<div class="menuButtons">
			<span><a href="#">프로젝트소개</a></span>
			<span><a href="#">이용 방법</a></span>
			<span><a href="#">자주 묻는 질문</a></span>
			<span><a href="#">로그인</a></span>
			<span><a href="#">회원가입</a></span>
		</div>
			<input type="checkbox" id="sideicon">
			<label for="sideicon">
				<span></span>
				<span></span>
				<span></span>
			</label>
			<div class="sidebar">
					<ul>
						<li><a>프로젝트소개</a></li>
						<li><a>이용방법</a></li>
						<li><a>자주 묻는 질문</a></li>
						<li><a>로그인</a></li>
						<li><a>회원가입</a></li>
					</ul>
					<label for="sideicon"></label>
			</div>
	</nav>
	<section class="login-form">
		<label for="sideicon" id="back"></label>
		<h1>Let's Synergy</h1>
		
		<form id="loginForm" name="loginForm" method="post" action="/synergy/login" >
			<div class="info-area">
				<input type="text" name="username" id="username" autocomplete="off" required>
				<label for="username">EMAIL</label>
			</div>

			<div class="info-area">
				<input type="password" name="password" id="password" autocomplete="off" required>
				<label for="password">PASSWORD</label>
			</div>
			<input type="checkbox" name="remember-me">자동로그인
			<div class="btn-area">
				<button type="button" id="loginBtn" onclick="checkLogin()">LOGIN</button>
				<button type="button" onclick="location='/synergy/index'">BACK</button>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
		
		<div class="thirdParty" align="center" style="margin-top:30px;">
			<a href="${google_url}"><img src="../resources/image/google.png"></a><br>
			<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
		<script>
		let csrfHeaderName = "${_csrf.headerName}";
		let csrfTokenValue = "${_csrf.token}";
		
		function onSignIn(googleUser) {
		    // Useful data for your client-side scripts:
			var profile = googleUser.getBasicProfile();
		    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
		    console.log("Full Name: " + profile.getName());
		    console.log("Given Name: " + profile.getGivenName());
		    console.log("Family Name: " + profile.getFamilyName());
		    console.log("Image URL: " + profile.getImageUrl());
		    console.log("Email: " + profile.getEmail());
		    
	
		    $.ajax({
		    	type : 'post',
		    	url  : '/synergy/all/checkMember',
		    	beforeSend: function(xhr){
		    		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		    		
		    	},
		    	data : 'username=' + profile.getEmail(),
		    	dataType: 'text',
		    	success : function(data){
		    		if(data == 'ok'){
		    			$('#username').val(profile.getEmail());
		    			$('#password').val('bitcamp159');
		    			document.loginForm.submit();
		    			
		    		}else{
		    			alert("없음");
		    			location="/synergy/all/addInfoForm?username=" + profile.getEmail();
		    			
		    		}
		    	}
		    	
		    });
		    
		    	
		    
		    if(profile.getEmail() != ''){
		    	return;
		    }
		
		    // The ID token you need to pass to your backend:
		    var id_token = googleUser.getAuthResponse().id_token;
		    console.log("ID Token: " + id_token);
		    
		  }
		</script>
			<img src="../resources/image/kakao_login_medium_narrow.png"><br>
		</div>

		<div class="caption">
			<a href="/synergy/all/joinForm">회원가입</a>&emsp;
			<a href="">아이디/비밀번호 찾기</a>
		</div>
	</section>
	<a href="#" onclick="signOut();">Sign out</a>
<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>
</body>
</html>