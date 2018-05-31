package spring.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.GenreDto;

@Repository("genreDao")
public class GenreDaoImpl implements GenreDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(String genre) {
		sqlSession.insert("spring.repository.GenreDao.insert", genre);
	}

	@Override
	public GenreDto getByGenre(String genre) {
		return sqlSession.selectOne("spring.repository.GenreDao.getByGenre", genre);
	}

	@Override
	public List<GenreDto> getList() {
		return sqlSession.selectList("spring.repository.GenreDao.getList");
	}

}
