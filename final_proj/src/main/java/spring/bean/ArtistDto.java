package spring.bean;

import lombok.Data;

@Data
public class ArtistDto {
	private int no;
	private String name;
	private String member;
	private String debutDate;		/* "2018/05/21" */
	private String activityType;	/* "그룹" or "솔로" */
	private String agency;
	private String thumb;
	private int likeCount;			/* default 0 */
	private String regDate;
	private String mId;
}
