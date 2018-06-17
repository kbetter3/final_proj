package spring.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.MusicDto;
import spring.repository.AlbumDao;
import spring.repository.MusicDao;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
	
	@Autowired
	private MusicDao musicDao;
	
	@Autowired
	private AlbumDao albumDao;
	
	@Override
	public void insert(MusicDto musicDto) {
		musicDao.insert(musicDto);
	}

	@Override
	public List<MusicDto> getList() {
		return musicDao.getList();
	}

	@Override
	public List<MusicDto> getListOrderByReleaseDate() {
		return musicDao.getListOrderByReleaseDate();
	}

	@Override
	public List<MusicDto> getListByName(String name) {
		return musicDao.getListByName(name);
	}

	@Override
	public List<MusicDto> getListByAlbumNo(int albumNo) {
		return musicDao.getListByAlbumNo(albumNo);
	}

	@Override
	public List<MusicDto> getListByGenre(String genre) {
		return musicDao.getListByGenre(genre);
	}

	@Override
	public List<MusicDto> getListByArtistNo(int artistNo) {
		return musicDao.getListByArtistNo(artistNo);
	}

	@Override
	public MusicDto getByNo(int no) {
		return musicDao.getByNo(no);
	}

	@Override
	public JSONArray getMusicChart(String type, int page) {
		JSONArray jsonArr = new JSONArray();
		
		if (type.equals("chartrealtime")) {
			List<MusicDto> list = musicDao.getMusicChartOrderByPlayCount(page);
			
			for (MusicDto music : list) {
				JSONObject musicObj = music.convertToJSON();
				musicObj.put("albumName", albumDao.getByNo(musicObj.getInt("albumNo")));
				jsonArr.put(music);
			}
		}
		
		return jsonArr;
	}

}
