$(document).ready(function(){
	//댓글 쓰기 
	$("#reply_writer_btn").click(function(){
		var reply = $("#reply_writer_text").val();
		var bno = document.querySelector('div.view_bno').innerText;
		var param = "reply="+reply+"&bno="+bno;
		
		$.ajax({
			type: "get",
			url: "/synergy/board/boardReply",
			data: param, /*{'reply':reply,'bno':bno}*/
			dataType: 'json',
			success: function(data){
				/*alert("댓글 등록");*/
				/*$.each(data.list, function(index, items){
					$('<li/>',{
						class: reply_group_item
					}).append($('<div/>',{
						class: reply_nickname,
						text: items.nickname
					})).append($('<div/>',{
						class: reply,
						text: items.reply
					})).append($('<div/>',{
						class: reply_button,
						text: "버튼"
					})).appendTo($(".reply_group"));
				});*/
				let a = '<li class="reply_group_item"><div class="reply_nickname">'
					    +"nickname"+
					    '</div><div class="reply">'
					    +reply+
					    '</div><div class="reply_button">'
					    +'<button type="button" class="replyBtn1">수정</button><button type="button" class="replyBtn2">삭제</button>'+
					    '</div></li>';
				
				let b = document.querySelector('ul.reply_group');
				
				b.innerHTML = b.innerHTML + a;
				
				$("#reply_writer_text").val('');
			},
			error: function(err){
				console.log(err);
			}
		});
	});
	//댓글 삭제
	$("button .replyBtn2").click(function(){
		var rno = document.querySelector('button.replyBtn2').parentElement.parentElement.children[0].value;
	});
});