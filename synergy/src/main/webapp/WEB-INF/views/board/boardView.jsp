<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="../resources/css/welcome.css">
<link rel="stylesheet" href="../resources/css/boardView.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../../resources/js/welcome.js" defer></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>

	<div class="bodywrapper">
        <div class="boardwrapper">
            <h1>${cBoardDTO.topic }</h1>
            <div class="boardcontainer">                
                <div class="board_header">
                    <div class="header_upside">
                        <div class="view_bno">#${cBoardDTO.bno }</div>
                        <div class="view_title">${cBoardDTO.title }</div>
                    </div>
                    <div class="header_downside">
                        <div class="downside_left">                                                   
                            <div class="view_nickname">${cBoardDTO.nickname }&emsp;</div>
                            <div class="view_boarddate"><fmt:formatDate pattern="yyyy-MM-dd" value="${cBoardDTO.boarddate }"/></div>
                        </div>
                        <div class="downside_right">
                            <div class="view_replys">댓글수 : ${cBoardDTO.replys }</div>
                            <div class="view_hit">조회수 : ${cBoardDTO.hit }</div>   
                        </div>               
                    </div>
                </div>
                <div class="board_body">
                    <div class="content">${cBoardDTO.content }</div>
                </div>
                <div class="board_footer">
                    <div class="reply">댓글</div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="boardList1.jsp" flush="false"/>
    
    <jsp:include page="footer.jsp" flush="false"/>
</body>
</html>