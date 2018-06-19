package spring.bean;

import org.json.JSONObject;

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
	
	public JSONObject convertToJSON() {
		JSONObject jobj = new JSONObject();
		
		jobj.put("no", this.no);
		jobj.put("name", this.name);
		jobj.put("artistno", this.artistNo);
		jobj.put("genre", this.genre);
		jobj.put("releasedate", this.releaseDate);
		jobj.put("agency", this.agency);
		jobj.put("thumb", this.thumb);
		jobj.put("liekcount", this.likeCount);
		jobj.put("regdate", this.regDate);
		jobj.put("mid", this.mId);
		
		return jobj;
	}
}
