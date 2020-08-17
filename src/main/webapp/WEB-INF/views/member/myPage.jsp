<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyPage</title>
    <link rel="shortcut icon" href="../resources/image/logo.png" />
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
    width: 50%;                         
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
<div id=body_wrapper>
	<jsp:include page="../template/header.jsp"/>
	<!-- 가운데 main 내용 -->
	<div id="content_wrapper">
		<header class="content_header">
        	<div class="header_label">개인정보 관리</div>
		</header>
		<aside class="content_aside"></aside>
        	<section class="content_section">
            	<form name="reviseForm" id="reviseForm" method="post" action="/synergy/member/revise">
            		<table>
                		<tbody>
                    		<tr>
                        	<th class="table_left">아이디</th>
                         	<td class="id_label">${memberDTO.username}</td>
        				</tr>
						<tr>
	            			<th class="table_left">비밀번호</th>
	            				<td class="table_right">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input type="hidden" id="username" name="username" value="${memberDTO.username}">
									<input type="password" name="password" id="password" placeholder="비밀번호 입력">
	                	
			              			<div id="pwdDiv"></div>
							</td>
						</tr>
						
				        <tr>
				            <th class="table_left">비밀번호 재입력</th>
				            <td class="table_right">
				                <input type="password" name="repwd" id="repwd" placeholder="한번 더 입력해 주세요">
				        	</td>
		        		</tr>
			        
			        	<tr>
			           		<th class="table_left_bottom"><span class="nickName_star">*  </span>닉네임</th>
			            	<td class="table_right_bottom">
			                	<input type="text" name="nickname" id="nickname" value="${memberDTO.nickname}">
			                	<span class="nickName_re">닉네임을 입력하세요</span>
			               		<div id="nicknameDiv"></div>
			           		</td>
			        	</tr>        
					</tbody>
				</table>
			</form>
			<div class="buttonDiv">
			    <input type="button" id="reviseBtn" value="수정">
			    <input type="reset" id="resetBtn" value="취소"> 
			</div>
			<div class="withdrawDiv">
	    
		    	<div class="withdrawDiv_label">
		    
			   		<form name="withdrawalForm" id="withdrawalForm" method="post" action="/synergy/member/withdrawal">
			    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			      		<input type="hidden" id="username" name="username" value="${memberDTO.username}">
		        	</form>
		        
					<div class="withdrawBtn"><input type="button" value="회원 탈퇴" id="withdrawBtn"></div>    
				</div>
			</div>
		</section>
		<article class="content_article"></article>
		<footer class="content_footer">
		</footer>
	</div>
	<jsp:include page="../template/footer.jsp"/>
</div>
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

<img id="chatting" src="../resources/image/chatting_floating.png" width="100" height="100" style="position:fixed;
																							   	  top: 700px;
																							  	  right : 50%;
																							  	  margin-right: -900px;
																							  	  cursor:pointer;
																							  	  z-index : 99;">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

//========================================================= 회원정보 수정

$('#reviseBtn').click(function(){

$('#pwdDiv').empty();	
$('#nicknameDiv').empty();
	
let pwd = $('#password').val();
let repwd = $('#repwd').val();
let nickname = $('#nickname').val();

	
	if(pwd != repwd){
		
		$('#pwdDiv').text("비밀번호를 동일하게 입력해 주세요").css("color", "red").css("font-size", "8pt").css("font-weight", "bold");	
		
	}else if(nickname == null || nickname == ""){
	
		$('#nicknameDiv').text("닉네임을 입력해 주세요").css("color", "red").css("font-size", "8pt").css("font-weight", "bold");
	
	}else{
		document.forms[0].submit();	
	}
	
});

//========================================================= 회원탈퇴

$('#withdrawBtn').click(function(){
	
let withdrawal = confirm("정말 회원을 탈퇴 하시겠습니까?");

	if(withdrawal){
		document.forms[1].submit()
	}
});

//============================================================ modal 창

//Get the modal
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
    let text = document.getElementById("messageinput").value + "," + document.getElementById("nickname").value;
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
</body>
</html>