package crawl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import user.CBoardDTO;
import user.UserDAO;


public class Crawler {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CBoardDTO cboardDTO;
	private static final int HOUR = 36000;
	
	@Async
	@Scheduled(fixedDelay=HOUR*3)
    public void getList() {
        /*
            Document 클래스 : 연결해서 얻어온 HTML 전체 문서
            Element 클래스  : Documnet의 HTML 요소
            Elements 클래스 : Element가 모인 자료형
        */       
        String url = "https://okky.kr/articles/gathering";
        String selector = "div#list-article>div.panel.panel-default>ul>li";
        Document doc = null;    
        
        try {
            doc = Jsoup.connect(url).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Elements titles = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
        
        List<CBoardDTO> list = new ArrayList<CBoardDTO>();
        
        for(Element element: titles) {
        	cboardDTO = new CBoardDTO();
        	String fullSource = element.text();
            System.out.println(fullSource);
        	//#754380 정기모임/스터디 자연어 텍스트 nlp 딥러닝 자연어 처리 스터디 모집 0 0 460 오키동 10 2020-08-05 11:20:32
            
            int bno = Integer.parseInt(element.getElementsByClass("list-group-item-text article-id").text().substring(1));
            System.out.println(bno);
            //#754380
            
            String title = element.getElementsByClass("list-group-item-heading list-group-item-evaluate").text();
            System.out.println(title);
            //[온라인] 알고리즘 스터디 인원 추가합니다
            
            String nickname = element.getElementsByClass("nickname").text();
            System.out.println(nickname);
            //오키동
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String _date = element.getElementsByClass("timeago").text();
            Date date = new Date();
            try {
				date = sdf.parse(_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            System.out.println(date);
            //2020-08-05 10:12:56
            
            System.out.println();
            System.out.println();

            cboardDTO.setBno(bno);
            cboardDTO.setTitle(title);
            cboardDTO.setNickname(nickname);
            cboardDTO.setBoarddate(date);
            list.add(cboardDTO);
        }
        
        
        for(CBoardDTO dto : list)System.out.println(dto.toString());
        
        try{userDAO.crawlInsert(list);
        }catch(Exception e) {
        }
        
        System.out.println("--------------------------");
    }
	
	@Async
	@Scheduled(fixedDelay=HOUR*1)
    public void getContent() {
		List<String> list = userDAO.getEmptyContentBno();
		if(list.size() == 0) return;
		
		List<CBoardDTO> insertList = new ArrayList<CBoardDTO>();
		
		for(int i = 0; i < list.size(); i ++) {
			String url = "https://okky.kr/article/"+list.get(i);
			String selector = "article.content-text";

			Document doc = null;    
			try { doc = Jsoup.connect(url).get();
			} catch (IOException e) { System.out.println(e.getMessage());}
			
			CBoardDTO cboardDTO = new CBoardDTO();
			cboardDTO.setBno(Integer.parseInt(list.get(i)));
			cboardDTO.setContent(doc.select(selector).text());
			
			insertList.add(cboardDTO);
		}

		userDAO.insertContents(insertList);
	}

}
