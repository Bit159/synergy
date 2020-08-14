package user;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Setter
@Getter
public class MatchDTO {
	
	private String email;
	private Double x;
	private Double y;
	private Double range;
	private String time;
	private String topic;
	private int career;
	private int people;
	private Date created;
	private Date updated;
	
	@Override
	public String toString() {
		return email+" "+x+" "+y+" "+range+" "+time+" "+topic+" "+career+" "+people;
	}
}
