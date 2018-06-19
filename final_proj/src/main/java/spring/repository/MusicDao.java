package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.bean.MusicDto;

@Repository
public interface MusicDao {
	public void insert(MusicDto musicDto);
	
	public List<MusicDto> getList();
	public List<MusicDto> getListOrderByReleaseDate();
	public List<MusicDto> getListByName(String name);
	public List<MusicDto> getListByAlbumNo(int albumNo);
	public List<MusicDto> getListByGenre(String genre);
	public List<MusicDto> getListByArtistNo(int artistNo);
	
	public List<MusicDto> getListByMemberId(String mId);
	
	public List<MusicDto> getMusicChartOrderByPlayCount(int page);
	
	public MusicDto getByNo(int no);
}
