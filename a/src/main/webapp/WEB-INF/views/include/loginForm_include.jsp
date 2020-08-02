<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<section class="login-form">
  <label for="sideicon" id="back"></label>
  <h1>Let's Synergy</h1>
  <form method="post" action="">
    <div class="info-area">
      <input type="text" name="id" id="id" autocomplete="off" required />
      <label for="id">EMAIL</label>
    </div>

    <div class="info-area">
      <input type="password" name="pwd" autocomplete="off" id="pwd" required />
      <label for="pwd">PASSWORD</label>
    </div>
    <div class="btn-area">
      <button type="button" id="loginBtn">LOGIN</button>
      <button onclick="location='index.jsp'">BACK</button>
    </div>
  </form>
  <div class="thirdParty" align="center" style="margin-top: 30px;">
    <img src="/a/resources/image/google.png" /><br />
    <img src="/a/resources/image/kakao_login_medium_narrow.png" /><br />
  </div>

  <div class="caption">
    <a href="joinForm.html">회원가입</a>&emsp;
    <a href="">아이디/비밀번호 찾기</a>
  </div>
</section>




<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$('#loginBtn').click(function(){
		if($('#id').val()==''){
			return;
		}
		$.ajax({
			type:'post',
			url:'/synergy/member/login',
			data:{'id':$('#id').val(),'pwd':$('#pwd').val()},
			dataType:'text',
			success:function(data){
				alert(data);
				if(data=='fail'){
					alert('실패');
				}else if(data=='success'){
					alert('성공');
					location.href='/synergy/member/welcome';
				}
			},
			error:function(){
				alert('error');
			}
		});
	});
</script>