<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<script defer src="/resources/js/admin_map.js" ></script>
<link rel="stylesheet" href="/resources/css/admin_map.css" />
</head>
<body>
	<%@ include file="/template/header.jsp"%>
	<div id="map" style="width: 800px; height: 600px; margin: 0 auto; border: 3px solid #32be78;"></div>
	<%@ include file="/template/footer.jsp"%>
	<script	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41be22a5170d5fc6115853c77dc3d45e"></script>
</body>
</html>
