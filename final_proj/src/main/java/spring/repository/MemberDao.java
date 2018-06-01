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
	public MemberDto getByActivationKey(String activationKey);
	
	public void accountActivation(String activationKey);
}
