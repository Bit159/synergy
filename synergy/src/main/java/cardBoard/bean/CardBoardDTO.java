package cardBoard.bean;


import java.util.Date;

import lombok.Data;

@Data
public class CardBoardDTO {
	private int seq;
	private String title;
	private String nickname;
	private String topic;
	private String location;
	private int people;
	private String content;
	private Date registDate;
}