package spring.bean;

import org.json.JSONObject;

import lombok.Data;

@Data
public class MemberDto {
	private int no;
	private String id;
	private String pw;
	private int pwLoop;
	private String salt;				/* SHA2용 salt UUID 사용 */
	private String email;				/* 영숫자 200자 이내 */
	private String activationKey;		/* 256자리 아이디 SHA */
	private String activationDate;
	private String regDate;
	private String withdrawDate;
	private int power;					/* foreign key 설정으로 default 설정이 안되므로 mapper에서 0으로 잡아줘야함 */
	private String expireDate;
	private int downCount;
	private String loginSession;
	
	public JSONObject convertToJSON() {
		JSONObject jobj = new JSONObject();
		
		jobj.put("id", this.id);
		jobj.put("email", this.email);
		jobj.put("regdate", this.regDate.substring(0, 10));
		jobj.put("power", this.power);
		jobj.put("voucher", this.expireDate);
		jobj.put("downcount", this.downCount);
		
		return jobj;
	}
}
