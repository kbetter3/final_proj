package spring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.MusicDto;

@Repository("musicDao")
public class MusicDaoImpl implements MusicDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(MusicDto musicDto) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("name", musicDto.getName());
		map.put("albumNo", musicDto.getAlbumNo());
		map.put("mid", musicDto.getMId());
		
		if (musicDto.getLyrics() != null) {
			map.put("lyrics", musicDto.getLyrics());
		} else {
			map.put("lyrics", "");
		}
		
		map.put("releaseDate", musicDto.getReleaseDate());
		map.put("genre", musicDto.getGenre());
		map.put("artistNo", musicDto.getArtistNo());
		
		if (musicDto.getMFile() != null) {
			map.put("mfile", musicDto.getMFile());
		} else {
			map.put("mfile", "");
		}
		
		map.put("loc", musicDto.getLoc());
		
		sqlSession.insert("spring.repository.MusicDao.insert", map);
	}

	@Override
	public List<MusicDto> getList() {
		return sqlSession.selectList("spring.repository.MusicDao.getList");
	}

	@Override
	public List<MusicDto> getListOrderByReleaseDate() {
		return sqlSession.selectList("spring.repository.MusicDao.getListOrderByReleaseDate");
	}

	@Override
	public List<MusicDto> getListByName(String name) {
		return sqlSession.selectList("spring.repository.MusicDao.getListByName", name);
	}

	@Override
	public List<MusicDto> getListByAlbumNo(int albumNo) {
		return sqlSession.selectList("spring.repository.MusicDao.getListByAlbumNo", albumNo);
	}

	@Override
	public List<MusicDto> getListByGenre(String genre) {
		return sqlSession.selectList("spring.repository.MusicDao.getListByGenre", genre);
	}

	@Override
	public List<MusicDto> getListByArtistNo(int artistNo) {
		return sqlSession.selectList("spring.repository.MusicDao.getListByArtistNo", artistNo);
	}
	
	
	
	@Override
	public List<MusicDto> getMusicChartOrderByPlayCount(int page) {
		return sqlSession.selectList("spring.repository.MusicDao.getMusicChartOrderByPlayCount", page);
	}
	
	

	@Override
	public MusicDto getByNo(int no) {
		return sqlSession.selectOne("spring.repository.MusicDao.getByNo", no);
	}

	@Override
	public List<MusicDto> getListByMemberId(String mId) {
		return sqlSession.selectList("spring.repository.MusicDao.getListByMemberId", mId);
	}

	@Override
	public List<MusicDto> getListByLoc(String loc) {
		return sqlSession.selectList("spring.repository.MusicDao.getListByLoc", loc);
	}

}
