package test;

import java.util.ArrayList;
import java.util.List;

import user.MatchDTO;

public class TTT {
	public static void main(String[] args) {
		
		double sumOfTwoRanges = (2441.8429515420394 + 2449.6530366581874) / 1000.0;
		double distance = distanceInKmBetweenEarthCoordinates
				(127.03395280998197, 37.507301162990224,
						127.06026559172224, 37.514237412760906);
		System.out.println(sumOfTwoRanges);
		System.out.println(distance);
		
		double d = distance(37.50585031479993, 126.96525615801221, 37.46837100208391, 127.02423619555401);
		System.out.println(d);

	}
	
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
        return dist;
    }
 
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


	
	// 경력 검증 : 비교기준 career보다 mycareer값이 더 큰 위시들을 리스트에 담아서 리턴
	public List<MatchDTO> careerValidation(MatchDTO standardDTO, List<MatchDTO> career_validation_list) {
		List<MatchDTO> return_list = new ArrayList<MatchDTO>();
		for (int i = 0; i < career_validation_list.size(); i++) {
			if (career_validation_list.get(i).getMycareer() >= standardDTO.getCareer())
				return_list.add(career_validation_list.get(i));
		}
		return return_list;
	}


	public static double degreesToRadians(double degrees) {
		return (degrees * Math.PI) / 180;
	}

	public static double distanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
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
