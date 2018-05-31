package spring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.AlbumDto;

@Repository("albumDao")
public class AlbumDaoImpl implements AlbumDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(AlbumDto albumDto) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("name", albumDto.getName());
		map.put("artistNo", albumDto.getArtistNo());
		map.put("releaseDate", albumDto.getReleaseDate());
		map.put("genre", albumDto.getGenre());
		
		if (albumDto.getAgency() != null) {
			map.put("agency", albumDto.getAgency());
		} else {
			map.put("agency", "");
		}
		
		if (albumDto.getThumb() != null) {
			map.put("thumb", albumDto.getThumb());
		} else {
			map.put("thumb", "");
		}
		
		sqlSession.insert("spring.repository.AlbumDao.insert", map);
	}

	@Override
	public List<AlbumDto> getList() {
		return sqlSession.selectList("spring.repository.AlbumDao.getList");
	}

	@Override
	public List<AlbumDto> getListByNmae(String name) {
		return sqlSession.selectList("spring.repository.AlbumDao.getListByName", name);
	}

	@Override
	public List<AlbumDto> getListByArtistNo(int artistNo) {
		return sqlSession.selectList("spring.repository.AlbumDao.getListByArtistNo", artistNo);
	}
	
	@Override
	public List<AlbumDto> getListByGenre(String genre) {
		return sqlSession.selectList("spring.repository.AlbumDao.getListByGenre", genre);
	}

	@Override
	public AlbumDto getByNo(int no) {
		return sqlSession.selectOne("spring.repository.AlbumDao.getByNo", no);
	}
}
