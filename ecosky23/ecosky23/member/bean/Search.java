package ecosky23.member.bean;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class Search extends Pagination{
	private String searchType;
	private String keyword;	
}
