<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
</head>
<body>
<input type="text" id="message">
<input type="button" id="sendBtn" value="보내기">
<div id="messageArea"></div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$('#sendBtn').click(function(){
	sendMessage();
	$('#message').val('');
	
	
});
let socket = new Sock
</script>
</html>