package member.bean;

import lombok.Data;

@Data
public class MemberDTO {
	private int seq;
	private int birthYear;
	private int github;
	private int google;
	private int kakao;
	
	private String email;
	private String id;
	private String nickname;
	private String pw;
	private String auth;
}
