/*var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.528129, 126.981729), // 지도의 중심좌표
		level: 7, // 지도의 확대 레벨
	};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 표시할 원을 생성합니다
function drawCircle(y, x, range) {
	var circle = new kakao.maps.Circle({
		center: new kakao.maps.LatLng(y, x), // 원의 중심좌표 입니다
		radius: range, // 미터 단위의 원의 반지름입니다
		strokeWeight: 3, // 선의 두께입니다
		strokeColor: "#32be78", // 선의 색깔입니다
		strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle: "solid", // 선의 스타일 입니다
		fillColor: "#CFE7FF", // 채우기 색깔입니다CFE7FF
		fillOpacity: 0.4, // 채우기 불투명도 입니다
	});

	// 지도에 원을 표시합니다
	circle.setMap(map);
}*/



var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.528129, 126.981729), // 지도의 중심좌표
		level: 9, // 지도의 확대 레벨
	};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 표시할 원을 생성합니다
function drawCircle(y, x, range) {
	var circle = new kakao.maps.Circle({
		center: new kakao.maps.LatLng(y, x), // 원의 중심좌표 입니다
		radius: range, // 미터 단위의 원의 반지름입니다
		strokeWeight: 3, // 선의 두께입니다
		strokeColor: "#32be78", // 선의 색깔입니다
		strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle: "solid", // 선의 스타일 입니다
		fillColor: "#CFE7FF", // 채우기 색깔입니다CFE7FF
		fillOpacity: 0.4, // 채우기 불투명도 입니다
	});

	// 지도에 원을 표시합니다
	circle.setMap(map);
}