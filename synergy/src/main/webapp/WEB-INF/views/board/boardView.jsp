<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta id="_csrf" name="_csrf" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="../resources/css/welcome.css">
<link rel="stylesheet" href="../resources/css/boardView.css">


</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>

	<div class="bodywrapper">
        <div class="boardwrapper">
            <h1>${cBoardDTO.topic }</h1>
            <div class="boardcontainer">                
                <div class="board_header">
                    <div class="header_upside">
                        <div class="view_bno">${cBoardDTO.bno }</div>
                        
                        <div class="view_title">${cBoardDTO.title }</div>
                    </div>
                    <div class="header_downside">
                        <div class="downside_left">                                                   
                            <div class="view_nickname">${cBoardDTO.nickname }&emsp;</div>
                            <div class="view_boarddate"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${cBoardDTO.boarddate }"/></div>
                            <button type="button" class="origin" onclick="window.open('https://okky.kr/article/${cBoardDTO.bno }')">출처</button>
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
                    <div class="replywrapper">
                        <div class="reply_header">댓글 ${cBoardDTO.replys }개</div>
	                        
	                        <ul class="reply_group">
	                            <!-- <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">
	                                	<button type="button" class="replyBtn1">수정</button>
				                        <button type="button" class="replyBtn2">삭제</button>
	                                </div>
	                            </li> -->
	                            
	                            <c:forEach var="replydto" items="${replyList }" varStatus="status">
		                            	<c:if test="${not empty replydto }">
		                            		<li class="reply_group_item">
		                            			<%-- <input type="hidden" class="reply_rno" value="${replydto.rno }"> --%>
				                                <div class="reply_nickname">${replydto.nickname }</div>
				                                <div class="reply">${replydto.reply }</div>
				                                <div class="reply_button">
				                                	<button type="button" class="modifyBtn">수정</button>
				                                	<button type="button" class="deleteBtn" data-rno="${ replydto.rno }">삭제</button>
				                                </div>
				                            </li>
		                            	</c:if>
	                            </c:forEach>
	                        </ul>
	                        
	                    <%-- <c:if test="${not empty session }"> --%>
	                    <!-- <div class="reply_writer_wrapper">
							<div class="reply_writer">
								<label class="reply_writer_label">
									댓글 쓰기
								</label>
								<div class="reply_writer_div">
									<textarea id="reply_writer_text"></textarea>
									<button type="submit" id="reply_writer_btn" >등록</button>
								</div>
							</div>
						</div> -->
	                    <%-- </c:if> --%>
	                        
	                    
	                    <form id="reply_write_form" method="post" action="/board/replyWrite">
	                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">    
						<div class="reply_writer_wrapper">
							<div class="reply_writer">
								<label class="reply_writer_label">
									댓글 쓰기
								</label>
								<div class="reply_writer_div">
									<textarea id="reply_writer_text"></textarea>
									<button type="submit" id="reply_writer_btn" data-bno="${cBoardDTO.bno }">등록</button>
								</div>
							</div>
						</div>
						</form> 
						
						
                    </div>                    
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="boardList1.jsp" flush="false"/>
    <jsp:include page="footer.jsp" flush="false"/>
    
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="../resources/js/welcome.js" defer></script>
	<script src="../resources/js/boardView.js" defer></script>
</body>
</html>