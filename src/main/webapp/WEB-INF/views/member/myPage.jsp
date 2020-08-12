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
            <span><img src="../resources/image/chatting.png" width="30" height="30" id="chatting"></span>
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

<div id="myModal" class="modal">
	<div class="modal-content">
	   	<span class="close">&times;</span>                                                               
	   	<input type="text" name="messageinput">
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

</script>
</html>