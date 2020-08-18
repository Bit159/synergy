<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="map" style="width: 100%; height: 800px;"></div>
<p>
	<button onclick="removeCircles()">모두 지우기</button>
	<br />
</p>
<em> 지도를 마우스로 클릭하면 원 그리기가 시작되고 <br /> 오른쪽 마우스를 클릭하면 원 그리기가 종료됩니다
</em>
<p>
	선택이 완료되었다면 완료 버튼을 눌러주세요<br />
	<button type="button" value="완료" onclick="xydbclose()" />
</p>
<form>
	<input type="hidden" id="x" name="x" /> <input type="hidden" id="y"
		name="y" /> <input type="hidden" id="range" name="range" />
</form>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41be22a5170d5fc6115853c77dc3d45e"></script>
<script src="/resources/map.js" />