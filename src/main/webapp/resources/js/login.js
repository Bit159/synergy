$('#loginBtn').click(function(){
	let id=$('#username').val();
	let pw=$('#password').val();
	
	if(id == ''){
		alert('아이디 입력ㄱㄱ');
		
	} else if(pw == ''){
		alert('비번 입력ㄱㄱ');
		
	}
	
});