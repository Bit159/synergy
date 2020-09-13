<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
    
<link rel="stylesheet" href="../resources/css/header.css">
<div id="loginBar_wrapper">
	<div id="loginBar" class="container">
		<sec:authorize access="isAnonymous()">
   			<a href="/all/joinForm">회원가입</a>
   			<a href="/all/loginForm">로그인</a> 
  		</sec:authorize>
  		
   		<sec:authorize access="isAuthenticated()">
   			<form action="/logout" method="post" name="logout">
   				<a href="javascript:document.logout.submit();">로그아웃</a>
   				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
   			</form>
   			
   			<form action="/member/myPage" method="post" name="myPage">
   				<a href="javascript:document.myPage.submit();">마이페이지</a>
				<sec:authentication property="principal.username" var="username"/>
				<input type="hidden" value="${username }" id="username" name="username" >
   				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
   			</form>
		</sec:authorize>
	</div>
</div>
<div id=header_wrapper>
	<header id="header" class="container">
		<!-- 로고 -->
		<div id="logo">
			<a href="/all/welcome"><img style="width:155px;height:55px;" src="../resources/image/logo.png"/></a>
		</div>
		<!-- nav -->
		<nav id="nav">
			<ul>
				<li class="welcome" style="white-space: nowrap;">
					<a href="/all/welcome">Welcome</a>
				</li>
				
				<li class="matching" style="white-space: nowrap;">
					<a href="/member/recruitGroup">스터디 매칭</a>
				</li>
				
				<li class="menu1" style="white-space: nowrap;">
					<a href="/member/createGroup">스터디 모집</a>
				</li>
				
				<li class="chatting" style="white-space: nowrap;" >
					<a href="/member/chattingList">채팅</a>
				</li>
				
				<li class="boardList" style="white-space: nowrap;">
					<a href="/all/boardList">게시판</a>
				</li>
				
				<li class="menu2" style="white-space: nowrap;">
					<a href="#">메뉴</a>
				</li>
				
				<li class="menu3" style="white-space: nowrap;">
					<a href="#" >문의</a>
				</li>
			</ul>
		</nav>
	</header>
</div>