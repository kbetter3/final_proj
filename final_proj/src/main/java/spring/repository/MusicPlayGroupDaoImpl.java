package spring.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.MusicPlayGroupDto;

@Repository("musicPlayGroupDao")
public class MusicPlayGroupDaoImpl implements MusicPlayGroupDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<MusicPlayGroupDto> getListByPlayDateMonthly() {
		return sqlSession.selectList("spring.repository.MusicPlayGroupDao.getListByPlayDateMonthly");
	}

	@Override
	public List<MusicPlayGroupDto> getListByPlayDateWeekly() {
		return sqlSession.selectList("spring.repository.MusicPlayGroupDao.getListByPlayDateWeekly");
	}

	@Override
	public List<MusicPlayGroupDto> getListByPlayDateDaily() {
		return sqlSession.selectList("spring.repository.MusicPlayGroupDao.getListByPlayDateDaily");
	}
}
