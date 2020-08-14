<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/myPage.css">
<style type="text/css">
.modal {
    display: none;
    position: fixed;
    z-index: 4;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0,0,0);
    background-color: rgba(0,0,0,0.4);
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 30%;      
}

/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>
</head>
<body>
<div class="nav">
    <nav>
        <div id="logoDiv">
            <a href="/synergy/index"><img src="../resources/image/logo.png" /></a>
        </div>
        <div id="menuButtons">
			<span></span>
            <span>프로젝트소개</span>
            <span>이용 방법</span>
            <span>자주 묻는 질문</span>
            <span><img src="../resources/image/chatting.png" width="30" height="30"></span>
            <span><a href="#"><img src="../resources/image/message.png" width="30" height="30"></a></span>
            <span><a href="/synergy/member/myPage_Update"><img src="../resources/image/my.png" width="30" height="30"></a></span>
        </div>
        <div id="menuButtonsM">
            <a href="javascript:mobileMenu()">
                <div class="line"></div>
                <div class="line"></div>
                <div class="line"></div>
            </a>
        </div>
    </nav>
</div>

<sec:authentication var="user" property="principal.username"/>
<div id="myModal" class="modal">
	<div class="modal-content">
		<div id="messages"></div>
	
	   	<span class="close">&times;</span>                                                               
	   	<input type="hidden" id="sender" value="${user }">
	   	<input type="text" name="messageinput" id="messageinput" size="70%">
	   	
	   	<div class="modal_footer">
	   		<input type="button" value="Send" onclick="send()">
	   		<input type="button" value="Close" onclick="closeSocket()">
	   	</div>
 	</div>
</div>
<section class="myPage_Form">
    <h1>My Page</h1>
    <form method="post" action="/synergy/logout">
        <div class="myInfo-area">
            <aside id="profile"><img src="../resources/image/profile.png" width="50%"></aside>
            <section id="myInfo">
                <div class="infoDiv">
                    <label>이 름 : </label>
                    <label class="infoLabel">양병주</label>
                </div>
            </section>
            <footer id="pr">ㅋㅋ</footer>
        </div>
		
		<div class="btn-area">
			<button type="button" name="updateForm" id="updateForm" onclick="location='/synergy/member/myPage_Update'">UPDATE</button>
			<button type="button" name="back" id="back" onclick="location='javascript:history.go(-1)'">BACK</button>
			<button name="logout" id="logout">LOGOUT</button>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</div>
    </form>
   	<img id="chatting" src="../resources/image/chatting_floating.png" width="100" height="100"
		 style="position:fixed;
		   		top: 700px;
		  		right : 50%;
		  		margin-right: -900px;
		  		cursor:pointer;
		  		z-index : 99;">
</section>
</body>
<script type="text/javascript">
// Get the modal
let modal = document.getElementById('myModal');

// Get the button that opens the modal
let chatting = document.getElementById("chatting");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];                                          

// When the user clicks on the button, open the modal 
chatting.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

//==============================================채팅 스크립트

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
    console.log(text);
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