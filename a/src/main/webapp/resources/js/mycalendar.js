let myData = new Array();
let schedules = new Array();

/*Read : 페이지 로드했을 때 db에서 값 가져와서 달력그리기 */
document.addEventListener("DOMContentLoaded", function () {

/*작성한 코드*/
  let url = "/getSchedules";
  let options = {
    method: "POST",
    headers: {
      "X-CSRF-TOKEN": document.getElementById("csrf").content,
      Accept: "application/json",
      "Content-Type": "application/json; charset=utf-8",
    },
  };
  fetch(url, options).then((res) => res.json().then((json) => {
		myData = json;
		myData.forEach((e)=>{
		    var sc = new Object();
			var d = new Date(e.time.time);
		    sc.title = e.title;
			var month = ((d.getMonth()+1) < 10) ? '0'+(d.getMonth()+1) : (d.getMonth()+1);
			var dd = (d.getDate() < 10) ? '0'+d.getDate() : d.getDate();
		     sc.start = d.getFullYear()+'-'+month+'-'+dd;
			sc.allDay = false;
			sc.place = e.place;
			sc.no = e.no;
			schedules.push(sc);
		});
		
		
	  var calendarEl = document.getElementById("calendar");
	
	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    headerToolbar: {
	      left: "prevYear,prev,next,nextYear today",
	      center: "title",
	      right: "dayGridMonth,timeGridWeek,timeGridDay,listMonth",
	    },
	    initialDate: new Date(),
	    locale: "ko",
	    navLinks: true, // can click day/week names to navigate views
	    businessHours: true, // display business hours
	    editable: true,
	    selectable: true,
	    selectMirror: true,
	    select: function (arg) {createSchedule(arg, this)},
	    eventClick: function (arg) {myEventClick(arg, this)},
	    editable: true,
	    dayMaxEvents: true, // allow "more" link when too many events
	    events: schedules,
	  });

/*
[
      {
        title: "Business Lunch",
        start: "2020-06-03",
        constraint: "businessHours",
      },
      {
        title: "Meeting",
        start: "2020-06-13T11:00:00",
        constraint: "availableForMeeting", // defined below
        color: "#257e4a",
      },
      {
        title: "Conference",
        start: "2020-06-18",
        end: "2020-06-20",
      },
      {
        title: "Party",
        start: "2020-06-29T20:00:00",
      },

      // areas where "Meeting" must be dropped
      {
        groupId: "availableForMeeting",
        start: "2020-06-11T10:00:00",
        end: "2020-06-11T16:00:00",
        display: "background",
      },
      {
        groupId: "availableForMeeting",
        start: "2020-06-13T10:00:00",
        end: "2020-06-13T16:00:00",
        display: "background",
      },

      // red areas where no events can be dropped
      {
        start: "2020-06-24",
        end: "2020-06-28",
        overlap: false,
        display: "background",
        color: "#ff9f89",
      },
      {
        start: "2020-06-06",
        end: "2020-06-08",
        overlap: false,
        display: "background",
        color: "#ff9f89",
      },
    ]
*/
  calendar.render();
	}
	)).catch((e)=>console.log(e));
/*작성한 코드 끝*/  
});
/*Read 끝 : 페이지 로드했을 때 db에서 값 가져와서 달력그리기 */





/*일정 클릭했을 때의 이벤트 */
function myEventClick(arg, target) {
	//html: 속성으로 직접 넣기, customClass: 속성으로 클래스값 넣기 onOpen: 속성으로 열렸을때 특정 펑션 수행하기 가능
	/*
       html:true,
    showCancelButton: true,
    confirmButtonClass: "btn-success",
    confirmButtonText: "Confirm",
    cancelButtonText: "Cancel",
    closeOnConfirm: false,
    closeOnCancel: false,
    showLoaderOnConfirm: true
	*/
	swal({
	  title: "일정 제거",
	  text: "이 일정을 제거하시겠습니까?",
	  icon: "warning",
	  buttons: ["취소", "제거"],
	  dangerMode: true,
	}).then((willDelete) => {
	  if (willDelete) {
		let no = arg.event._def.extendedProps.no;
		let url = "/deleteSchedule";
		let options = {
			method: "POST",
			headers: {
				"X-CSRF-TOKEN": document.getElementById("csrf").content,
				Accept: "application/json",
				"Content-Type": "application/json; charset=utf-8",
			},
			body: no
		};
		fetch(url, options).then((res)=>res.text().then((text)=>{
			if(text==='1')	document.querySelectorAll('input.no').forEach((e)=>{
				if(e.innerText==no) e.parentElement.parentElement.remove();	});
		}));
	    swal("일정 제거가 완료되었습니다", {
	      icon: "success",
	    });
	  } else {
	    swal("일정 제거가 취소되었습니다");
	  }
	});
}
/*End of 일정 클릭했을 때의 이벤트 */

/*일정 생성하기 이벤트*/
function createSchedule(arg, calendar) {
	/*
	swal("제목", "내용", "warning",{
	  content: {
	    element: "input",
	    attributes: {
	      placeholder: "Type your password",
	      type: "datetime",
	    },
	  },
	}).then((result)=>console.log(result));
	*/
	
	console.log(arg);
	console.log(calendar);
	//alert는 삭제했음.
	//그냥 1일전, 2시간전, 15분전 이렇게 세번 알림 그냥 보내는걸로 고정 가자.
	let ob = new Object();
	ob.username = 'jpcnani@naver.com';
	ob.time = arg.start.getTime();
	ob.place = prompt("Place");;
	ob.title = prompt("Title:");
	ob.content = prompt("Content:");
	ob.created = new Date().getTime();
	ob.updated = new Date().getTime();
	//유효성검증 추가해야할 부분
	if(ob.place.length===0 || ob.title.length===0 || ob.content.length===0) return;


	let url = "/createSchedule";
	let options = {
					method: "POST",
					headers: {
								"X-CSRF-TOKEN": document.getElementById('csrf').content,
								Accept: "application/json",
								"Content-Type": "application/json; charset=utf-8",
							},
					body: JSON.stringify(ob),
				};
	fetch(url, options).then((res)=>res.text().then((text)=>{
			let no = parseInt(text);
				calendar.addEvent({
				title: ob.title,
				start: arg.start,
				end: arg.end,
				/*allDay: arg.allDay,*/
				
				allDay: false,
				username: ob.username,
				time: ob.time,
				place: ob.place,
				content: ob.content,
				created: ob.created,
				updated: ob.updated,
				no: no
			});
	}));
		
	calendar.unselect();
	completeAlert();
	
}
/* End of 일정 생성하기 이벤트*/ 

function completeAlert() {
	swal({
		title: "등록 완료",
		text: "정상적으로 등록되었습니다",
		icon: "success",
		button: "완료",
	});
}

  function removeConfirm() {
    swal({
      title: "삭제하시겠습니까?",
      text: "삭제 후엔 되돌릴 수 없습니다",
      icon: "warning",
      buttons: ["취소", "삭제"],
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("삭제되었습니다", {
          icon: "success",
        });
      } else {
        swal("삭제가 취소되었습니다");
      }
    });
  }