package member.bean;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pwd;
	private String name,gender,email1,email2,tel1,tel2,tel3,zipcode,addr1,addr2;
	private Date logtime;
}
