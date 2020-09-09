<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyPage</title>
    <link rel="shortcut icon" href="../resources/image/logo.png" />
    <link rel="stylesheet" href="../resources/css/myPage.css">
    <style type="text/css">
        .aside_menu div{
            border-radius: 10px;
            padding: 10px 10px;
            cursor: pointer;
        }

        .study_wrap{
            text-align: left;
        }


        .study_name:hover, .study_name:focus, .study:hover, .study:focus {
            background-color: rgb(50, 190, 120);
        }

        .study_content{
            display: none;
            text-align: left;
            font-size: 8px;
        }

        #study_content{
            display: none;
            text-align: left;
            font-size: 8px;
        }

        .study_content1{
            display:block;
        }
	</style>
</head>
<body>
<div id=body_wrapper>
	<jsp:include page="../template/header.jsp"/>
	<!-- 가운데 main 내용 -->
	<div id="content_wrapper">
		<header class="content_header">
        	<div class="header_label" id="header_label">개인 정보 관리</div>
		</header>
		<aside class="content_aside" id="content_aside">
			<div class="aside_menu" id="aside_menu">
				<div class="study_wrap">
					<div class="study_name" onclick="getMyInfo()">개인 정보 관리</div>
				</div>

				<div class="study_wrap">
					<div class="study_name" onclick="showMenu(this)">JAVA Study</div>
                    
					<div class="study_content" id="study_content" style="padding:0;">
                    	<div class="study" onclick="getStudyInfo()">&emsp; - 스터디 정보</div>
                    	<div class="study">&emsp; - 일정 관리</div>
                    </div>
                </div>
            </div>
		</aside>
        <section class="content_section" id="content_section">
        	<div class="revise_wrap" id="revise_wrap">
				<form name="reviseForm" id="reviseForm" method="post" action="/member/revise">
					<table>
						<tbody>
							<tr>
								<th class="table_left">아이디</th>
								<td class="id_label">${memberDTO.username}</td>
	        				</tr>
							<tr>
		            			<th class="table_left">비밀번호</th>
		            			<td class="table_right">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<input type="hidden" id="username" name="username" value="${memberDTO.username}">
										<input type="password" name="password" id="password" placeholder="비밀번호 입력">
		                	
			              				<div id="pwdDiv"></div>
								</td>
							</tr>
						
				        	<tr>
				           		<th class="table_left">비밀번호 재입력</th>
				           		<td class="table_right">
				                	<input type="password" name="repwd" id="repwd" placeholder="한번 더 입력해 주세요">
				        		</td>
		        			</tr>
			        
			        		<tr>
			           			<th class="table_left_bottom"><span class="nickName_star">*  </span>닉네임</th>
			            		<td class="table_right_bottom">
			                		<input type="text" name="nickname" id="nickname" value="${memberDTO.nickname}">
			                		<span class="nickName_re">닉네임을 입력하세요</span>
			               			<div id="nicknameDiv"></div>
			           			</td>
			        		</tr>        
						</tbody>
					</table>
				</form>
				<div class="buttonDiv">
				    <input type="button" id="reviseBtn" value="수정">
				    <input type="reset" id="resetBtn" value="취소"> 
				</div>
				<div class="withdrawDiv">
			    	<div class="withdrawDiv_label">
				   		<form name="withdrawalForm" id="withdrawalForm" method="post" action="/member/withdrawal">
				    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				      		<input type="hidden" id="username" name="username" value="${memberDTO.username}">
			        	</form>
						<div class="withdrawBtn"><input type="button" value="회원 탈퇴" id="withdrawBtn"></div>    
					</div>
				</div>
			</div>
			
			<div id="study_info" align="center" style="display:none;">
				<form name="infoForm" id="infoForm">
					<table>
						<tbody>
							<tr>
								<th class="table_left" style="width: 30%;">스터디 이름</th>
								<td class="table_right" style="width: 500px;">&emsp; 자바 & 스프링 스터디 합시댜</td>
							</tr>
							<tr>
								<th class="table_left">스터디 과목</th>
								<td class="table_right">&emsp; 자바, 스프링</td>
							</tr>
						
							<tr>
								<th class="table_left">스터디 주제</th>
								<td class="table_right">&emsp; MVC Spring Web 프로젝트</td>
							</tr>
							
							<tr>
								<th class="table_left">스터디 인원</th>
								<td class="table_right">
									&emsp; 뱅주(byungjoo104@gmail.com)<br>
									&emsp; 근형(kkh@gmail.com) <br>
									&emsp; 형중(jhj@gmail.com) <br>
									&emsp; 세진(hsj@gmail.com) <br>
									&emsp; 하진(jhj@gmail.com) <br>
								</td>
							</tr>        
						</tbody>
					</table>
				</form>
			</div>
		</section>
		<article class="content_article"></article>
		<footer class="content_footer"></footer>
	</div>
	<jsp:include page="../template/footer.jsp"/>
</div>
<jsp:include page="chattingList.jsp"/>                                            
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

//========================================================= 회원정보 수정

$('#reviseBtn').click(function(){

$('#pwdDiv').empty();	
$('#nicknameDiv').empty();
	
let pwd = $('#password').val();
let repwd = $('#repwd').val();
let nickname = $('#nickname').val();

	
	if(pwd != repwd){
		
		$('#pwdDiv').text("비밀번호를 동일하게 입력해 주세요").css("color", "red").css("font-size", "8pt").css("font-weight", "bold");	
		
	}else if(nickname == null || nickname == ""){
	
		$('#nicknameDiv').text("닉네임을 입력해 주세요").css("color", "red").css("font-size", "8pt").css("font-weight", "bold");
	
	}else{
		document.forms[0].submit();	
	}
	
});

//========================================================= 회원탈퇴

$('#withdrawBtn').click(function(){
	
let withdrawal = confirm("정말 회원을 탈퇴 하시겠습니까?");

	if(withdrawal){
		document.forms[1].submit()
	}
});

function showMenu(obj){
    let content = document.getElementById('study_content');

    if(!obj.classList.contains('1')){
        content.style.display='block';
        
    }else{
        content.style.display="none";
    }

    obj.classList.toggle("1");

}

function getStudyInfo(){
	let header_label = document.getElementById('header_label');
	let revise = document.getElementById('revise_wrap');
	let info = document.getElementById('study_info');
	
	$('#header_label').empty();
	header_label.innerHTML += '스터디 정보'
		
	revise.style.display = 'none';
	info.style.display = 'block';
	
}

function getMyInfo(){
	let header_label = document.getElementById('header_label');
	let revise = document.getElementById('revise_wrap');
	let info = document.getElementById('study_info');
	
	$('#header_label').empty();
	header_label.innerHTML += '개인 정보 관리';
	revise.style.display = 'block';
	info.style.display = 'none';
	
}
</script>
</body>
</html>