package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.MemberDto;
import spring.repository.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void insert(MemberDto memberDto) {
		memberDao.insert(memberDto);
	}

	@Override
	public List<MemberDto> getList() {
		return memberDao.getList();
	}

	@Override
	public List<MemberDto> getListOrderById() {
		return memberDao.getListOrderById();
	}

	@Override
	public List<MemberDto> getListOrderByRegDate() {
		return memberDao.getListOrderByRegDate();
	}

	@Override
	public List<MemberDto> getListOrderByPower() {
		return memberDao.getListOrderByPower();
	}

	@Override
	public List<MemberDto> getListByPower(int power) {
		return memberDao.getListByPower(power);
	}

	@Override
	public MemberDto getById(String id) {
		return memberDao.getById(id);
	}

}
