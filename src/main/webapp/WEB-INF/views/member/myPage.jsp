<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/myPage.css">
</head>
<body>
<div class="nav">
    <nav>
        <div id="logoDiv">
            <a href="/synergy/index"><img src="../resources/image/testLogo.png" /></a>
        </div>
        <div id="menuButtons">
            <span></span>
            <span></span>
            <span>프로젝트소개</span>
            <span>이용 방법</span>
            <span>자주 묻는 질문</span>
            <span>로그인</span>
            <span>회원가입</span>
            <span></span>
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
</html>