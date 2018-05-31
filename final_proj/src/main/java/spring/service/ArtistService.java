package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.ArtistDto;

@Service
public interface ArtistService {
	public void insert(ArtistDto artistDto);
	public List<ArtistDto> getList();
	public ArtistDto getByNo(int no);
	public List<ArtistDto> getListByName(String name);
}
