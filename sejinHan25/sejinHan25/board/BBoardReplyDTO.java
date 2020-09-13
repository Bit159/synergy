package sejinHan25.board;

import java.util.Date;

import lombok.Data;

@Data
public class BBoardReplyDTO {
	private int rno;
	private int bno;
	private String reply;
	private String nickname;
	private Date replydate;
	private Date updatedate;
	
	@Override
	public String toString() {
		return rno+" "+bno+" "+reply+" "+nickname;
	}
}
