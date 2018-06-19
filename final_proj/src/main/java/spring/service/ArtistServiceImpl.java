package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.ArtistDto;
import spring.repository.ArtistDao;

@Service("artistService")
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistDao artistDao;
	
	@Override
	public void insert(ArtistDto artistDto) {
		artistDao.insert(artistDto);
	}

	@Override
	public List<ArtistDto> getList() {
		return artistDao.getList();
	}

	@Override
	public ArtistDto getByNo(int no) {
		return artistDao.getByNo(no);
	}
	
	@Override
	public List<ArtistDto> getListByMemberId(String mId) {
		return artistDao.getListByMemberId(mId);
	}

	@Override
	public List<ArtistDto> getListByName(String name) {
		return artistDao.getListByName(name);
	}
}
