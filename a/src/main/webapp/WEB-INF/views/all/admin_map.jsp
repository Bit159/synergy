<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin Map</title>
<meta id="csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
<meta id="csrf" name="_csrf" content="${_csrf.token}" />
<link rel="shortcut icon" href="/resources/image/symbol.png">
</head>
<body>
	<%@ include file="/template/header.jsp"%>

	<div id="map" style="width: 1280px; height: 900px; margin: 0 auto; border: 3px solid #32be78;"></div>
	<%@ include file="/template/footer.jsp"%>
	<script
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41be22a5170d5fc6115853c77dc3d45e"></script>
	<script src="/resources/js/admin_map.js"></script>

	<script>
		let csrf = document.getElementById('csrf').content;
		let csrf_header = document.getElementById('csrf_header').content;
		let url = "/admin_map_getList";
		let options = {
			method: "POST",
			headers: {
				"X-CSRF-TOKEN": document.getElementById('csrf').content,
				Accept: "application/json",
				"Content-Type": "application/json; charset=utf-8",
			}
		};

		fetch(url, options).then((res) =>res.json().then((json)=>{
			console.log(json);
			console.log(json.length);
			console.log(json[0]);
			for(let i =0; i <json.length; i++) {
				drawCircle(json[i].y, json[i].x, json[i].range);
			}
			
		}));
	</script>
</body>
</html>
