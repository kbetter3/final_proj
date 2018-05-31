package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.bean.GenreDto;

@Repository
public interface GenreDao {
	public void insert(String genre);
	public GenreDto getByGenre(String genre);
	public List<GenreDto> getList();
}
