package ecosky23.member.bean;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.Data;
@Service
@Data
public class MemberDTO {
	   private String username;
	   private String nickname;
	   private String password;
	   private String auth;
	   private String enabled;
	   private int mycareer;
	   private Date created;
	   
	   private int github;
	   private int google;
	   private int kakao;
}
