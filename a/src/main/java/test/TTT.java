package test;

import java.util.ArrayList;
import java.util.List;

import user.MatchDTO;

public class TTT {
	public static void main(String[] args) {
		MatchDTO a = new MatchDTO();
		MatchDTO b = new MatchDTO();
		System.out.println(a.isVisited());
		System.out.println(a.toString());

		List<MatchDTO> listFromMatch = new ArrayList<>();
		listFromMatch.add(a);
		listFromMatch.add(b);
		System.out.println(listFromMatch.get(0).toString());

		List<MatchDTO> sourceList = new ArrayList<>();
		sourceList = listFromMatch;
		System.out.println(sourceList.get(0).toString());

		sourceList.get(0).setVisited(true);
		System.out.println(sourceList.get(0).isVisited());
		System.out.println(listFromMatch.get(0).isVisited());
		System.out.println(a.isVisited());
		
		sourceList.remove(0);
		System.out.println(a.toString());

	}
}
