<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/welcome.css">
</head>
<body>
	<div id="loginBar_wrapper">
        <div id="loginBar" class="container">
            <a href="#">회원가입</a>
            <a href="#">로그인</a>
        </div>
    </div>
	<div id=header_wrapper>
	    <header id="header" class="container">
	        <!-- 로고 -->
	        <div id="logo">
	            <a href="home.html"><img alt="" style="width:155px;height:55px;" src="../resources/image/logo.png" /></a>
	        </div>
	        <!-- nav -->
	        <nav id="nav">
	            <ul>
	                <li class="welcome" style="white-space: nowrap;">
	                    <a href="home.html">Welcome</a>
	                </li>
	                <li class="matching" style="white-space: nowrap;">
	                    <a href="#">스터디 매칭</a>
	                </li>
	                <li class="grpup" style="white-space: nowrap;">
	                    <a href="#">스터디 모집</a>
	                </li>
	                <li class="menu1" style="white-space: nowrap;">
	                    <a href="boardList">게시판</a>
	                </li>
	                <li class="menu2" style="white-space: nowrap;">
	                    <a href="#">메뉴</a>
	                </li>
	                <li class="menu3" style="white-space: nowrap;">
	                    <a href="#">문의</a>
	                </li>	                
	            </ul>
	        </nav>
	        <input type="checkbox" id="menuicon">
	        <label for="menuicon">
	            <span></span>
	            <span></span>
	            <span></span>
	        </label>
	        <!-- <div class="sidebar">
	            <ul>
	                <li><a>Welcome</a></li>
	                <li><a>스터디 매칭</a></li>
	                <li><a>스터디 모집</a></li>
	                <li><a>그룹관리</a></li>
	                <li><a>메뉴</a></li>
	                <li><a>문의</a></li>
	            </ul>
	        </div> -->
	    </header>
	</div>
</body>
</html>