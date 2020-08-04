<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <!-- navbar -->
 <div class="nav">
   <nav>
     <a href="/a/"><img src="/a/resources/image/logo.png" id="logo" /></a>

     <!-- PC ver. 메뉴 -->
     <div id="menuButtons">
       <span></span><span></span><span></span>
       <a href="/a/info"><span>프로젝트소개</span></a>
       <a href="/a/map"><span>지도를 내놓거라</span></a>
       <a href="/a/board"><span>모집게시판</span></a>
       <a href="/a/login"><span>로그인</span></a>
       <a href="/a/join"><span>회원가입</span></a>
       <span></span>
     </div>

     <!-- Mobile ver. 햄버거 -->
     <input type="checkbox" id="sideicon" />
     <label for="sideicon">
       <div id="hamburgerDiv">
         <div class="hamburger"></div>
         <div class="hamburger"></div>
         <div class="hamburger"></div>
       </div>
     </label>
     <!---->
   </nav>
 </div>

 <!-- Mobile ver. 메뉴 -->
 <div id="sidebar">
   <a href="/a/info"><span>프로젝트소개</span></a>
   <a href="/a/howto"><span>이용 방법</span></a>
   <a href="/a/board"><span>모집게시판</span></a>
   <a href="/a/join"><span>회원가입</span></a>
   <a href="/a/login"><span>로그인</span></a>
 </div>
 <!-- End of navbar -->

 <div id="margin"></div>
