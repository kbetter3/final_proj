package spring.bean;

import org.json.JSONObject;

import lombok.Data;

@Data
public class MusicPlayGroupDto {
	private int musicno;
	private int cnt;
	
	public JSONObject convertToJSON() {
		JSONObject jobj = new JSONObject();
		
		jobj.put("no", this.musicno);
		jobj.put("cnt", this.cnt);
		
		return jobj;
	}
}
