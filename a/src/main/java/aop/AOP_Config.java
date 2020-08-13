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
			cboardDTO.setContent(doc.select(selector).text());

			insertList.add(cboardDTO);
		}

		userDAO.insertContents(insertList);
		email.send("jpcnani@naver.com", "글내용 크롤링 성공!");
	}
	
	@Async
	@AfterReturning("execution(public * controller.HomeController.insertMatch(..))")
	public void after() {
		System.out.println("afterreturning 어드바이스 삽입!");
		List<MatchDTO> list = userDAO.getListFromMatch();
		List<MatchDTO> matchedList = null;
		boolean matched = false;
		
		for (int i = 0; i < list.size() - 4; i++) {
			System.out.println("현재 리스트 길이입니다 : " + list.size());
			matchedList = new ArrayList<MatchDTO>();
			matchedList.add(list.get(i));
			
			for (int j = i + 1; j < list.size(); j++) {
				double sumOfTwoRanges = (list.get(i).getRange() + list.get(j).getRange()) / 1000.0;
				double distance = distanceInKmBetweenEarthCoordinates(list.get(i).getX(), list.get(i).getY(),
						list.get(j).getX(), list.get(j).getY());

				if (sumOfTwoRanges >= distance) {
					matchedList.add(list.get(j));
				} 
				
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
