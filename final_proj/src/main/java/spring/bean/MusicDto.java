package spring.bean;

import org.json.JSONObject;

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
	
	public JSONObject convertToJSON() {
		JSONObject jobj = new JSONObject();
		
		if (this.statement != 0) {
			jobj.put("no", this.no);
		}
		
		jobj.put("name", this.name);
		jobj.put("albumNo", this.albumNo);
		jobj.put("lyrics", this.lyrics);
		jobj.put("releaseDate", this.releaseDate);
//		jobj.put("genre", this.genre);
		jobj.put("artistNo", this.artistNo);
		jobj.put("statement", this.statement);
		jobj.put("likeCount", this.likeCount);
		jobj.put("playCount", this.playCount);
		jobj.put("downCount", this.downCount);
		jobj.put("thumb", this.thumb);
		
		return jobj;
	}
}
