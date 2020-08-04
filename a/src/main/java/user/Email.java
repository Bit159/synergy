package user;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Email {

	private final String userName;
	private final String password;
	private final Properties prop;
	private final Session session;

	// 생성자. bean 등록 될 때부터 발송을 위한 기본 설정을 완료하도록 설정.
	public Email() {
		this.userName = "synergy.bit159";
		this.password = "bitcamp159";

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		this.prop = prop;

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		this.session = session;
	}

	// 리스트 발송 메소드.
	public int sendToList(List<String> list) {
		if (list.size() == 0) {
			System.out.println("리스트가 비었습니다");
			return 0;
		}
		send(list);
		return list.size();
	}

	// 리스트 메일 발송 메소드
	public void send(List<String> list) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setSubject("매치가 완료되었습니다!"); // 메일 제목을 입력
			message.setText("안녕하세요 시너지입니다. 매치가 완료되었으니 마이페이지에서 확인바랍니다."); // 메일 내용을 입력
			InternetAddress[] addresses = new InternetAddress[list.size()]; // 수신자 배열 생성
			for (int i = 0; i < list.size(); i++) { // 수신자 배열에 주소 주입
				addresses[i] = new InternetAddress(list.get(i));
			}
			Transport.send(message, addresses); // 전송
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 단일 메일 발송 메소드
	public void send(String email) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName)); // 발신자 설정
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 수신자 설정
			message.setSubject("매치가 완료되었습니다!"); // 메일 제목을 입력
			message.setText("안녕하세요 시너지입니다. 매치가 완료되었으니 마이페이지에서 확인바랍니다."); // 메일 내용을 입력
			Transport.send(message); // 전송
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}