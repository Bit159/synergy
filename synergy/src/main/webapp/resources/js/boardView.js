$(document).ready(function(){
	//댓글 쓰기 
	$(document).on("click","#reply_writer_btn",function(){
		var reply = $("#reply_writer_text").val();
		var bno = document.querySelector('div.view_bno').innerText;
		var param = "reply="+reply+"&bno="+bno;
		var csrfHeader = document.getElementById('_csrf_header').content;
		var csrfToken = document.getElementById('_csrf').content;
		console.log(csrfHeader);
		console.log(csrfToken);
		
		if(reply != ""){
			$.ajax({
				type: "post",
				url: "/synergy/board/boardReply",
				beforeSend: function(xhr){
		    		xhr.setRequestHeader(csrfHeader, csrfToken);
		    		
		    	},
				data: param, 
				dataType: 'json',
				success: function(data){
					alert("댓글 등록");
					console.log(data);
					
					// 비동기 방식
					/*let a = '<li class="reply_group_item"><div class="reply_nickname">'
						    +"nickname"+
						    '</div><div class="reply">'
						    +reply+
						    '</div><div class="reply_button">'
						    +'<button type="button" class="modifyBtn">수정</button><button type="button" class="deleteBtn" data-rno="${ replydto.rno }">삭제</button>'+
						    '</div></li>';
					
					let b = document.querySelector('ul.reply_group');
					
					b.innerHTML = b.innerHTML + a;
					
					$("#reply_writer_text").val('');
					
					let target = document.querySelector('.reply_header');
					let newnum = target.innerText.substring(6);
					newnum++;
					target.innerText = `댓글수 : ${newnum}`;
					
					let target1 = document.querySelector('.view_replys');
					let newnum1 = target1.innerText.substring(6);
					newnum1++;
					target1.innerText = `댓글수 : ${newnum1}`;*/
					
					//동기 방식
					location.href='/synergy/board/'+bno
				},
				error: function(err){
					console.log(err);
				}
			});
		}else{
			alert("댓글 내용을 입력하세요");
		}
		
	});
	
	//댓글 삭제
	$(document).on("click",".deleteBtn", function(){
		var $btnObj = $(this);
		let rno = $(this).data('rno') // data-rno
		var bno = document.querySelector('div.view_bno').innerText;
		
		var csrfHeader = document.getElementById('_csrf_header').content;
		var csrfToken = document.getElementById('_csrf').content;
		
		var param = "rno="+rno+"&bno="+bno;
		let result = confirm("정말 삭제하시겠습니까?");
		if(result){
			$.ajax({
				type: 'post',
				url: '/synergy/board/replyDelete',
				beforeSend: function(xhr){
		    		xhr.setRequestHeader(csrfHeader, csrfToken);
		    	},
				data: param,
				success: function(data){
					$btnObj.parent().parent().remove();
					
					let target = document.querySelector('.reply_header');
					let newnum = target.innerText.substring(6);
					newnum--;
					target.innerText = `댓글수 : ${newnum}`;
					
					let target1 = document.querySelector('.view_replys');
					let newnum1 = target1.innerText.substring(6);
					newnum1--;
					target1.innerText = `댓글수 : ${newnum1}`;
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
	
	//댓글 수정
	$(document).on("click",".modifyBtn", function(){
		var $btnObj = $(this);
		$btnObj.parent().parent().parent().children('div.reply_modify_wrapper').css('display','block');
	});
	
	$(document).on("click",".reply_modify_cancel", function(){
		var $btnObj = $(this);
		$btnObj.parent().parent().parent().parent().css('display','none');
	});
	
	$(document).on("click",".reply_modify_button",function(){
		var bno = document.querySelector('div.view_bno').innerText;
		console.log(bno);
		let rno = $(this).data('rno') // data-rno
		var reply = $(this).parent().parent().children('textarea.reply_modify_text').val();
		console.log(reply);
		var csrfHeader = document.getElementById('_csrf_header').content;
		var csrfToken = document.getElementById('_csrf').content;
		
		var param = "reply="+reply+"&rno="+rno;
		console.log(param);
		let result = confirm("정말 수정하시겠습니까?");
		if(result){
			$.ajax({
				type: 'post',
				url: '/synergy/board/replyModify',
				beforeSend: function(xhr){
		    		xhr.setRequestHeader(csrfHeader, csrfToken);
		    	},
		    	data: param,
		    	success: function(data){
		    		alert("댓글 수정 완료");
		    		location.href='/synergy/board/'+bno
		    	},
		    	error: function(err){
					console.log(err);
				}
				
			});
		}
		
	});
	
	
});