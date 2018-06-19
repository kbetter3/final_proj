package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.bean.AlbumDto;

@Repository
public interface AlbumDao {
	public void insert(AlbumDto albumDto);
	public List<AlbumDto> getList();
	public List<AlbumDto> getListByNmae(String name);
	public List<AlbumDto> getListByArtistNo(int artistNo);
	public List<AlbumDto> getListByGenre(String genre);
	public List<AlbumDto> getListByMemberId(String mId);
	public AlbumDto getByNo(int no);
}
