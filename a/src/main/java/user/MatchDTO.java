package user;

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

	
	@Override
	public String toString() {
		return email+" "+x+" "+y+" "+range;
	}
}