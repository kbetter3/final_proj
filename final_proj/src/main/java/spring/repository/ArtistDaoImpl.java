package spring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.ArtistDto;

@Service("artistDao")
public class ArtistDaoImpl implements ArtistDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(ArtistDto artistDto) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", artistDto.getName());
		map.put("member", artistDto.getMember());
		map.put("debutDate", artistDto.getDebutDate());
		map.put("activityType", artistDto.getActivityType());
		map.put("mid", artistDto.getMId());
		
		if (artistDto.getAgency() != null) {
		map.put("agency", artistDto.getAgency());
		} else {
			map.put("agency", "");
		}
		
		if (artistDto.getThumb() != null) {
			map.put("thumb", artistDto.getThumb());
		} else {
			map.put("thumb", "");
		}
		
		sqlSession.insert("spring.repository.ArtistDao.insert", map);
	}

	@Override
	public List<ArtistDto> getList() {
		return sqlSession.selectList("spring.repository.ArtistDao.getList");
	}

	@Override
	public ArtistDto getByNo(int no) {
		return sqlSession.selectOne("spring.repository.ArtistDao.getByNo", no);
	}

	@Override
	public List<ArtistDto> getListByName(String name) {
		return sqlSession.selectList("spring.repository.ArtistDao.getListByName", name);
	}
	 
}
