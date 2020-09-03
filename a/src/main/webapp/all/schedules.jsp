<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta id="csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
    <meta id="csrf" name="_csrf" content="${_csrf.token}" />
    <title>일정관리</title>
    <link rel="stylesheet" href="/resources/css/mycalendar.css" />
    <link rel="stylesheet" href="/resources/css/main.css" />
    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/locales-all.js"></script>
    <script src="/resources/js/mycalendar.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  </head>
  <body>
  	<%@ include file="/template/header.jsp"%>
    <div id="calendar"></div>
    <%@ include file="/template/footer.jsp"%>
  </body>
</html>
