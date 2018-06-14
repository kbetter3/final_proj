package spring.bean;

import lombok.Data;

@Data
public class AlbumDto {
	private int no;
	private String name;
	private int artistNo;
	private String releaseDate;
	private String genre;
	private String agency;
	private String thumb;
	private int likeCount;
	private String regDate;
	private String mId;
}
