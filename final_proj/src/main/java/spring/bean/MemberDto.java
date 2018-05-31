package spring.bean;

import lombok.Data;

@Data
public class MemberDto {
	private int no;
	private String id;
	private String pw;
	private int pwLoop;
	private String email;
	private String activationKey;		/* 6자리 랜덤 영숫자조합 */
	private String activationDate;
	private String regDate;
	private String withdrawDate;
	private int power;
	private String expireDate;
	private int downCount;
	private String loginSession;
}
