package ecosky23.member.service;



import java.util.List;
import java.util.Map;

import ecosky23.member.bean.MatchDTO;
import ecosky23.member.bean.MemberDTO;
import ecosky23.member.bean.ProgrammingDTO;
import ecosky23.member.bean.Search;
import ecosky23.member.bean.TotalDTO;

public interface MemberService {

	public MemberDTO login(String id, String pwd);

	public void join(Map<String, String> map);

	public void withdrawal(String username);

	public MemberDTO getMyPage(Map<String, String> map);

	public void revise(Map<String, String> map);

	public void nicknameRevice(Map<String, String> map);

	public List<MemberDTO> getMember();

	public void memberDelete(String username);

	public List<MemberDTO> getBoardList();

	

	

	public List<TotalDTO> getTotalStats();

	public List<ProgrammingDTO> getProgrammingStats();

	public List<MatchDTO> getListFromMatch();

	public int getBoardListCnt(Search search);

	public List<MemberDTO> getBoardList(Search search);

	public List<MemberDTO> getWithdrawalList(String username);

	public List<MemberDTO> getNickName(String nickname);

	public void passwordRevise(Map<String, String> map);

	

	

	

	
}
