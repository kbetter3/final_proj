package spring.bean;

import lombok.Data;

@Data
public class MusicDto {
	private int no;
	private String name;
	private int albumNo;
	private String lyrics;
	private String releaseDate;
	private String genre;
	private int artistNo;
	private int statement;			/* 0(normal), 1(block), 2(delete), default 0 */
	private int likeCount;
	private int playCount;
	private int downCount;
	private String thumb;
	private String regDate;
	private String mId;
}
