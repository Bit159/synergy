<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/welcome.css">
<link rel="stylesheet" href="../resources/css/boardList1.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../resources/js/welcome.js" defer></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>
	
	<div class="body_wrapper">
        <div class="body_container">
            <h1>외부게시판</h1>
            <div class="list_wrapper">
                <ul class="list_group">
                    <li class="list_group_item">
                        <div class="bno">글번호</div>
                        <div class="topic">말머리</div>
                        <div class="title">제목</div>                        
                        <div class="nickname">작성자</div>
                        <div class="boarddate">작성시간</div>
                        <div class="hit">조회수</div>
                    </li>
                    
                    <c:forEach var="dto" items="${list }">
                    	<li class="list_group_item">
                    		<div class="bno"><c:out value="${dto.bno}" /></div>
	                        <div class="topic"><c:out value="${dto.topic}" /></div>
	                        <div class="title"><a id="titleA" href="#"><c:out value="${dto.title}" /></a></div>                        
	                        <div class="nickname"><c:out value="${dto.nickname}" /></div>
	                        <div class="boarddate"><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.boarddate }"/></div>	                        
	                        <div class="hit"><c:out value="${dto.hit}" /></div>
                    	</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>