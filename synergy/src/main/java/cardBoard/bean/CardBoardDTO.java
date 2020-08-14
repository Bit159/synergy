package cardBoard.bean;

import lombok.Data;

@Data
public class CardBoardDTO {
	private int seq;
	private String title;
	private String topic;
	private String location;
	private int people;
	private String content;
}
