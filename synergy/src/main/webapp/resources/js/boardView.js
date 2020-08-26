$(document).ready(function(){
	//댓글 쓰기 
	$("#reply_writer_btn").click(function(){
		var reply = $("#reply_writer_text").val();
		var bno = document.querySelector('div.view_bno').innerText;
		var param = "reply="+reply+"&bno="+bno;
		var header = '${_csrf.header}'; 
		var token = '${_csrf.token}';		
		
		$.ajax({
			type: "post",
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
					    +'<button type="button" class="modifyBtn">수정</button><button type="button" class="deleteBtn" data-rno="${ replydto.rno }">삭제</button>'+
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
	$(".deleteBtn").on("click", function(){
		var $btnObj = $(this);
		let rno = $(this).data('rno') // data-rno
		
		var param = "rno="+rno;
		let result = confirm("정말 삭제하시겠습니까?");
		if(result){
			$.ajax({
				type: 'get',
				url: '/synergy/board/replyDelete',
				data: param,
				success: function(data){
					$btnObj.parent().parent().remove();
				},
				error: function(err){
					console.log(err);	
				}
			});
		}
		
		/*
		let a = document.querySelectorAll('button .deleteBtn');
		let rno;
		
		for(let i = 0; i<a.length; i++){
			rno = a[i].parentElement.parentElement.children[0].value;
		}
		
		var param = "rno="+rno;
		
		a[i].addEventListener('click', ()=> {
			let result = confirm("정말 삭제하시겠습니까?");
			
			if(result){
				$.ajax({
					type: 'get',
					url: '/synergy/board/replyDelete',
					data: param,
					success: function(data){
						alert("삭제 성공");
						a[i].parentElement.parentElement.remove();
					},
					error: function(err){
						console.log(err);	
					}
				});
				
			}
		});
		*/
	});
});