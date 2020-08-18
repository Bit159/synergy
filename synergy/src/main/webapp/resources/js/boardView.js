$(doucument).ready(function(){
	//댓글 쓰기 
	$("#reply_writer_btn").click(function(){
		var reply = $("#reply_writer_text").val();
		var bno = "${cBoardDTO.bno }";
		var param = "reply="+reply+"&bno="+bno;
		
		$.ajax({
			type: "post",
			url: "/board/reply",
			data: param,
			success: function(){
				alert("댓글 등록");
				$('<li/>').append($('<div>',{
					
				})).append($('<div>',{
					
				})).append($('<div>',{
					
				})).appendTo($('.reply_group'));
			}
		});
	});
});