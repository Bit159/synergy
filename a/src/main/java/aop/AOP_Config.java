package aop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import user.CBoardDTO;
import user.Email;
import user.MatchDTO;
import user.UserDAO;

@Service
@Aspect
public class AOP_Config {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private Email email;
	
	@Async
	@After("execution(public * crawl.Crawler.getList(..))")
	public void getContent() {
		System.out.println("글 내용 삽입을 위한 애프터");
		List<String> list = userDAO.getEmptyContentBno();
		if (list.size() == 0)
			return;

		List<CBoardDTO> insertList = new ArrayList<CBoardDTO>();

		for (int i = 0; i < list.size(); i++) {
			String url = "https://okky.kr/article/" + list.get(i);
			String selector = "article.content-text";

			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			CBoardDTO cboardDTO = new CBoardDTO();
			cboardDTO.setBno(Integer.parseInt(list.get(i)));
			
			//.text() 에서 .outerHtml로 변경하여 태그를 유지한다.
			cboardDTO.setContent(doc.select(selector).outerHtml());
			insertList.add(cboardDTO);
		}

		int result = 0;
		try {
			result = userDAO.insertContents(insertList);
		}catch (Exception e) {
			email.send("jpcnani@naver.com", "글 내용 INSERT 입력중 에러 발생!", "글 내용을 등록중 에러가 발생하였습니다."+e.getMessage());
			e.printStackTrace();
			return;
		}
		if(result != 0) {
			email.send("jpcnani@naver.com", result+"개 글 내용 INSERT 성공!", result+"개의 글 내용을 정상적으로 등록하였습니다.");
		}
	}
	
