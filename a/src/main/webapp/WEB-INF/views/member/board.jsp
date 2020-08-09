<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
	<head>
		<%@ include file="../include/head_include.jsp"%>
		<title>게시판</title>
		<style>
			tr, td{
				margin:0;
				padding:0;
				text-align: center;
				border-bottom: 1px solid black;
			}
		</style>
	</head>

	<body>
		<%@ include file="../include/nav_include.jsp"%>
		<table style="width:70%; height:200px; margin:0 auto; margin-top:50px;">
			<tr><td>번호</td><td>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>
			<tr><td>4</td><td>신논현역 면접 스터디 급구</td><td>김근형</td><td>15:30:22</td><td>33</td></tr>
			<tr><td>3</td><td>강남역 알고리즘 스터디 충원합니다</td><td>양병주</td><td>2020.07.30</td><td>42</td></tr>
			<tr><td>2</td><td>언주역 스프링 스터디</td><td>한세진</td><td>2020.07.28</td><td>22</td></tr>
			<tr><td>1</td><td>주말 모각코 구합니다</td><td>정하진</td><td>2020.07.27</td><td>43</td></tr>
		</table>
		<a href="/a/mylogout">로그아웃</a>
	</body>
	
</html>
