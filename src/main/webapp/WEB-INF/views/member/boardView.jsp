<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/boardView.css">
</head>
<body>
<jsp:include page="../template/header.jsp"/>
	<div class="bodywrapper">
        <div class="boardwrapper">
            <h1>topic</h1>
            <div class="boardcontainer">                
                <div class="board_header">
                    <div class="header_upside">
                        <div class="bno">글번호</div>
                        <div class="title">제목</div>
                    </div>
                    <div class="header_downside">
                        <div class="downside_left">                                                   
                            <div class="nickname">작성자</div>
                            <div class="boarddate">작성시간</div>
                        </div>
                        <div class="downside_right">
                            <div class="replys">댓글수</div>
                            <div class="hit">조회수</div>   
                        </div>               
                    </div>
                </div>
                <div class="board_body">
                    <div class="content">내용</div>
                </div>
                <div class="board_footer">
                    <div class="reply">댓글</div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="../template/footer.jsp"/>
</body>
</html>