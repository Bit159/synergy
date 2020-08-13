package user;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
/* JSON으로 가져와서 db에 넣어야하기 때문에.... enum 은 쓰기 어려울 것 같다는 결론....
enum Time{
	WEEKDAY_AFTERNOON, WEEKDAY_EVENING, WEEKEND_AFTERNOON,WEEKEND_EVENING
}
enum Topic {
	MOGAKKO, ALGORHYTHM, JAVA, PYTHON, C, C_SHARP, C_PLUSPLUS, JAVASCRIPT, REACT, VUE, SPRING, SPRINGBOOT, SQL, IOS, ANDROID, FRONTEND, BACKEND, TOYPROJECT, MACHINELEARNING
}
enum Career {
	NO_MATTER(0), O_TO_TWO_YEARS(2), THREE_TO_FIVE_YEARS(5), OVER_FIVE_YEARS(6);
	private final int value;
	Career(int value) {this.value = value;}
	public int value() {return this.value;}
}
enum People {
	NO_MATTER(0), THREE_TO_FIVE(5), SIX_TO_EIGHT(8), OVER_NINE(9);
	private final int value;
	People(int value) {this.value = value;}
	public int value() {return this.value;}
}
*/
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
