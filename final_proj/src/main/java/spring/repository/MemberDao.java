package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.bean.MemberDto;

@Repository
public interface MemberDao {
	public void insert(MemberDto memberDto);
	
	public List<MemberDto> getList();
	public List<MemberDto> getListOrderById();
	public List<MemberDto> getListOrderByRegDate();
	public List<MemberDto> getListOrderByPower();
	
	public List<MemberDto> getListByPower(int power);
	
	public MemberDto getById(String id);
	public MemberDto getByEmail(String email);
	public MemberDto getByActivationKey(String activationKey);
	
	public void accountActivation(String activationKey);
	
	public void setVoucher(String uid, int day);
	public void addVoucher(String uid, int day);
	
	public void addDownCount(String uid, int downcount);
	public void subDownCount(String uid);
	
	public void updatePower(String uid, int power);
}
