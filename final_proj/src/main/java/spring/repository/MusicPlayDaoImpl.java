package spring.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.MusicPlayDto;

@Repository("musicPlayDao")
public class MusicPlayDaoImpl implements MusicPlayDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(MusicPlayDto musicPlayDto) {
		Map<String, Object> map = new HashMap<>();
		map.put("musicno", musicPlayDto.getMusicno());
		map.put("mId", musicPlayDto.getMid());
		
		sqlSession.insert("spring.repository.MusicPlayDao.insert", map);
	}

}
