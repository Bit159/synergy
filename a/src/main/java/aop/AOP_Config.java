package aop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@AfterReturning("execution(public * controller.HomeController.insertMatch(..))")
	public void after() {
		System.out.println("afterreturning 어드바이스 삽입!");
		List<MatchDTO> list = userDAO.selectAllFromMatch();
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
					System.out.println("distance 보다 range가 큽니다!! 현재 매치인원수 : " +matchedList.size());
				} // when sum of ranges is bigger than distance
				
				if(matchedList.size() >= 5) {
					System.out.println("5인이 모여서 브레이크합니다");
					matched = true;
					break;
				}

			} // inner for

			if(matched) {
				System.out.println("삐뽀 삐뽀 매치 발생!");
				List<String> targetList = new ArrayList<String>();
				for (MatchDTO dto : matchedList) targetList.add(dto.getEmail());
				email.sendToList(targetList);
				int result = userDAO.deleteMatched(matchedList);
				System.out.println("메일 발송 완료. 삭제한 rows : " + result);
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
