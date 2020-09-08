$(document).ready(function(){
		
		//댓글 쓰기 
		$(document).on("click","#reply_writer_btn",function(){
			var $btnObj = $(this);
			var page = $(this).data('page');
			var range = $(this).data('range');
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
					url: "/synergy/bboard/boardReply2",
					beforeSend: function(xhr){
			    		xhr.setRequestHeader(csrfHeader, csrfToken);
			    		
			    	},
					data: param, 
					dataType: 'json',
					success: function(data){
						alert("댓글 등록");
						console.log(data);
						
						//동기 방식
						location.href='/synergy/bboard/'+bno+'?pg='+page+'&range='+range;
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
			let rno = $(this).data('rno'); // data-rno
			var bno = document.querySelector('div.view_bno').innerText;
			
			var csrfHeader = document.getElementById('_csrf_header').content;
			var csrfToken = document.getElementById('_csrf').content;
			
			var param = "rno="+rno+"&bno="+bno;
			let result = confirm("정말 삭제하시겠습니까?");
			if(result){
				$.ajax({
					type: 'post',
					url: '/synergy/bboard/replyDelete2',
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
			
		});
		
		//댓글 수정
		$(document).on("click",".modifyBtn", function(){
			var $btnObj = $(this);
			$btnObj.parent().parent().parent().css('display','none');
			$btnObj.parent().parent().parent().parent().children('div.reply_modify_wrapper').css('display','block');
		});
		
		$(document).on("click",".reply_modify_cancel", function(){
			var $btnObj = $(this);
			$btnObj.parent().parent().parent().parent().parent().children('li.reply_group_item2').css('display','flex');
			$btnObj.parent().parent().parent().parent().css('display','none');
		});
		
		$(document).on("click",".reply_modify_button",function(){
			var bno = document.querySelector('div.view_bno').innerText;
			console.log(bno);
			var page = $(this).data('page');
			var range = $(this).data('range');
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
					url: '/synergy/bboard/replyModify2',
					beforeSend: function(xhr){
			    		xhr.setRequestHeader(csrfHeader, csrfToken);
			    	},
			    	data: param,
			    	success: function(data){
			    		alert("댓글 수정 완료");
			    		location.href='/synergy/board/'+bno+'?pg='+page+'&range='+range;
			    	},
			    	error: function(err){
						console.log(err);
					}
					
				});
			}
			
		});
		
		
	});