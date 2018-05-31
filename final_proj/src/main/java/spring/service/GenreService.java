package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.GenreDto;

@Service
public interface GenreService {
	public void insert(String genre);
	public List<GenreDto> getList();
	public GenreDto getByGenre(String genre);
}
