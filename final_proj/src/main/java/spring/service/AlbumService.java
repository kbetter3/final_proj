package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.AlbumDto;

@Service
public interface AlbumService {
	public void insert(AlbumDto albumDto);
	public List<AlbumDto> getList();
	public List<AlbumDto> getListByNmae(String name);
	public List<AlbumDto> getListByArtistNo(int artistNo);
	public List<AlbumDto> getListByGenre(String genre);
	public AlbumDto getByNo(int no);
}
