<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../resources/css/card.css">
</head>
<body>
	<div id="body-wrapper">
	<jsp:include page="../template/header.jsp"></jsp:include>
    <div id="container">
        <input type="button" value="모집 등록" id="regist" onclick="location.href='/synergy-kh/member/createGroup'">
        <div id="selectBar">
        	<h1>지역 선택</h1>
        	<div class="selectLocation">
        		<ul id="select-si">
        			<li><a href="#">서울</a></li>
        			<li><a href="#">경기</a></li>
        			<li><a href="#">인천</a></li>
        			<li><a href="#">부산</a></li>
        			<li><a href="#">대구</a></li>
        			<li><a href="#">광주</a></li>
        			<li><a href="#">대전</a></li>
        			<li><a href="#">울산</a></li>
        			<li><a href="#">세종</a></li>
        			<li><a href="#">강원</a></li>
        			<li><a href="#">경남</a></li>
        			<li><a href="#">전남</a></li>
        			<li><a href="#">전북</a></li>
        			<li><a href="#">경북</a></li>
        			<li><a href="#">충남</a></li>
        			<li><a href="#">충북</a></li>
        			<li><a href="#">제주</a></li>
        		</ul>
	        	<ul id="select-gu">
	        		<li id="selectAll"><a>전체선택</a></li>
	        	</ul>
	        	<div id="selected-location">
	        	선택한 지역: 
	        	
	        	</div>
        	</div>
        	<br>
        	<div class="selectTopic">
        		<h1>주제 선택</h1>
        		<div id="selectTopic">
        			
        		</div>
        	</div>
        </div>
        <c:forEach var="dto" items="${list}">
        <a href="#">
            <div class="card">
                <!-- 카드 헤더 -->
                <div class="card-header">
                    <div class="card-header-content">
                        <div class="card-header-text"> 모집중 </div>
                        <div class="card-header-count">1 / ${dto.people}  </div>
                    </div>
                </div>
                <!-- 카드 바디 -->
                <div class="card-body">
                    <div class="card-body-content">
                        <h1 class="card-body-title">${dto.title }</h1>
                        <p class="card-body-nickname">작성자: 조커</p>
                        <p class="card-body-location">지역: ${dto.location }</p>
                    </div>
                </div>
                <!-- 카드 푸터 -->
                <div class="card-footer"></div>
            </div>
        </a>
        </c:forEach>
    </div>
    <jsp:include page="../template/footer.jsp"></jsp:include>
    </div>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			type:'get',
			url:'/synergy-kh/member/getLocation',
			dataType:'json',
			success:function(data){
				let aIndex=0;
				let guIndex=0;
				let city='';
				let gu='';
				/* 상위지역 선택 */
				$('#select-si').on('click','li',function(){
					aIndex = $(this).index();
					city = $('#select-si li:nth-child('+aIndex+')').children('a').text();
					var color = $(this).css('background-color');
					
					if(color!='rgb(211, 211, 211, 0.5)'){
						$('#select-si > li').css('background-color','white');
						$(this).css('background-color','rgb(211, 211, 211,0.5)');
					}else $(this).css('background-color','white');
					
					$('#select-gu li:gt(0)').remove();
							$.each(data[aIndex], function(i,item){
								$('#select-gu').append("<li><a>"+data[aIndex][i]+"</li></a>")
							});
					/* 하위지역 선택 */		
					$('#select-gu').on('click','li',function(){
						guIndex=$(this).index()+1;
						gu =  $('#select-gu li:nth-child('+guIndex+')').children('a').text();
						alert(city+' '+gu);
					});
				});
				$('#select-si > li:nth-child(1)').trigger('click');
			},
			error:function(){
				console.log('error');
			}
		});
	});		
	</script>
</body>
</html>