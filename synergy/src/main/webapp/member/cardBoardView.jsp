<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
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
                	<ul>
                	<c:forEach var="dto" items="${replyList}">
                	<li>
                		<div id= >
                		</div>
                		<div id="replyContent">
                			<p>${dto.reply}</p>
                		</div>
                	</li>
                	</c:forEach>
                	</ul>
                </div>
            </div>
		<jsp:include page="/template/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
// $(document).ready(function(){
// 	$.ajax({
// 		type:'get',
// 		url:'synergy-kh/member/getReplyList',
// 		data: 'seq='+$('#boardSeq').val(),
// 		success:function(){
			
// 		},
// 		error:function(){
// 			alert('댓글에러남')
// 		}
// 	});
// });
</script>
</body>
</html>