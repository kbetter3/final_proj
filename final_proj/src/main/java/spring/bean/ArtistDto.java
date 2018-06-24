package spring.bean;

import org.json.JSONObject;

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
	
	public JSONObject convertToJSON() {
		JSONObject jobj = new JSONObject();
		
		jobj.put("no", this.no);
		jobj.put("name", this.name);
		jobj.put("member", this.member);
//		jobj.put("debutdate", this.debutDate);
		jobj.put("debutdate", this.debutDate.substring(0, 10));
		jobj.put("activitytype", this.activityType);
		jobj.put("agency", this.agency);
		jobj.put("thumb", this.thumb);
		jobj.put("likecount", this.likeCount);
//		jobj.put("regdate", this.regDate);
		jobj.put("regdate", this.regDate.substring(0, 10));
		jobj.put("mid", this.mId);
		
		return jobj;
	}
}
