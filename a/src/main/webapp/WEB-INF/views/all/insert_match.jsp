<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Get Matched!</title>
    <script defer src="/resources/js/insert_match.js"></script>
    <link rel="stylesheet" href="/resources/css/insert_match.css" />
    <meta id="csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
    <meta id="csrf" name="_csrf" content="${_csrf.token}" />
    <link rel="shortcut icon" href="/resources/image/symbol.png">
  </head>
  <body>
  <%@ include file="/template/header.jsp"%>
    <form>
      <div id="wrap">
        <div id="selections">
          <select name="location" id="myLocation">
            <option value="">지역</option>
            <option value="온라인">온라인</option>
            <option value="서울">서울</option>
            <option value="경기">경기</option>
            <option value="인천">인천</option>
            <option value="대전">대전</option>
            <option value="대구">대구</option>
            <option value="부산">부산</option>
            <option value="울산">울산</option>
            <option value="광주">광주</option>
            <option value="강원">강원</option>
            <option value="세종">세종</option>
            <option value="충북">충북</option>
            <option value="충남">충남</option>
            <option value="경북">경북</option>
            <option value="경남">경남</option>
            <option value="전북">전북</option>
            <option value="전남">전남</option>
            <option value="제주">제주</option>
          </select>
          <select name="time" id="time">
            <option value="">시간대</option>
            <option value="WEEKDAY_AFTERNOON">평일 낮</option>
            <option value="WEEKDAY_EVENING">평일 저녁</option>
            <option value="WEEKEND_AFTERNOON">주말 낮</option>
            <option value="WEEKEND_EVENING">주말 저녁</option>
          </select>
          <select name="topic" id="topic">
            <option value="">주제</option>
            <option value="모각코">모각코</option>
            <option value="Algorithm">Algorithm</option>
            <option value="Java">Java</option>
            <option value="Python">Python</option>
            <option value="C">C</option>
            <option value="C++">C++</option>
            <option value="C#">C#</option>
            <option value="JavaScript">JavaScript</option>
            <option value="React">React</option>
            <option value="Vue">Vue</option>
            <option value="Spring">Spring</option>
            <option value="SpringBoot">SpringBoot</option>
            <option value="SQL">SQL</option>
            <option value="iOS">iOS</option>
            <option value="Android">Android</option>
            <option value="FrontEnd">FrontEnd</option>
            <option value="BackEnd">BackEnd</option>
            <option value="Toy Project">Toy Project</option>
            <option value="Machine Learning">Machine Learning</option>
          </select>
          <select name="career" id="career">
            <option value="">경력</option>
            <option value="0">무관</option>
            <option value="2">0년~2년</option>
            <option value="5">3년~5년</option>
            <option value="6">5년 이상</option>
            <option value="10">10년 이상</option>
          </select>
          <select name="people" id="people">
            <option value="">희망인원</option>
            <option value="0">무관</option>
            <option value="5">3인~5인</option>
            <option value="8">6인~8인</option>
            <option value="9">9인 이상</option>
          </select>
          <button type="button" id="submitButton">등록하기</button>
          <div id="msg"></div>
          <input type="hidden" name="x" id="x" />
          <input type="hidden" name="y" id="y" />
          <input type="hidden" name="range" id="range" />
        </div>
        <div id="padding_insert_match"></div>
        <div id="matching_db_wrapper">
        	<ul>
        		<li>
        			<div>area</div>
        			<div>time</div>
        			<div>topic</div>
        			<div>career</div>
        			<div>people</div>
        		</li>
       		<c:forEach var="dto" items="${list }" varStatus="status">
       			<li>
       				<input type="hidden" id="x${status.count }" value="${dto.x }" />
       				<input type="hidden" id="y${status.count }" value="${dto.y }" />
       				<input type="hidden" id="range${status.count }" value="${dto.range }" />
       				<div class="rangedArea"></div>
       				<div>${dto.time }</div>
       				<div>${dto.topic }</div>
       				<div>${dto.career }</div>
       				<div>${dto.people }</div>
       				<div><button class="deleteButtons_in_insert_match" type="button">삭제</button></div>
       			</li>
       		</c:forEach>
        	</ul>
        </div>
      </div>
      <%@ include file="/template/footer.jsp"%>
    </form>
  </body>
</html>
