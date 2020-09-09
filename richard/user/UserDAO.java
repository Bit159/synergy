package user;

import java.util.Date;
import java.util.List;

import notifier.NotDTO;

public interface UserDAO {

	//지도용
	public abstract int insertMatch(MatchDTO matchDTO);
	public abstract List<MatchDTO> getListFromMatch();
	public abstract int deleteMatched(List<MatchDTO> list);
	public abstract int deleteMatch(MatchDTO matchDTO);
	public abstract int getMycareer(String username);
	
	//크롤링용
	public abstract int crawlInsert(List<CBoardDTO> list);
	public abstract List<String> getEmptyContentBno();
	public abstract int insertContents(List<CBoardDTO> list);
	public abstract int getGreatestBno();
	
	//크롤게시판용
	public abstract List<CBoardDTO> getBoardList();
	
	//알림용
	public abstract List<NotDTO> getOnTimeList();
	public abstract Date getDBTime();
	
	//스케쥴용
	public abstract int getGreatestNo();
	public abstract int createSchedule(NotDTO dto);
	public abstract List<NotDTO> getSchedules();
	public abstract int updateSchedule(NotDTO dto);
	public abstract int removeSchedule(int no);
}
