let ws;

/*window.onload=function openSocket(){
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
}*/

/*function send(chattingRoom){
    let chattingRoomNum = chattingRoom.id;
	let text = document.getElementById("messageInput").value + "," + document.getElementById("sender").value;
    ws.send(text); 
    $.ajax({
    	type : 'post',
    	url  : '/member/sendMessage',
		beforeSend: function(xhr){
    		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
    		
    	},
    	data : 'text=' + text + '&chattingRoom=' + chattingRoomNum,
    	success : function(){
    		
    	}
    });
    text = "";
    document.getElementById("messageInput").value="";
}

function closeSocket(){
	ws.close();
}

function writeResponse(text){
    messages.innerHTML+="<div>" + text + "</div>";
}*/