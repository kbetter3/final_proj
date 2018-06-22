package spring.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import spring.bean.MemberDto;

@Service
public interface MemberService {
	public void insert(MemberDto memberDto) throws NoSuchAlgorithmException, MessagingException;
	
	public List<MemberDto> getList();
	public List<MemberDto> getListOrderById();
	public List<MemberDto> getListOrderByRegDate();
	public List<MemberDto> getListOrderByPower();
	
	public List<MemberDto> getListByPower(int power);
	
	public MemberDto getById(String id);
	public MemberDto getByEmail(String email);
	public MemberDto login(String id, String pw) throws NoSuchAlgorithmException;
	
	public boolean accountActivation(String activationKey);
	
	public void subDownCount(String uid);
	
	public void updatePower(String uid, int power);
}
