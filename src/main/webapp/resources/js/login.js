/*$('#loginBtn').click(function(){
	let id=$('#id').val();
	let pw=$('#pw').val();
	
	if(id == ''){
		alert('아이디 입력ㄱㄱ');
		
	} else if(pw == ''){
		alert('비번 입력ㄱㄱ');
		
	} else {
		$.ajax({
			type : 'post',
			url  : '/synergy/all/login',
			data : 'id=' + id + '&pw=' + pw,
			dataType : 'text',
			success : function(data){
				alert(data);
				location='/synergy/index';
			}
		});
	}
	
});*/