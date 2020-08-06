package user;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class UserDTO {
	private int seq;
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private int birthyear;
	private int google;
	private int kakao;
	private int github;
	
	@Override
	public String toString() {
		return id+" "+pw+" "+nickname+" "+email;
	}
}
