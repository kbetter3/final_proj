package spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import spring.bean.ArtistDto;

@Repository
public interface ArtistDao {
	public void insert(ArtistDto artistDto);
	public List<ArtistDto> getList();
	public ArtistDto getByNo(int no);
	public List<ArtistDto> getListByMemberId(String mId);
	public List<ArtistDto> getListByName(String name);
}
