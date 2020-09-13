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
    <link rel="stylesheet" href="/resources/richard/css/mycalendar.css" />
    <link rel="stylesheet" href="/resources/richard/css/main.css" />
    <script src="/resources/richard/js/main.js"></script>
    <script src="/resources/richard/js/locales-all.js"></script>
    <script src="/resources/richard/js/mycalendar.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
  </head>
  <body>
  	<%@ include file="/WEB-INF/views/richard/template/header.jsp"%>
    <div id="calendar"></div>
    <%@ include file="/WEB-INF/views/richard/template/footer.jsp"%>
  </body>
</html>
