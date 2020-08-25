<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authentication property="principal.username" var="username"/>
<div id="messages" align="left"></div>
<div align="center">
    <input type="hidden" id="sender" value="${username }">
    <input type="text" id="messageinput">
    <button type="button" onclick="send();">Send</button>
    <button type="button" onclick="closeSocket();">Close</button>
</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
let ws;
let messages = document.getElementById("messages");

window.onload=function openSocket(){
    if(ws!==undefined && ws.readyState!==ws.CLOSED){
        writeResponse("WebSocket is already opened.");
        return;
    }
    //웹소켓 객체를 만든다.
    ws=new WebSocket("ws://localhost:8080/chat");
    
    ws.onopen=function(event){
        if(event.data===undefined) return;
        
        writeResponse(event.data);
    };
    
    ws.onmessage=function(event){
        writeResponse(event.data);
    };
    
    ws.onclose=function(event){
        writeResponse("Connection closed");
    }
}

function send(){
    let text = document.getElementById("messageinput").value + "," + document.getElementById("sender").value;
    ws.send(text); 
    $.ajax({
    	type : 'post',
    	url  : '/member/sendMessage',
		beforeSend: function(xhr){
    		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
    		
    	},
    	data : 'text=' + text,
    	success : function(){
    	}
    });
    text = "";
    document.getElementById("messageinput").value="";
}

function closeSocket(){
	ws.close();
}

function writeResponse(text){
    messages.innerHTML+="<br/>"+text;
}

//==========================================================db 가져오기
/* $(document).ready(function(){
	$.ajax({
		type : 'get',
		url  : '/member/getChatting',
		dataType : 'json',
		success : function(data){
			$.each(data.list, function(index, items){
				 messages.innerHTML+="<br/>"+ items.username + " : " + items.chat;
			});
		}
		
	});
	
}); */
</script>
</html>