<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅룸 리스트</title>
<style type="text/css">
.content{
    width: 1024px;
    display: grid;
    grid-template-columns: 3fr 7fr;
}

div{
	border : 1px solid black;
}
</style>
</head>
<body>
<%-- <jsp:include page="../template/header.jsp"/> --%>
<div class="wrapDiv" align="center">
    <div class="content">
        <div id="roomList">
            <div>채팅방 리스트</div>
            <div id="allChat">전체 채팅방</div>
        </div>
        
        <div id="room">
            <div>채팅방 영역</div>
            <div><img src="/resources/image/home.jpg"></div>
            <!-- <jsp:include page="chatting.jsp"/> -->
        </div>
    </div>
</div>
<input type="button" value="채팅방 생성" name="createChat" id="createChat">
<%-- <jsp:include page="../template/footer.jsp"/> --%>
<sec:authentication property="principal.username" var="username"/>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
let csrfHeaderName = "${_csrf.headerName}";
let csrfTokenValue = "${_csrf.token}";
$(document).ready(function(){
	$.ajax({
		type : 'post',
		beforeSend: function(xhr){
    		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
    		
    	},
		url  : '/member/getChattingRoom',
		data : 'username=' + '${username}',
		dataType : 'json',
		success : function(data){
			$.each(data.list, function(index, items){
				$('<div/>',{
					text : items.chattingRoom
				}).appendTo($('#roomList'));
			});
		}
	});
});

$('#createChat').click(function(){
	$.ajax({
		type : 'get',
		url  : '/member/createChat',
		success : function(){
			$('<div/>', {
				text : '새 채팅방'
			}).appendTo($('#roomList'));
		}

	});
});

//==================================== 채팅방 입장


</script>
</html>