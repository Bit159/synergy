package sejinHan25.board;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
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
		return bno+" "+title+" "+nickname+" "+boarddate;
	}
}
