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
	private String mFile;
	private String regDate;
	private String mId;
	private String loc;
	
	public JSONObject convertToJSON() {
		JSONObject jobj = new JSONObject();
		
		if (this.statement == 0) {
			jobj.put("no", this.no);
		}
		
		jobj.put("name", this.name);
		jobj.put("albumno", this.albumNo);
		jobj.put("lyrics", this.lyrics);
		jobj.put("releasedate", this.releaseDate);
		jobj.put("genre", this.genre);
//		jobj.put("artistNo", this.artistNo);
		jobj.put("statement", this.statement);
		jobj.put("likecount", this.likeCount);
		jobj.put("playcount", this.playCount);
		jobj.put("downcount", this.downCount);
		jobj.put("mfile", this.mFile);
		jobj.put("regdate", this.regDate);
		jobj.put("mid", this.mId);
		jobj.put("loc", this.loc);
		
		return jobj;
	}
}
