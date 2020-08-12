package member.bean;

import lombok.Data;

@Data
public class MemberDTO {
	private String username;
	private String nickname;
	private String password;
	private String auth;
	private String enabled;
	private String birthYear;
	
	private int github;
	private int google;
	private int kakao;
	
}
