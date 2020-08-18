<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Synergy</title>
    <script defer src="/resources/js/_insert_match.js"></script>
    <link rel="stylesheet" href="/resources/css/_insert_match.css" />
    <link rel="shortcut icon" href="/resources/image/symbol.png">
    <meta id="csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
    <meta id="csrf" name="_csrf" content="${_csrf.token}" />
  </head>
<body>
  <%@ include file="/template/header.jsp"%>
    <input type="hidden" id="x" />
    <input type="hidden" id="y" />
    <input type="hidden" id="range" />
    <div id="wrap">
    <div id="msg"> </div>
      <div id="option_boss">
        <div id="location">
          <div id="location_selection_wrapper">
            <input
              type="text"
              id="location_selection"
              class="opener"
              readonly
              placeholder="지역 ▽"
            />
          </div>
          <div id="location_option_wrapper" class="option_wrappers">
          	<input type="hidden" id="myLocation">
            <div class="location_options">온라인</div>
            <div class="location_options">서울</div>
            <div class="location_options">경기</div>
            <div class="location_options">인천</div>
            <div class="location_options">대전</div>
            <div class="location_options">대구</div>
            <div class="location_options">부산</div>
            <div class="location_options">울산</div>
            <div class="location_options">광주</div>
            <div class="location_options">강원</div>
            <div class="location_options">세종</div>
            <div class="location_options">충북</div>
            <div class="location_options">충남</div>
            <div class="location_options">경북</div>
            <div class="location_options">경남</div>
            <div class="location_options">전북</div>
            <div class="location_options">전남</div>
            <div class="location_options">제주</div>
          </div>
        </div>
        <!-- Beginning of time Selection-->
        <div id="time">
          <div id="time_selection_wrapper">
            <input
              type="text"
              id="time_selection"
              class="opener"
              readonly
              placeholder="시간대 ▽"
            />
          </div>
          <div id="time_option_wrapper" class="option_wrappers">
            <div class="time_options">무관&nbsp;</div>
            <div class="time_options">평일 낮&nbsp;</div>
            <div class="time_options">평일 저녁&nbsp;</div>
            <div class="time_options">주말 낮&nbsp;</div>
            <div class="time_options">주말 저녁&nbsp;</div>
          </div>
        </div>
        <!-- End of time Selection-->
        <!-- Beginning of theme Selection-->
        <div id="theme">
          <div id="theme_selection_wrapper">
            <input
              type="text"
              id="theme_selection"
              class="opener"
              readonly
              placeholder="주제 ▽"
            />
          </div>
          <div id="theme_option_wrapper" class="option_wrappers">
            <div class="theme_options">모각코&nbsp;</div>
            <div class="theme_options">Algorithm&nbsp;</div>
            <div class="theme_options">Java&nbsp;</div>
            <div class="theme_options">Python&nbsp;</div>
            <div class="theme_options">C&nbsp;</div>
            <div class="theme_options">C++&nbsp;</div>
            <div class="theme_options">C#&nbsp;</div>
            <div class="theme_options">JavaScript&nbsp;</div>
            <div class="theme_options">React&nbsp;</div>
            <div class="theme_options">Vue&nbsp;</div>
            <div class="theme_options">Spring&nbsp;</div>
            <div class="theme_options">SpringBoot&nbsp;</div>
            <div class="theme_options">SQL&nbsp;</div>
            <div class="theme_options">iOS&nbsp;</div>
            <div class="theme_options">Android&nbsp;</div>
            <div class="theme_options">FrontEnd&nbsp;</div>
            <div class="theme_options">BackEnd&nbsp;</div>
            <div class="theme_options">Toy Project&nbsp;</div>
            <div class="theme_options">머신 러닝&nbsp;</div>
          </div>
        </div>
        <!-- End of theme Selection-->
        <!-- Beginning of Career-->
        <div id="career">
          <div id="career_selection_wrapper">
            <input
              type="text"
              id="career_selection"
              class="opener"
              readonly
              placeholder="경력 ▽"
            />
          </div>
          <div id="career_option_wrapper" class="option_wrappers">
            <div class="career_options">무관&nbsp;</div>
            <div class="career_options">0~2년&nbsp;</div>
            <div class="career_options">3~5년&nbsp;</div>
            <div class="career_options">5년 이상&nbsp;</div>
            <div class="career_options">10년 이상&nbsp;</div>
          </div>
        </div>
        <!-- End of Career-->
        <!-- Beginning of People-->
        <div id="people">
          <div id="people_selection_wrapper">
            <input
              type="text"
              id="people_selection"
              class="opener"
              readonly
              placeholder="희망인원 ▽"
            />
          </div>
          <div id="people_option_wrapper" class="option_wrappers">
            <div class="people_options">무관&nbsp;</div>
            <div class="people_options">&nbsp; ~ 3명&nbsp;</div>
            <div class="people_options">4 ~ 6명&nbsp;</div>
            <div class="people_options">7 ~ 9명&nbsp;</div>
            <div class="people_options">10명 이상&nbsp;</div>
          </div>
        </div>
        <!-- End of Career-->
        <button type="button" id="insert_match_button">추가하기</button>
      </div>
      <!-- End of option_boss-->
      
	    <!-- Wish List Div-->
	    <div id="wish_wrapper">
	    	<div class="wishes">
	    		<input type="text" class="wishElements" readonly value="지역"/>
	    		<div>
	    		<input type="text" class="wishElements" readonly />
	    			<span class="wishSelected">호호</span>
	    			<span class="wishSelected">호호호호호호호</span>
    			</div>
    			<div>
	    			<input type="text" class="wishElements" readonly />
	    		</div>
	    		<div>
	    			<input type="text" class="wishElements" readonly />
	    		</div>
	    		<div>
	    			<input type="text" class="wishElements" readonly />
	    		</div>
	    		<button type="button" class="deleteButton_in_match">삭제</button>
	    	</div>
	    	
	    </div>

		<!-- End of Wish List Div -->
    </div>
    <!--End of Body Wrapper-->
    <%@ include file="/template/footer.jsp"%>
  </body>
</html>
