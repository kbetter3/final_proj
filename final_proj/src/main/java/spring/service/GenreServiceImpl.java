package spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.GenreDto;
import spring.repository.GenreDao;

@Service("genreService")
public class GenreServiceImpl implements GenreService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GenreDao genreDao;
	
	@Override
	public void insert(String genre) {
		genreDao.insert(genre);
	}

	@Override
	public List<GenreDto> getList() {
		return genreDao.getList();
	}

	@Override
	public GenreDto getByGenre(String genre) {
		return genreDao.getByGenre(genre);
	}

}
