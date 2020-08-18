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
<script src="../resources/js/welcome.js" defer></script>
<script src="../resources/js/boardView.js" defer></script>

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
                            <div class="view_boarddate"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${cBoardDTO.boarddate }"/></div>
                            <button type="button" class="origin" onclick="location.href='https://okky.kr/article/${cBoardDTO.bno }'">출처</button>
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
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            
	                            <li class="reply_group_item">
	                                <div class="reply_nickname">작성자</div>
	                                <div class="reply">댓글</div>
	                                <div class="reply_button">버튼</div>
	                            </li>
	                            <c:forEach var="replydto" items="${replyList }">
		                            	<c:if test="${not empty replydto }">
		                            		<li class="reply_group_item">
				                                <div class="reply_nickname">${replydto.nickname }</div>
				                                <div class="reply">${replydto.reply }</div>
				                                <div class="reply_button">버튼</div>
				                            </li>
		                            	</c:if>
	                            </c:forEach>
	                        </ul>
						<div class="reply_writer_wrapper">
							<div class="reply_writer">
								<label class="reply_writer_label">
									댓글 쓰기
								</label>
								<div class="reply_writer_div">
									<textarea id="reply_writer_text"></textarea>
									<button type="button" id="reply_writer_btn">등록</button>
								</div>
							</div>
						</div>                        
                    </div>                    
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="boardList1.jsp" flush="false"/>
    
    <jsp:include page="footer.jsp" flush="false"/>
</body>
</html>