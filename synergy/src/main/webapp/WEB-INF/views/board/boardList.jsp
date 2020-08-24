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
<link rel="stylesheet" href="../resources/css/boardList.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../resources/js/welcome.js" defer></script>

</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>
	
	<div class="body_wrapper">
        <div class="body_container">
            <h1>외부게시판</h1>
            <div class="list_wrapper">
                <ul class="list_group">
                    <li class="list_group_item">
                        <div class="bno">글번호</div>
                        <div class="topic">말머리</div>
                        <div class="title" id="titleHeader">제목</div>                        
                        <div class="nickname">작성자</div>
                        <div class="boarddate">작성시간</div>
                        <div class="hit">조회수</div>
                    </li>
                    
                    <c:forEach var="dto" items="${list }">
                    	<li class="list_group_item">
                    		<div class="bno"><c:out value="${dto.bno}" /></div>
	                        <div class="topic"><c:out value="${dto.topic}" /></div>
	                        <div class="title"><a id="titleA" href="/synergy/board/${dto.bno }"><c:out value="${dto.title}" /></a></div>                        
	                        <div class="nickname"><c:out value="${dto.nickname}" /></div>
	                        <div class="boarddate">
	                        	<fmt:formatDate var="nowdate" pattern="yyyy-MM-dd" value="${now }"/>
	                        	<fmt:formatDate var="boarddate" pattern="yyyy-MM-dd" value="${dto.boarddate }"/>
	                        	<fmt:formatDate var="boardtime" pattern="HH:mm:ss" value="${dto.boarddate }"/>
	                        	<c:choose>
	                        		<c:when test="${nowdate eq boarddate }">${boardtime }</c:when>
	                        		<c:otherwise>${boarddate }</c:otherwise>
	                        	</c:choose>
	                        </div>	                        
	                        <div class="hit"><c:out value="${dto.hit}" /></div>
                    	</li>
                    </c:forEach>
                </ul>
            </div>
            
            <ul class="pagination"> 
    			<li><a href="/synergy/board/getBoard?nowpage=0&searchOption=${searchOption}&keyword=${keyword}">&lt;&lt;</a></li> 
       			<!--현재 페이지가 0보다 작아질 경우 이전 버튼을 disabled하는 조건문 --> 
       				<c:choose> 
       					<c:when test="${nowpage <= 0}"> 
       						<li class="disabled"><a href="#">&lt;</a></li> 
       					</c:when> 
      						<c:otherwise> 
      							<li><a href="/synergy/all/getAdminBoard?nowpage=${nowpage-1}&searchOption=${searchOption}&keyword=${keyword}">&lt;</a></li> 
      						</c:otherwise> 
       				</c:choose> 
       				<!--해당하는 페이지로 갈 수 있는 버튼 --> 
       					<c:forEach var="i" begin="0" end="${totalpage}">
 									
								 <li><a href="/synergy/all/getAdminBoard?nowpage=${i}&searchOption=${searchOption}&keyword=${keyword}">${i+1}</a></li> 									
       						 
       					</c:forEach>
       							<%-- <li><a href="/synergy/all/getAdminBoard?nowpage=${i}&searchOption=${searchOption}&keyword=${keyword}">${i+1}</a></li> --%>  		
       				<!--현재 페이지가 totalpage보다 커질 경우 다음 버튼을 disabled하는 조건문 --> 
       				<c:choose> 
       					<c:when test="${nowpage >= totalpage }"> 
       							<li class="disabled"><a href="#">&gt;</a></li> 
       					</c:when> 
       						<c:otherwise> 
       							<li><a href="/synergy/all/getAdminBoard?nowpage=${nowpage+1}&searchOption=${searchOption}&keyword=${keyword}">&gt;</a></li> 
       						</c:otherwise> 
    		   			</c:choose> 
       							<li><a href="/synergy/all/getAdminBoard?nowpage=${totalpage}&searchOption=${searchOption}&keyword=${keyword}">&gt;&gt;</a></li> 
       		</ul>
        		
        		<div class="searchDiv">
	           <form action="/synergy/all/getAdminBoard" method="get">
		            <select name="searchOption" id="searchOption">
		            	<option value="username">아이디</option>
		            	<option value="nickname">닉네임</option>
		            </select>
		            <input type="text" name="keyword" id="keyword">
		            <input type="submit" id="searchBtn" value="검색">
	        	
	        	</form>
	        	</div>
        		
        		            
            
        </div>
    </div>
	
	<jsp:include page="footer.jsp" flush="false"/>
</body>
</html>