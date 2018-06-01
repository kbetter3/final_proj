package spring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.MemberDto;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(MemberDto memberDto) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("id", memberDto.getId());
		map.put("pw", memberDto.getPw());
		map.put("pwLoop", memberDto.getPwLoop());
		map.put("salt", memberDto.getSalt());
		map.put("email", memberDto.getEmail());
		map.put("activationKey", memberDto.getActivationKey());
		
		sqlSession.insert("spring.repository.MemberDao.insert", map);
	}

	@Override
	public List<MemberDto> getList() {
		return sqlSession.selectList("spring.repository.MemberDao.getList");
	}

	@Override
	public List<MemberDto> getListOrderById() {
		return sqlSession.selectList("spring.repository.MemberDao.getListOrderById");
	}

	@Override
	public List<MemberDto> getListOrderByRegDate() {
		return sqlSession.selectList("spring.repository.MemberDao.getListOrderByRegDate");
	}

	@Override
	public List<MemberDto> getListOrderByPower() {
		return sqlSession.selectList("spring.repository.MemberDao.getListOrderByPower");
	}

	@Override
	public List<MemberDto> getListByPower(int power) {
		return sqlSession.selectList("spring.repository.MemberDao.getListByPower", power);
	}

	@Override
	public MemberDto getById(String id) {
		return sqlSession.selectOne("spring.repository.MemberDao.getById", id);
	}
	
	@Override
	public MemberDto getByActivationKey(String activationKey) {
		return sqlSession.selectOne("spring.repository.MemberDao.getByActivationKey", activationKey);
	}

	@Override
	public void accountActivation(String activationKey) {
		sqlSession.update("spring.repository.MemberDao.activation", activationKey);
	}
}