	@Async
	@AfterReturning("execution(public * controller.HomeController.insertMatch(..))")
	public void after() {
		System.out.println("---------------------------------------------------------");
		System.out.println("match db 삽입 후 매칭 검증을 위한 after Returning");
		List<MatchDTO> list = userDAO.getListFromMatch();
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("포문 " + (i+1) + "바퀴째");
			//1. 지역 검증+인원 검증
			List<MatchDTO> range_validated_list = rangeValidation(list.get(i), list);
			if(range_validated_list.size() < list.get(i).getPeople()) continue;
			System.out.println("지역검증+인원검증 통과");
			
			//2. 경력 검증+인원 검증
			List<MatchDTO> career_validated_list = careerValidation(list.get(i), range_validated_list);
			if(career_validated_list.size() < list.get(i).getPeople()) continue;
			System.out.println("경력검증 통과\r\n\r\n");
			
			//3. 시간대 검증 : 최대 3개의 리스트를 담은 리스트를 반환. 그 중 인원수 검증에 실패한 것은 제거. 
			List<List<MatchDTO>> time_validated_list_set = timeValidation(list.get(i), career_validated_list);
			if(time_validated_list_set.size()==0) continue;
			System.out.println("시간대 검증 통과");

			//4. 주제 검증
			List<MatchDTO> fullValidatedList = topicValidation(list.get(i), time_validated_list_set);
			if(fullValidatedList == null) {
				System.out.println("fullValidatedList is null");
				System.out.println("----------------------------------------------------------\r\n\r\n");
				continue;
			}
			System.out.println("주제 검증까지 통과");
			
			//검증 완료 후 작업
			System.out.println("매칭 발생~");
			List<String> mailList = new ArrayList<String>();
			for (MatchDTO dto : fullValidatedList) {
				System.out.println(dto.toString());
				mailList.add(dto.getUsername());
			}
			System.out.println("메일발송대상 : "+mailList);
			//email.send(mailList);
			
			//db삭제
			int result = userDAO.deleteMatched(fullValidatedList);
			System.out.println(result +"행 삭제 완료");
			break;
			//메일 발송
			//email.sendToList(targetList);
			//채팅방개설?
			//알림 발송?
		} // for
	}// after method
	
	
	//주제 검증
	public List<MatchDTO> topicValidation(MatchDTO standardDTO, List<List<MatchDTO>> topic_validation_list_set) {
		List<MatchDTO> return_list = new ArrayList<MatchDTO>();
		List<String> standard_topics = new ArrayList<String>();
		standard_topics.add(standardDTO.getTopic1());
		if(standardDTO.getTopic2()!=null) standard_topics.add(standardDTO.getTopic2());
		if(standardDTO.getTopic3()!=null) standard_topics.add(standardDTO.getTopic3());
		
		for(int i = 0; i < standard_topics.size(); i++) {
			List<MatchDTO> list = new ArrayList<MatchDTO>();
			for (int j = 0; j < topic_validation_list_set.size(); j++) {
				for (int j2 = 0; j2 < topic_validation_list_set.get(j).size(); j2++) {
					if(standard_topics.get(i).equals(topic_validation_list_set.get(j).get(j2).getTopic1()))
						list.add(topic_validation_list_set.get(j).get(j2));
					if(standard_topics.get(i).equals(topic_validation_list_set.get(j).get(j2).getTopic2()))
						list.add(topic_validation_list_set.get(j).get(j2));
					if(standard_topics.get(i).equals(topic_validation_list_set.get(j).get(j2).getTopic3()))
						list.add(topic_validation_list_set.get(j).get(j2));
				}
			}
			if(list.size() >= standardDTO.getPeople()) return list;
		}
		return null;
	}

	//시간대 검증
	public List<List<MatchDTO>> timeValidation(MatchDTO standardDTO, List<MatchDTO> time_validation_list){
		//옵션이 3개라서 매칭결과 리스트도 최대 3개가 나올 수 있기 때문에 리턴할 "리스트를 담는 리스트"를 생성
		List<List<MatchDTO>> return_list = new ArrayList<List<MatchDTO>>();
		//기준이 될 dto에서 time옵션 최대 3가지를 null이 아닌 경우 list에 담아서 size를 활용할 수 있게 세팅
		List<String> standard_times = new ArrayList<String>();
		standard_times.add(standardDTO.getTime1());
		if(standardDTO.getTime2() != null) standard_times.add(standardDTO.getTime2());
		if(standardDTO.getTime3() != null) standard_times.add(standardDTO.getTime3());
		System.out.println("standard_time1 : " + standardDTO.getTime1());
		System.out.println("standard_time2 : " + standardDTO.getTime2());
		System.out.println("standard_time3 : " + standardDTO.getTime3());
		//기준 옵션의 갯수만큼 반복하며 리스트내에서 같은 옵션이 있는 위시들을 리스트에 담아 "리스트를 담는 리스트"에 담는다.
		for (int i = 0; i < standard_times.size(); i++) {
			//같은 옵션이 있는 위시디를 담는 리스트
			List<MatchDTO> list = new ArrayList<MatchDTO>();
			for (int j = 0; j < time_validation_list.size(); j++) {
				System.out.println((j+1) + "번째 요소의 첫번째 시간대" +time_validation_list.get(j).getTime1());
				System.out.println((j+1) + "번째 요소의 두번째 시간대" +time_validation_list.get(j).getTime2());
				System.out.println((j+1) + "번째 요소의 세번째 시간대" +time_validation_list.get(j).getTime3());
				if(standard_times.get(i).equals(time_validation_list.get(j).getTime1()))
					list.add(time_validation_list.get(j));
				if(standard_times.get(i).equals(time_validation_list.get(j).getTime2()))
					list.add(time_validation_list.get(j));
				if(standard_times.get(i).equals(time_validation_list.get(j).getTime3()))
					list.add(time_validation_list.get(j));
			}
			System.out.println(list);
			if(list.size() >= standardDTO.getPeople()) return_list.add(list);
		}
		return return_list;
	}
	
	//경력 검증 : 비교기준 career보다 mycareer값이 더 큰 위시들을 리스트에 담아서 리턴
	public List<MatchDTO> careerValidation(MatchDTO standardDTO, List<MatchDTO> career_validation_list){
		List<MatchDTO> return_list = new ArrayList<MatchDTO>();
		for (int i = 0; i < career_validation_list.size(); i++) {
			if(career_validation_list.get(i).getMycareer() >= standardDTO.getCareer())
				return_list.add(career_validation_list.get(i));
		}
		return return_list;
	}

	//지역 검증 : 비교기준 x,y,range 와 접면이 있는 위시들을 리스트에 담아서 리턴
	//기준, 전체리스트, 인덱스, 예비리스트 준비
	//주어진 인덱스부터 돌면서(처음엔0을 주어서 자기자신이 예비리스트에 담겨지도록 한다) 전체리스트에서 기준과 접면이 있는 녀석을 찾는다.
	//재귀가 무조건 필요한거같다. 
	//1번(기준) 으로 돌다가
	//3번에서 1번과 매칭이 된다 하면.
	// 1-3 에다가 추가로 되는애들 다 찾고
	// 찾을때 인원수 조건도 동시에 갖춰서 찾자.
	// 1 3 조합으로 못찾는다.
	// 그러면 1 4 부터 다시 시작해서 다시 돌리기..
	
	public List<MatchDTO> rangeValidation(MatchDTO standardDTO, List<MatchDTO> sourceList, int index) {
		//후보리스트 생성
		List<MatchDTO> candidateList = new ArrayList<>();
		//후보리스트에 기준값 먼저 넣고.
		candidateList.add(standardDTO);
		
		for (int i = index+1; i < sourceList.size(); i++) {
			double sumOfTwoRanges = (standardDTO.getRange() + sourceList.get(i).getRange()) / 1000.0;
			double distance = distanceInKmBetweenEarthCoordinates(standardDTO.getX(), standardDTO.getY(),
					sourceList.get(i).getX(), sourceList.get(i).getY());
			if (sumOfTwoRanges >= distance) {
				for (int j = 0; j < candidateList.size(); j++) {
					sumOfTwoRanges = (standardDTO.getRange() + sourceList.get(i).getRange()) / 1000.0;
					distance = distanceInKmBetweenEarthCoordinates(standardDTO.getX(), standardDTO.getY(),
							sourceList.get(i).getX(), sourceList.get(i).getY());
					
				}
			}
		}
		return null;
	}

	
	
	public double degreesToRadians(double degrees) {
		return (degrees * Math.PI) / 180;
	}

	public double distanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
		int earthRadiusKm = 6371;

		double dLat = degreesToRadians(lat2 - lat1);
		double dLon = degreesToRadians(lon2 - lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earthRadiusKm * c;
	}

//	@Before("execution(public * sample01.MessageBeanImpl.*Before(..))")
//	public void beforeTrace() {
//		System.out.println();
//		System.out.println("before 어드바이스 삽입!");
//		System.out.println("------------------------------------");
//	}

//	@Around("execution(public * *.*.*Print(..))")
//	public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.println();
//		System.out.println("around 시작~");
//		String methodName = joinPoint.getSignature().toShortString();
//		System.out.println("현재 메소드 : "+methodName);
//		
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start(methodName);
//		
//		String result = (String) joinPoint.proceed();
//		System.out.println(result);
//		stopWatch.stop();
//		System.out.println(methodName+" 소요 시간 : "+stopWatch.getTotalTimeMillis()+"밀리초");
//		System.out.println("around 끝!");
//		System.out.println();
//	}

}
