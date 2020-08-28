package notifier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import user.Email;
import user.UserDAO;


//10분 간격으로 알림 발송해야할 목록을 가져와서 메일 발송하는 클래스
//비동기 처리하여 서버 내 지연을 줄여야하기 때문에 또다른 스케쥴 비동기와 다른 객체에서 선언
@Service
public class Notifier {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private Email email;
	
	@Async
	@Scheduled(cron = "0 0/10 * * * *")
	public void getOnTimeList() {
		System.out.println("현재 DB 서버시간 :" + userDAO.getDBTime());
		List<NotDTO> onTimeList = userDAO.getOnTimeList();
		System.out.println(onTimeList);
		NotDTO d = onTimeList.get(0);
		email.send(d.getUsername(), d.getTitle(), d.getContent()+"\r\n\r\n"+d.getPlace()+d.getTime());
	}

	public static void main(String[] args) {
		System.out.println(new Date());
	}
}
