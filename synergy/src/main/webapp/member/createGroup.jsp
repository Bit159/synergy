<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta id="_csrf" name="_csrf" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<meta charset="UTF-8">
<title>시너지</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="../resources/css/createGroup.css">
</head>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	<form id="registerForm" method="post" action="/synergy-kh/member/regist">
	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
    <div id="body-wrapper">
        <div id="container" style="white-space: nowrap;">
            <div class="registForm-header">
                <h2>스터디그룹 개설하기</h2>
            </div>
            <div class="registForm">
                <div class="registForm-title">
                    <h1>한 줄 소개<span>*</span></h1>
                    <input type="text" name="title" id="title" placeholder="소개를 입력해주세요">
                </div>
                <div class="registForm-topic">
                    <h1>주제 선택<span>*</span></h1>
                    <input type="text" id="topic" name="topic">
                    <div id="AddTopic"></div>
                </div>
                <div class="registForm-location">
                    <h1>지역 검색<span>*</span></h1>
                    <input type="text" name="location" id="location">
                </div>
                <div class="registForm-people">
                    <h1>모집 인원<span>*</span></h1>
                    <select id="people" name="people">
                        <option >선택</option>
                        <option value="3">3명</option>
                        <option value="4">4명</option>
                        <option value="5">5명</option>
                        <option value="6">6명</option>
                        <option value="7">7명</option>
                        <option value="8">8명</option>
                        <option value="9">9명</option>
                    </select>
                </div>
                <div class="registForm-content">
                    <h1>상세설명</h1>
                    <textarea name="content"></textarea>
                </div> 
<!--                 <input type="hidden" id="content" name="content"> -->
                <div class="buttons">
                	<input type="submit" value="등록">
                	<input type="button" id="backBtn" value="돌아가기">
                </div>
            </div>
        </div>
    </div>
    </form>
    <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){
		$.ajax({
			type:'get',
			url:'/synergy-kh/member/autocomplete',
			data:{},
			dataType:'json',
			success:function(data){
				$(function(){
			    $("#location").autocomplete({
			        source: data.arr
			      });
				});
			},
			error:function(){
				alert('값 가져오기 실패')
			}
		});
	});
	let topics=["자바","자바스크립트","파이썬","리액트","스프링"]
    $('#topic').autocomplete({
        source: topics
      });
	$('#backBtn').click(function(){
		var back = confirm('입력하신 정보가 저장되지 않을 수 있습니다');
		if(back){
			location.href='/synergy-kh/member/cardBoard';
		}else{
			return;
		}
	});
</script>
</html>