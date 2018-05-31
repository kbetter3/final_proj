package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.AlbumDto;
import spring.repository.AlbumDao;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private AlbumDao albumDao;
	
	@Override
	public void insert(AlbumDto albumDto) {
		albumDao.insert(albumDto);
	}

	@Override
	public List<AlbumDto> getList() {
		return albumDao.getList();
	}

	@Override
	public List<AlbumDto> getListByNmae(String name) {
		return albumDao.getListByNmae(name);
	}

	@Override
	public List<AlbumDto> getListByArtistNo(int artistNo) {
		return albumDao.getListByArtistNo(artistNo);
	}

	@Override
	public List<AlbumDto> getListByGenre(String genre) {
		return albumDao.getListByGenre(genre);
	}

	@Override
	public AlbumDto getByNo(int no) {
		return albumDao.getByNo(no);
	}

}
