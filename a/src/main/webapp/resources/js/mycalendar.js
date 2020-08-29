let myData = new Array();
let schedules = new Array();
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
		console.log(myData);
		myData.forEach((e)=>{
		    var sc = new Object();
			var d = new Date(e.time.time);
		    sc.title = e.title;
			var month = ((d.getMonth()+1) < 10) ? '0'+(d.getMonth()+1) : (d.getMonth()+1);
			var dd = (d.getDate() < 10) ? '0'+d.getDate() : d.getDate();
		    sc.start = d.getFullYear()+'-'+month+'-'+dd;
			console.log(sc);
			schedules.push(sc);
		});
		console.log(schedules);
		
		
  var 	calendarEl = document.getElementById("calendar");

  var calendar = new FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
      left: "prevYear,prev,next,nextYear today",
      center: "title",
      right: "dayGridMonth,timeGridWeek,timeGridDay,listDay,listWeek,listMonth",
    },
    initialDate: "2020-06-12",
    locale: "ko",
    navLinks: true, // can click day/week names to navigate views
    businessHours: true, // display business hours
    editable: true,
    selectable: true,
    selectMirror: true,
    select: function (arg) {
      var title = prompt("Event Title:");

      if (title) {
        calendar.addEvent({
          title: title,
          start: arg.start,
          end: arg.end,
          allDay: arg.allDay,
        });
      }
      calendar.unselect();
    },
    eventClick: function (arg) {
      if (confirm("Are you sure you want to delete this event?")) {
        arg.event.remove();
      }
    },
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
