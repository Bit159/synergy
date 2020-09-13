package richard.notifier;

import java.util.Date;
import org.springframework.stereotype.Service;
import lombok.Data;

@Service
@Data
public class NotDTO {
	//alert는 삭제했음.
	//그냥 1일전, 2시간전, 15분전 이렇게 세번 알림 그냥 보내는걸로 고정 가자.
	private int no;
	private int group;
	private String username;
	private Date start;
	private Date end;
	private String title;
	private String place;
	private String content;
	private Date created;
}
