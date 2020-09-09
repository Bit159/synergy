<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta id="_csrf" name="_csrf" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/boardWriteForm.css">
</head>
<body>
	<div id="boardWriteWrapper">
        <div id="boardWriteContainer">
            <!--
            <div id="boardWriteTopic">
                <label for="" id="boardWriteTopicLabel"></label>
                <div id="boardWriteTopicDiv"></div>
            </div>
            -->
            <div id="boardWriteTitle">
                <label for="" id="boardWriteTitleLabel"></label>
                <div id="boardWriteTitleDiv">
                    <input type="text" id="boardWriteTitleText">
                </div>
            </div>
            <div id="boardWriteContent">
                <label for="" id="boardWriteContentLabel"></label>
                <div id="boardWriteContentDiv">
                    <textarea name="boardWriteContentText" id="boardWriteContentText" ></textarea>
                </div>
            </div>
            <div id="boardWriteButton">
                <button id="boardWriteBtn">글작성</button>
                <button id="boardListBtn">글목록</button>
            </div>
        </div>
    </div>
    
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
    $(document).on('click', '#boardWriteBtn', function(){
    	var title = $("#boardWriteTitleText").val();
    	var content = $("#boardWriteContentText").val();
    	var nickname = "nickname";
    	var param = "title="+title+"&content="+content+"&nickname="+nickname;
    	
    	var csrfHeader = document.getElementById('_csrf_header').content;
		var csrfToken = document.getElementById('_csrf').content;
		console.log(csrfHeader);
		console.log(csrfToken);
    	
		if(title != "" & content != ""){
			$.ajax({
				type : "post",
				url : "/synergy/board/boardModify",
				beforeSend: function(xhr){
		    		xhr.setRequestHeader(csrfHeader, csrfToken);
		    		
		    	},
		    	data: param,
		    	success: function(){
		    		alert("글 수정 완료");
		    		location.href='/synergy/bboard/boardList2';
		    	},
		    	error: function(err){
					console.log(err);
				}
			});
		}else{
			alert("제목과 내용을 입력해 주세요")
		}
    });
    </script>
    
</body>
</html>