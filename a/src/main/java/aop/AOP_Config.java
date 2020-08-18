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
		System.out.println("애프터 적용!");
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
			
			
			
			System.out.println(cboardDTO.getContent());
			
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
		System.out.println("match db 삽입 후 매칭 검증을 위한 after Returning");
		List<MatchDTO> list = userDAO.getListFromMatch();
		List<MatchDTO> matchedList = null;
		boolean matched = false;
		
		for (int i = 0; i < list.size() - 4; i++) {
			System.out.println("현재 리스트 길이입니다 : " + list.size());
			matchedList = new ArrayList<MatchDTO>();
			boolean location_pass = false;
			boolean time_pass = false;
			boolean topic_pass = false;
			boolean career_pass = false;
			boolean people_pass = false;
			
			matchedList.add(list.get(i));
			
			//outer for문에서 선택된 i번째 요소와 
			//inner for문에서 선택된 j번째 요소가 매칭이 되는지 체크한 후
			//매칭이 될 경우( _pass 불리언이 모두 true일때 )

			for (int j = i + 1; j < list.size(); j++) {
				// 1. 지역 매칭 검증
				double sumOfTwoRanges = (list.get(i).getRange() + list.get(j).getRange()) / 1000.0;
				double distance = distanceInKmBetweenEarthCoordinates(list.get(i).getX(), list.get(i).getY(),
						list.get(j).getX(), list.get(j).getY());
				if (sumOfTwoRanges >= distance)	location_pass = true;
				else continue;
				// 1. 지역 매칭 검증 끝
				
				// 2. 커리어 검증 : 무관 - 0 | 0~2년 - 2 | 3~5년 - 5 | 5년이상 - 6 | 10년이상 - 10
				// 회원가입할때 자신의 연차를 적도록 하고, 해가 바뀔 때마다 모든 테이블컬럼의 값을 1씩 더해주도록 해야겠다.
				// 매칭리스트에 넣을때 경력은 상대방의 경력이 어느정도 되어야 한다는 설정을 넣는 것으로 정하자.
				// 기준값인 i가 무관을 넣었을 땐 j값과 상관없이 
				if(list.get(i).getCareer())
				
				
				
				//matchedList.add(list.get(j));
				if(matchedList.size() >= 5) {
					matched = true;
					break;
				}

			} // inner for

			if(matched) {
				List<String> targetList = new ArrayList<String>();
				for (MatchDTO dto : matchedList) targetList.add(dto.getEmail());
				email.sendToList(targetList);
				int result = userDAO.deleteMatched(matchedList);
				return;
			}

		} // outer for
	}// after method

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
