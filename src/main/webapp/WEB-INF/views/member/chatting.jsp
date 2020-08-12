<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div>
        <input type="text" id="sender" value="뱅주" style="display: none;">
        <input type="text" id="messageinput">
    </div>
    <div>
        <button type="button" onclick="send();">Send</button>
        <button type="button" onclick="closeSocket();">Close</button>
    </div>
 <div id="messages"></div>
</body>
<script type="text/javascript">
let ws;
let messages = document.getElementById("messages");

window.onload=function openSocket(){
    if(ws!==undefined && ws.readyState!==ws.CLOSED){
        writeResponse("WebSocket is already opened.");
        return;
    }
    //웹소켓 객체를 만든다.
    ws=new WebSocket("ws://localhost:8080/synergy/chat");
    
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
    text = "";
    document.getElementById("messageinput").value="";
}

function closeSocket(){
	ws.close();
}

function writeResponse(text){
    messages.innerHTML+="<br/>"+text;
}
</script>
</html>