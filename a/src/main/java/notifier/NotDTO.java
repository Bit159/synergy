package notifier;

import java.util.Date;
import org.springframework.stereotype.Service;
import lombok.Data;

@Service
@Data
public class NotDTO {
	private String username;
	private Date time;
	private String place;
	private String title;
	private String content;
	private String link;
	private Date created;
	private Date updated;
}
