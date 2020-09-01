<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta id="_csrf" name="_csrf" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/cardBoardView.css">
</head>
<body>
	<div class="body-wrapper">
	<jsp:include page="/template/header.jsp"></jsp:include>
		 <div class="view-container">
                <div class="cardView">
                    <div id="card-title">
                        <h1>${dto.title}</h1>
                        <fmt:formatDate var="registDate" pattern="yyyy-MM-dd HH:mm" value="${dto.registDate }"/>
                        <span id="writer">작성자 ${dto.nickname}</span>
                        <span id="registDate">등록일 ${registDate}</span>
                        <input type="hidden" id="boardSeq" value="${dto.seq}">
                    </div>
                    <div id="view-content">
                        <h1>모집내용</h1>
                        <table id="viewTable">
                        <tr>
                        <th>주제</th>
                        <td>${dto.topic }</td>
                        </tr>
                        <tr>
                        <th>지역</th>
                        <td>${dto.location }</td>
                        </tr>
                        <tr>
                        <th>인원</th>
                        <td>${dto.people }명</td>
                        </tr>
                        </table>
                        <div id="card-content">
                        	<h1>상세내용</h1>
                        	<pre style="white-space: pre-line;">${dto.content}</pre>
                        </div>
                    </div>
                </div>
                <div class="view-reply">
                	<ul class="reply_group">
 					<c:forEach var="replydto" items="${replyList }">
                   	<c:if test="${not empty replydto }">
                    	<li class="reply_group_item">
                   		<div class="reply_group_div">
                    		<%-- <input type="hidden" class="reply_rno" value="${replydto.rno }"> --%>
                          <div class="reply_nickname">${replydto.nickname }</div>
                          <fmt:formatDate var="regDate" pattern="yyyy-MM-dd HH:mm:ss" value="${replydto.regDate }"/>
                          <fmt:formatDate var="editDate" pattern="yyyy-MM-dd HH:mm:ss" value="${replydto.editDate }"/>
                          <div id="reply_regDate">${regDate}</div>
                          <div id="reply_editDate" >${editDate} 수정</div>
                          <div class="reply">${replydto.reply }</div>
                          <c:if test="${replydto.nickname eq nickname} ">
                          <div class="reply_button">
                          	<button type="button" id="modifyReplyBtn" data-rseq="${ replydto.rseq }">수정</button>
                          	<button type="button" id="deleteReplyBtn" data-rseq="${ replydto.rseq }">삭제</button>
                          </div>
                          </c:if>
                     	  <div class="reply_modify_wrapper">
                          <div class="reply_modify">
<!--                               <label class="reply_modify_label">댓글 수정</label> -->
                              <div class="reply_modify_div">
                                  <textarea name="reply_modify_text" id="reply_modify_text">${replydto.reply }</textarea>
                                  <div class="reply_modify_button_div">
                                      <button id="reply_modify_button" data-rseq="${ replydto.rseq }">수정</button>
                                      <button id="reply_modify_cancel">취소</button>
                                  </div>
                              </div>
                          </div>
                      	</div>
                     </div>
               		</li>
                   	</c:if>
                  </c:forEach>
                </ul>
                <div class="reply_write">
                	<div class="reply_write_item">
                	<textarea name="reply" id="reply" rows="3"></textarea>
                	<div class="reply_write_button">
                		<button type="button" id="reply_write_regist">등록</button>
                	</div>
                	</div>
                </div>
                </div>
            </div>
		<jsp:include page="/template/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$('#reply_write_regist').click(function(){
	$.ajax({
		type:'get',
		url:'/synergy-kh/member/writeReply',
		data:{'reply':$('#reply').val(),'seq':$('#boardSeq').val()},
		success:function(){
			location.reload();
		},
		error:function(){
			alert('다시 시도해 주세요')
		}
	});
});
$('.reply_button').on('click','#deleteReplyBtn',function(){
	let rseq = $(this).data('rseq')
	$.ajax({
		type:'get',
		url:'/synergy-kh/member/deleteReply',
		data:'rseq='+rseq,
		success:function(){
			location.reload();
		},
		error:function(){
		}
	});
})
$('.reply_button').on('click','#modifyReplyBtn',function(){
	$(this).parent().parent().children('.reply_modify_wrapper').css('display','block')
});
$('.reply_modify_button_div').on('click','#reply_modify_button',function(){
// 	$(this).parent().parent().parent().parent().parent().children('#reply_editDate').css('display','block');
	let rseq = $(this).data('rseq');
	reply = $(this).parent().parent().children('textarea').val();
	$.ajax({
		type:'get',
		url:'/synergy-kh/member/modifyReply',
		data:{'rseq':rseq,'reply':reply},
		success:function(){
			location.reload();
		},
		error:function(){
		}
	});
});
$('.reply_modify_button_div').on('click','#reply_modify_cancel',function(){
	$(this).parent().parent().parent().parent().css('display','none');
	$(this).parent().parent().children('textarea').val('');
});
</script>
</body>
</html>