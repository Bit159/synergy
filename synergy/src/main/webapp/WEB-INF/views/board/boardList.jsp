<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="../resources/css/boardList.css">
</head>
<body>
	<div id=body_wrapper>
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
					<a href="home.html"><img alt="" style="width:155px;height:55px;" src="../resources/image/logo.png"/></a>
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
						<li class="menu1" style="white-space: nowrap;">
							<a href="#">스터디 모집</a>
						</li>
						<li class="menu2" style="white-space: nowrap;">
							<a href="#">메뉴</a>
						</li>
						<li class="menu3" style="white-space: nowrap;">
							<a href="#" >문의</a>
						</li>
						<li class="menu4" style="white-space: nowrap;">
							<a href="/synergy/board/boardList" >게시판</a>
						</li>
					</ul>
				</nav>
			</header>
        </div>
        <div id="boardListTable_wrapper">
        	<input type="hidden" id="pg" value="${pg }">
        	<table id="boardListTable" border="1" frame="hsides" rules="rows">
				<tr>
					<th width="100">글번호</th>
					<th width="200">제목</th>
					<th width="100">작성자</th>
					<th width="100">작성일</th>
					<th width="100">조회수</th>
				</tr>
			</table>
			<br>
			<div id="boardPagingDiv" style="width: 700px; text-align: center;"></div><br>
			
			<form>
			<input type="hidden" name="pg" value="1">
			<div id="searchOptionDiv" style="width: 650px; text-align: center;">
				<select name="searchOption" id="searchOption" style="width: 100px;">
			 		<option value="subject">제목</option>
			 		<option value="id">작성자</option>
				</select>
			 	<input type="search" name="keyword" id="keyword" value="${requestScope.keyword }">
				<input type="button" id="boardSearchBtn" value="검색">
			</div>
			</form>
        </div>
	</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../resources/js/boardList.js?var=1"></script>
</body>
</html>