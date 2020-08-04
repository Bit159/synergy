<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Let's Synergy!</title>
    <link rel="shortcut icon" href="../resources/image/logo.png" />
    <link rel="stylesheet" href="../resources/css/index.css" />
    <script defer type="text/javascript" src="../resources/js/index.js"></script>
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
          <span>로그인</span>
          <span>회원가입</span>
          <span>로그아웃</span>
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
    <div class="firstBanner">
      <div class="container" id="firstContent">
        <section class="banner1">
			<input type="button" value="LOGIN" class="buttons" id="loginButton" onclick="location='/synergy/all/loginForm'">
			<input type="button" value="JOINUS"class="buttons" onclick="location='/synergy/all/joinForm'">

        </section>
      </div>
    </div>

    <div class="secondBanner">
      <div class="container" id="secondContent">
        <section class="banner2"></section>
      </div>
    </div>

    <div class="thirdBanner">
      <div class="container" id="thirdContent">
        <section class="banner3"></section>
      </div>
    </div>

    <div class="fourthBanner">
      <div class="container" id="fourthContent">
        <section class="banner4"></section>
      </div>
    </div>

    <div class="fifthBanner">
      <footer>
        <div>
          <ul>
            <li><strong>서비스</strong></li>
            <li>회사</li>
            <li>문의</li>
            <li>고객센터</li>
            <li>이메일</li>
          </ul>
        </div>
        <div>
          <ul>
            <li><strong>공지사항</strong></li>
            <li>회사소개</li>
            <li>채용</li>
            <li>블로그</li>
          </ul>
        </div>
        <div>
          <ul>
            <li><strong>문의</strong></li>
            <li>사업 제휴</li>
            <li>마케팅</li>
            <li>IR</li>
          </ul>
        </div>
        <div>
          <ul>
            <li><strong>메뉴메뉴</strong></li>
            <li>메뉴1</li>
            <li>메뉴2</li>
            <li>메뉴3</li>
            <li>메뉴4</li>
          </ul>
        </div>
      </footer>
    </div>

    <div class="mobileMenu">
      <ul>
        <li>프로젝트소개</li>
        <li>이용 방법</li>
        <li>자주 묻는 질문</li>
        <li>로그인</li>
        <li>회원가입</li>
      </ul>
    </div>

      <input type="button" value="맨위로" id="topButton">
  </body>
</html>
