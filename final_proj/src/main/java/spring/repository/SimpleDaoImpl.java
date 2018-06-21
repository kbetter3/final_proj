package spring.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.SimpleDto;

@Repository("simpleDao")
public class SimpleDaoImpl implements SimpleDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public SimpleDto getVoucherDateCalc(String uid) {
		return sqlSession.selectOne("spring.repository.SimpleDao.getVoucherDateCalc", uid);
	}

}
