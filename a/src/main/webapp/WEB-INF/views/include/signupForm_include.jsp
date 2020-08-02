<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="join-form">
	<h1>Let's Synergy</h1>
	<form action="" method="post">
		<div class="join-area">
			<input type="text" name="name" id="name" autocomplete="off" required>
			<label for="name">NAME</label>
		</div>
	
		<div class="join-area">
			<input type="text" name="id" id="id" autocomplete="off" required>
			<label for="id">USER NAME</label>
		</div>
		
		<div class="join-area">
			<input type="password" name="pwd" id="pwd" autocomplete="off" required>
			<label for="pwd">PASSWORD</label>
		</div>
		
		<div class="join-area">
			<input type="radio" name="gender" id="gender" value="남성" checked><span style="color:aliceblue">남 성</span>&emsp;
			<input type="radio" name="gender" id="gender" value="여성"><span style="color:aliceblue">여 성</span>
		</div>
		
		<div class="join-area">
			<input type="text" name="email" id="email" autocomplete="off" required>
			<label for="email">E-MAIL</label>
		</div>
		
		<div class="join-area">
			<input type="text" name="phone" id="phone" autocomplete="off" placeholder="                                              - 를 입력해주세요." required>
			<label for="phone">PHONE</label>
		</div>

		<div class="join-area">
			<input type="date" id="birth" style="width: 45%;">
			<label for="birth">BIRTH</label>
		</div>
		
		<div class="btn-area">
			<button type="submit">JOIN!</button>
			<button name="back" id="back" onclick="location='login.html'">BACK</button>
		</div>
	</form>
</section>