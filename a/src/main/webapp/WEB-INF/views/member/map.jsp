<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="../include/head_include.jsp"%>
		<style>@import url('/a/resources/css/map.css')</style>
		<title>지도</title>
	</head>

	<body>
		<%@ include file="../include/nav_include.jsp"%>

		<div id="map" style="width: 80%; height: 700px; margin:0 auto;"></div>
		<div id="divBelowMap">
		<p><button type="button" onclick="removeCircles()">모두 지우기</button></p>
		<em> 지도를 마우스로 클릭하면 원 그리기가 시작되고  오른쪽 마우스를 클릭하면 원 그리기가 종료됩니다	</em>
		<p>	선택이 완료되었다면 완료 버튼을 눌러주세요	<button type="button" style="width:50px;height:30px;" onclick="xydbclose()">완료!!</button></p>
		</div>
		<img id="loading" src="/a/resources/image/loading.svg" />
		<a href="/a/mylogout">로그아웃</a>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41be22a5170d5fc6115853c77dc3d45e"></script>
	<script src="/a/resources/js/synergymap.js"></script>
	<script src="/a/resources/js/kakaomap.js"></script>
	</body>
	
</html>
