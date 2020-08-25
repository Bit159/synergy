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
<script defer type="text/javascript" src="/resources/js/chattingList.js"></script>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>
<%-- <jsp:include page="../template/header.jsp"/> --%>
<div class="wrapDiv" align="center">
    <div class="content">
        <div id="roomList">
            <div>채팅방 리스트</div>
            <div id="chattingRoom" onclick="getChatting(chattingRoom);connect();">전체 채팅방</div>
        </div>
        
        <div id="room">
            <div>채팅방 영역</div>
            <div id="messages"><img id="home" src="/resources/image/home.jpg"></div>
        </div>
    </div>
    <div>
    	<input type="text" size="100" id="messageInput">
    	<input type="button" value="보내기" id="sendBtn">
    	<input type="hidden" value="${username }" id="sender">
    </div>
</div>
<input type="button" value="채팅방 생성" name="createChat" id="createChat">
<%-- <jsp:include page="../template/footer.jsp"/> --%>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/resources/js/sockjs.min.js"></script>
<script type="text/javascript" src="/resources/js/stomp.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script> -->
<script type="text/javascript">
let csrfHeaderName = "${_csrf.headerName}";
let csrfTokenValue = "${_csrf.token}";
let messages = document.getElementById("messages");
let chattingRoomNum = chattingRoom.id;
let username = '${username}'
let stompClient = null;
//======================================================== 채팅방 리스트 가져오기

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
					id : items.chattingRoom,
					onclick : 'getChatting(' + items.chattingRoom + '); connect();',
					text : items.chattingRoom
				}).appendTo($('#roomList'));
			});
		}
	});
});

//======================================================== 채팅방 생성

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

//======================================================== 채팅방 정보 가져오기

function getChatting(chattingRoom){
	$('div.message').remove();
	$.ajax({
		type : 'post',
		beforeSend: function(xhr){
    		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
    	},
		url  : '/member/getChatting',
		data : 'chattingRoom=' + chattingRoom.id,
		dataType : 'json',
		success : function(data){
			$('#home').css('display', 'none');
			$('#sendBtn').attr('onclick', 'send(' + chattingRoom.id + ')');
			$.each(data.list, function(index, items){
				messages.innerHTML += '<div class="message" align="left">' + items.username + " : " + items.chat + '</div>';
			});
		}
	});
}

//======================================================== Stomp, SockJS 

function connect(){
	let socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(){
		stompClient.subscribe('/topic/' + chattingRoom.id, onMessageReceived); 
		/* 
		첫번째 매개변수는 구독할 주소를 말하고
		두번째 매개변수는 메시지를 받았을 때 수행할 메소드를 넣으면 된다.
		*/
	});
}

function send(data){
	let chat = document.getElementById('messageInput').value;
	
	stompClient.send("/app/message", {}, JSON.stringify({'username' : username, 'chat' : chat, 'chattingRoom' : data.id }))
	
	document.getElementById("messageInput").value="";
	
	/*
	첫번째 인자는 spring controller mapping("/app"은 spring controller로 보낸다는 stomp prefix 규칙이다. 즉 /app 뒤가 진짜 mapping 주소)
	두번째 인자는 서버로 보낼 때 추가하고싶은 stomp 헤더이다.
	세번째 인자는 서버로 보낼 때 추가하고 싶은 stomp 바디이다. 서버 컨트롤러에서는 mapping 된 함수의 string 인자로 json stringify된 문자열을 받을 수 있다.
	*/
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function onMessageReceived(payload){
	console.log(payload.chat);
	let message = JSON.parse(payload.body);
	
	messages.innerHTML+="<div class='message' align='left'>" + message.username + " : " + message.chat + "</div>";
}


</script>
</html>