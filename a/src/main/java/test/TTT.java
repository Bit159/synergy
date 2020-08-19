package test;

import java.util.ArrayList;
import java.util.List;

import user.MatchDTO;

public class TTT {
	
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
		
		//기준 옵션의 갯수만큼 반복하며 리스트내에서 같은 옵션이 있는 위시들을 리스트에 담아 "리스트를 담는 리스트"에 담는다.
		for (int i = 0; i < standard_times.size(); i++) {
			//같은 옵션이 있는 위시디를 담는 리스트
			List<MatchDTO> list = new ArrayList<MatchDTO>();
			for (int j = 0; j < time_validation_list.size(); j++) {
				if(standard_times.get(i).equals(time_validation_list.get(j).getTime1()))
					list.add(time_validation_list.get(j));
				if(standard_times.get(i).equals(time_validation_list.get(j).getTime2()))
					list.add(time_validation_list.get(j));
				if(standard_times.get(i).equals(time_validation_list.get(j).getTime3()))
					list.add(time_validation_list.get(j));
			}
			//기준 인원수를 초과한 리스트만 리스트셋에 추가한다.
			if(list.size() >= standardDTO.getPeople()) return_list.add(list);
		}
		//리스트셋을 리턴한다
		return return_list;
	}
	

	//비교기준 career보다 mycareer값이 더 큰 위시들을 리스트에 담아서 리턴
	public List<MatchDTO> careerValidation(MatchDTO standardDTO, List<MatchDTO> career_validation_list){
		List<MatchDTO> return_list = new ArrayList<MatchDTO>();
		for (int i = 0; i < career_validation_list.size(); i++) {
			if(career_validation_list.get(i).getMycareer() >= standardDTO.getCareer())
				return_list.add(career_validation_list.get(i));
		}
		return return_list;
	}

	//비교기준 x,y,range 와 접면이 있는 위시들을 리스트에 담아서 리턴
	public List<MatchDTO> rangeValidation(MatchDTO standardDTO, List<MatchDTO> range_validation_list) {
		List<MatchDTO> return_list = new ArrayList<MatchDTO>();
		for (int i = 0; i < range_validation_list.size(); i++) {
			double sumOfTwoRanges = (standardDTO.getRange() + range_validation_list.get(i).getRange()) / 1000.0;
			double distance = distanceInKmBetweenEarthCoordinates(standardDTO.getX(), standardDTO.getY(),
					range_validation_list.get(i).getX(), range_validation_list.get(i).getY());
			if (sumOfTwoRanges >= distance) return_list.add(range_validation_list.get(i));
		}
		return return_list;
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

	public double degreesToRadians(double degrees) {
		return (degrees * Math.PI) / 180;
	}

}
