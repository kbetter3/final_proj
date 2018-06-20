package spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import spring.bean.MainDirectory;
import spring.bean.MusicDto;
import spring.bean.SubmenuType;
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
		
		List<MusicDto> list = null;
		if (type.equals(SubmenuType.CHART_REALTIME)) {
			list = musicDao.getMusicChartOrderByPlayCount(page);			
		} else if (type.equals(SubmenuType.CHART_MONTHLY)) {
		}
		
		if (list != null) {
			for (MusicDto music : list) {
				JSONObject musicObj = music.convertToJSON();
				musicObj.put("albumName", albumDao.getByNo(music.getAlbumNo()));
				jsonArr.put(music);
			}
		}
		
		return jsonArr;
	}

	@Override
	public List<MusicDto> getListByMemberId(String mId) {
		return musicDao.getListByMemberId(mId);
	}

	@Override
	public String saveMusic(MultipartFile mFile) throws IllegalStateException, IOException {
		String fileDir = "://mp3";
		String rname = System.currentTimeMillis() + "-" + UUID.randomUUID();
		String fname = mFile.getOriginalFilename();
		long fsize = mFile.getSize();
		String ftype = mFile.getContentType();
		
		File target = new File(MainDirectory.DIRECTORY + fileDir, rname);
		
		mFile.transferTo(target);
		
		return rname;
	}

}
