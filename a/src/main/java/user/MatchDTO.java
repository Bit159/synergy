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
	private String time1;
	private String time2;
	private String time3;
	private String topic1;
	private String topic2;
	private String topic3;
	private int career;
	private int people;
	private Date created;
	private Date updated;
	
	@Override
	public String toString() {
		return email+" "+x+" "+y+" "+range+" "+time1+" "+time2+" "+time3+" "+topic1+" "+topic2+" "+topic3+" "+career+" "+people;
	}
}
