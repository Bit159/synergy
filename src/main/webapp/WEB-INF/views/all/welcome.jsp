<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/welcome.css">
    <script src="http://code.jquery.com/jquery-latest.min.js" defer></script>
    <script src="/resources/js/welcome.js" defer></script>
</head>
<body>
    <div id=body_wrapper>
    	<!--header  -->
        <jsp:include page="../template/header.jsp"/>
        <!-- 배너 -->
        <div id="banner_wrapper">
            <div id="banner" class="box container">
                <div class=banner_content>
                    <div class="banner_left">
                        <div class="homeImg">
                        	<img src="/resources/image/home.jpg">
                        </div>
                    </div>
                    <div class="banner_right">
                        <h2 style="white-space: nowrap;">Synergy Together</h2>
                        <p style="white-space: nowrap;">쉽고 빠른 스터디 매칭</p>
                        <ul>
                            <li>
                            	<sec:authorize access="isAnonymous()">
	                                <a href="/all/loginForm" class="loginIcon">Login</a>
                            	</sec:authorize>
                            	<sec:authorize access="isAuthenticated()">
                            		<form action="/logout" method="post" name="logoutForm">
                            			<a href="javascript:document.logoutForm.submit();" class="loginIcon">Logout</a>
                            			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
                            		</form>
                            	</sec:authorize>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- 아티클 -->
        <div id="article_wrapper">
            <div class="container">
                <div class="article_content">
                    <div class="article_left">
                        <article class="box">
                            <img>
                            <div class="inner">
                            </div>
                        </article>
                    </div>
                    <div class="article_center">
                        <article class="box">
                            <img>
                        </article>
                    </div>
                    <div class="article_right">
                        <article class="box">
                            <img>
                        </article>
                    </div>
                </div>
            </div>
        </div>
        <!-- 푸터 -->
        <jsp:include page="../template/footer.jsp"/>
	</div>
</body>
</html>