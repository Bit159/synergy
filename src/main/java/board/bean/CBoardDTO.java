package board.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CBoardDTO {
	private int bno;
	private String topic;
	private String title;
	private String content;
	private int replys;
	private int hit;
	private String nickname;
	private Date boarddate;
	
	@Override
	public String toString() {
		return bno+" "+topic+" "+title+" "+content;
	}
}
