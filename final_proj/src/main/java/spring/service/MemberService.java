package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.MemberDto;

@Service
public interface MemberService {
	public void insert(MemberDto memberDto);
	
	public List<MemberDto> getList();
	public List<MemberDto> getListOrderById();
	public List<MemberDto> getListOrderByRegDate();
	public List<MemberDto> getListOrderByPower();
	
	public List<MemberDto> getListByPower(int power);
	
	public MemberDto getById(String id);
}
