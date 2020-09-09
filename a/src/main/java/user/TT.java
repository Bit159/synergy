package user;

import java.util.ArrayList;
import java.util.List;

public class TT {
	
	public List<MatchDTO> rangeValidation(List<MatchDTO> listFromMatch) { // match 테이블 전체 리스트를 파라메타로 받는다.
		for (int i = 0; i < listFromMatch.size(); i++) { // 리스트를 돌면서 한 명씩 돌아가면서 기준이 되어 검증한다.
			List<MatchDTO> candidateList = new ArrayList<>(); // 후보들을 담을 리스트. 후보들의 숫자가 people 숫자보다 크면 당선이다.
			candidateList.add(listFromMatch.get(i)); //기준이 될 녀석이 후보리스트의 첫번째 요소가 된다.
			List<MatchDTO> sourceList = new ArrayList<>();
			sourceList = listFromMatch;
			sourceList.remove(i); //기준이 되는 녀석은 검증할 리스트에서 제거한다.
			for (int j = 0; j < sourceList.size(); j++) { 
				boolean rangeMatchWithAllCandidates = true;
				//기준이 빠진 리스트를 돌며 후보리스트에 있는 모든 요소와 접면이 있는 요소를 후보리스트에 추가한다.
				for (int k = 0; k < candidateList.size(); k++) {
					double sumOfTwoRanges = (candidateList.get(k).getRange() + sourceList.get(i).getRange()) / 1000.0;
					double distance = distanceInKmBetweenEarthCoordinates
							(candidateList.get(k).getX(), candidateList.get(k).getY(),
							sourceList.get(i).getX(), sourceList.get(i).getY());
					if (sumOfTwoRanges < distance)	rangeMatchWithAllCandidates = false;
				}
				if(rangeMatchWithAllCandidates) candidateList.add(sourceList.get(i));
			}
			if(candidateList.size() >= candidateList.get(0).getPeople()) return candidateList;
		}
		return null;
	}// 메소드 끝
	/*
		double sumOfTwoRanges = (candidateList.get(0).getRange() + sourceList.get(i).getRange()) / 1000.0;
		double distance = distanceInKmBetweenEarthCoordinates(candidateList.get(0).getX(), candidateList.get(0).getY(),
				sourceList.get(i).getX(), sourceList.get(i).getY());
		if (sumOfTwoRanges >= distance) {
		}
	 */
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

}
