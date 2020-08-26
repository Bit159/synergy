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
        
        <div id="selectBox">
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
        	<div class="selectTopic">
        		<h1>주제 선택</h1>
        		<div id="selectTopic">
        			<select id="sel">
        				<option value=''>선택</option>
        				<option value="Java">Java</option>
        				<option value="JavaScript">JavaScript</option>
        				<option value="Python">Python</option>
        				<option value="Algorithm">Algorithm</option>
        				<option value="C">C</option>
        				<option value="C++">C++</option>
        				<option value="C#">C#</option>
        				<option value="React">React</option>
        				<option value="Vue">Vue</option>
        				<option value="Spring">Spring</option>
        				<option value="SpringBoot">SpringBoot</option>
        				<option value="SQL">SQL</option>
        				<option value="Android">Android</option>
        				<option value="iOS">iOS</option>
        				<option value="FrontEnd">FrontEnd</option>
        				<option value="BackEnd">BackEnd</option>
        				<option value="모각코">모각코</option>
        			</select>
        		</div>
        	</div>
        	<div id="searchCard">
        		<input type="button" value="검색" id="searchCardBtn">
        		<input type="button" value="그룹 만들기" id="regist" onclick="location.href='/synergy-kh/member/createGroup'">
        	</div>
        </div>
       	<form id="listForm">
        <div class="cardboard">
	        <c:forEach var="dto" items="${list}">
        	 <a href="/synergy-kh/member/cardBoardView?seq=${dto.seq}" id="card1">
	           	 <div class="card">
	                <!-- 카드 헤더 -->
	                <div class="card-header">
	                    <div class="card-header-content">
	                    	<input type=hidden name="seq" id="seq" value="${dto.seq}">
	                        <div class="card-header-text"> 모집중  </div>
	                        <div class="card-header-count">1 / ${dto.people}  </div>
	                    </div>
	                </div>
	                <!-- 카드 바디 -->
	                <div class="card-body">
	                    <div class="card-body-content">
	                        <h1 id="card-body-title">${dto.title }</h1>
	                        <p id="card-body-nickname">작성자 ${dto.nickname} </p>
	                        <p id="card-body-location">지역 ${dto.location }</p>
	                    </div>
	                </div>
	                <!-- 카드 푸터 -->
	                <div class="card-footer"></div>
            	</div>
	       	 </a>
	        </c:forEach>
        </div>
        </form>
        <!-- pagination{s} -->
		<div id="paginationBox">
			<ul class="pagination">
				<c:if test="${paging.prev}">
					<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${paging.page}', '${paging.range}', '${paging.rangeSize}')">〈</a></li>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="idx">
					<li class="page-item"><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${paging.range}', '${paging.rangeSize}')"> ${idx} </a></li>
				</c:forEach>
				<c:if test="${paging.next}">
					<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${paging.range}', '${paging.range}', '${paging.rangeSize}')" >〉</a></li>
				</c:if>
			</ul>
		</div>
		<!-- pagination{e} -->
    </div>
    <jsp:include page="../template/footer.jsp"></jsp:include>
    </div>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
	let aIndex=0;
	let guIndex=0;
	let si='';
	let gu='';
	let sigu='';
	let count=0;
	let locations = [];
	$(document).ready(function(){
		$.ajax({
			type:'get',
			url:'/synergy-kh/member/getLocation',
			dataType:'json',
			success:function(data){
				/* 상위지역 선택 */
				$('#select-si').on('click','li',function(){
					aIndex = $(this).index();
					si = $('#select-si li:nth-child('+(aIndex+1)+')').children('a').text();
				
					var color = $(this).css('background-color');
					if(color!='rgb(50, 190, 120, 0.7)'){
						$('#select-si > li').css('background-color','white');
						$('#select-si > li > a').css('color','black');
						$(this).css('background-color','rgb(50, 190, 120, 0.7)');
						$(this).children().css('color','white').css('font-weight','550');
					}else {
						$(this).css('background-color','white');
					}
					
					$('#select-gu li:gt(0)').remove();
					$.each(data[aIndex], function(i,item){
						$('#select-gu').append("<li><a>"+data[aIndex][i]+"</li></a>")
					});
				});
				$('#select-si > li:nth-child(1)').trigger('click');
			},
			error:function(){
				console.log('error');
			}
		});
	});		
	/* 하위지역 선택 */	
	$('#select-gu').on('click','li',function(){
		guIndex=$(this).index()+1;
		gu =  $('#select-gu li:nth-child('+guIndex+')').children('a').text();
		sigu = si+' '+gu;
		if($('#selected-location > span').text().includes(sigu)){
			return;
		}
		if($('#selected-location > span').length <= 2 ){
			count=count+1;
			$('#selected-location').append("<span id=close"+count+">"+sigu+"<button type=button>x</button></span>");
			locations.push(sigu);
			/* 추가된 지역 삭제 */
			$('#selected-location > span#close'+count).on('click','button',function(){
				$(this).parent().remove();
				let text = $(this).parent().text(); //해당 텍스트값에서
				let sliceChar = text.slice(0,-1); //마지막 글자인 x를 빼기
				locations.splice(locations.indexOf(sliceChar),1); //그 인덱스값을 제거
			});
		}else alert('최대 3개까지 선택할 수 있습니다.')
	});
	$('#searchCardBtn').click(function(){
		$.ajax({
			type:'get',
			url:'/synergy-kh/member/searchCard',
			data:{'locations':locations,'topic':$('#sel').val()},
			dataType:'json',
			traditional:true,
			success:function(data){
				$('.card').parent().remove();
				$.each(data, function(i,item){
					/* foreach문 안에 있는 코드 그대로.. */
					$('.cardboard').append("<a href=/synergy-kh/member/cardBoardView?seq="+item.seq+"><div class=card><div class=card-header><div class=card-header-content><div class=card-header-text>모집중</div><div class=card-header-count>1 /"+item.people+"</div></div></div><div class=card-body><div class=card-body-content><h1 class=card-body-title>"+item.title+"</h1><p class=card-body-nickname>작성자: "+item.nickname+"</p><p class=card-body-location>지역: "+item.location+"</p></div></div><div class=card-footer></div></div></a>");
					
					
				});
			},
			error:function(){
				console.log('에러러러럴')
			}
		});
	});
	
	//이전 버튼 이벤트
	function fn_prev(page, range, rangeSize) {
			var page = ((range - 2) * rangeSize) + 1;
			var range = range - 1;
			var url = "${pageContext.request.contextPath}/member/cardBoardList";
			url = url + "?pg=" + page;
			url = url + "&range=" + range;
			location.href = url;
		}
    //페이지 번호 클릭
	function fn_pagination(page, range, rangeSize) {
		var url = "${pageContext.request.contextPath}/member/cardBoardList";
		url = url + "?pg=" + page;
		url = url + "&range=" + range;
		location.href = url;	
	}
	//다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		var url = "${pageContext.request.contextPath}/member/cardBoardList";
		url = url + "?pg=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
	</script>
</body>
</html>