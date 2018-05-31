package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.MusicDto;

@Service
public interface MusicService {
public void insert(MusicDto musicDto);
	
	public List<MusicDto> getList();
	public List<MusicDto> getListOrderByReleaseDate();
	public List<MusicDto> getListByName(String name);
	public List<MusicDto> getListByAlbumNo(int albumNo);
	public List<MusicDto> getListByGenre(String genre);
	public List<MusicDto> getListByArtistNo(int artistNo);
	
	public MusicDto getByNo(int no);
}
