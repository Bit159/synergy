<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Let's Synergy!</title>
    <link rel="shortcut icon" href="../resources/image/logo.png" />
    <link rel="stylesheet" href="../resources/css/index.css" />
    <script defer type="text/javascript" src="../resources/js/index.js"></script>
	<script defer src="https://apis.google.com/js/platform.js"></script>
	<script defer type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
      </head>
  <body>
    <div class="nav">
      <nav>
        <div id="logoDiv">
          <a href="/synergy/index"><img src="../resources/image/logo.png" /></a>
        </div>
        <div id="menuButtons">
          <span></span>
          <span>프로젝트소개</span>
          <span>이용 방법</span>
          <span>자주 묻는 질문</span>
          <sec:authorize access="isAnonymous()">
		      <span>로그인</span>
		      <span>회원가입</span>
          </sec:authorize>
          <sec:authorize access="isAuthenticated()">
          	   <span>로그아웃</span>
          </sec:authorize>
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

    <div class="firstBanner">
      <div class="container" id="firstContent">
        <section class="banner1">
        	<sec:authorize access="isAnonymous()">
				<input type="button" value="LOGIN" class="buttons" id="loginButton" onclick="location='/synergy/all/loginForm'">
				<input type="button" value="JOINUS"class="buttons" onclick="location='/synergy/all/joinForm'">
			</sec:authorize>
			
			<%-- <sec:authorize access="isAuthenticated()"> --%>
				<form action="/synergy/logout" method="post">
					<input type="submit" value="LOGOUT" class="buttons" id="logoutButton">
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
				</form>
			<%-- </sec:authorize> --%>

        </section>
      </div>
    </div>

    <div class="secondBanner">
      <div class="container" id="secondContent">
        <section class="banner2"></section>
      </div>
    </div>

    <div class="thirdBanner">
      <div class="container" id="thirdContent">
        <section class="banner3"></section>
      </div>
    </div>

    <div class="fourthBanner">
      <div class="container" id="fourthContent">
        <section class="banner4"></section>
      </div>
    </div>

    <div class="fifthBanner">
      <footer>
        <div>
          <ul>
            <li><strong>서비스</strong></li>
            <li>회사</li>
            <li>문의</li>
            <li>고객센터</li>
            <li>이메일</li>
          </ul>
        </div>
        <div>
          <ul>
            <li><strong>공지사항</strong></li>
            <li>회사소개</li>
            <li>채용</li>
            <li>블로그</li>
          </ul>
        </div>
        <div>
          <ul>
            <li><strong>문의</strong></li>
            <li>사업 제휴</li>
            <li>마케팅</li>
            <li>IR</li>
          </ul>
        </div>
        <div>
          <ul>
            <li><strong>메뉴메뉴</strong></li>
            <li>메뉴1</li>
            <li>메뉴2</li>
            <li>메뉴3</li>
            <li>메뉴4</li>
          </ul>
        </div>
      </footer>
    </div>

    <div class="mobileMenu">
      <ul>
        <li>프로젝트소개</li>
        <li>이용 방법</li>
        <li>자주 묻는 질문</li>
        <sec:authorize access="isAnonymous()">
	        <li>로그인</li>
	        <li>회원가입</li>	
        </sec:authorize>
      </ul>
    </div>

	<input type="button" value="맨위로" id="topButton">
<!-- <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
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
		    			$.ajax({
		    				type : 'post',
		    				beforeSend: function(xhr){
		    		    		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		    		    		
		    		    	},
		    				data : {
		    					'username' : profile.getEmail(),
		    					'password' : 'bitcamp159'
		    					
		    				},		
		    				url  : '/synergy/login',
		    				success : function(){
		    					alert('로그인 성공');
		    				}
		    			});
		    			
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
		</script>  -->	
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
