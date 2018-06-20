package spring.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import spring.bean.MainDirectory;
import spring.bean.MusicDto;
import spring.bean.MusicPlayGroupDto;
import spring.bean.SubmenuType;
import spring.repository.AlbumDao;
import spring.repository.MusicDao;
import spring.repository.MusicPlayGroupDao;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
	
	@Autowired
	private MusicDao musicDao;
	
	@Autowired
	private AlbumDao albumDao;
	
	@Autowired
	private MusicPlayGroupDao musicPlayGroupDao;
	
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
			list = getListByPlayCount(musicPlayGroupDao.getListByPlayDateMonthly());
		} else if (type.equals(SubmenuType.CHART_WEEKLY)) {
			list = getListByPlayCount(musicPlayGroupDao.getListByPlayDateWeekly());
		} else if (type.equals(SubmenuType.CHART_DAILY)) {
			list = getListByPlayCount(musicPlayGroupDao.getListByPlayDateDaily());
		} else if (type.equals(SubmenuType.LASTEST_DOM)) {
			list = musicDao.getListByLoc("dom");
		} else if (type.equals(SubmenuType.LASTEST_INT)) {
			list = musicDao.getListByLoc("int");
		} else if (type.equals(SubmenuType.GENRE_BALLAD)) {
			list = musicDao.getListByGenre("발라드");
		} else if (type.equals(SubmenuType.GENRE_DANCE)) {
			list = musicDao.getListByGenre("댄스");
		} else if (type.equals(SubmenuType.GENRE_HIPHOP)) {
			list = musicDao.getListByGenre("랩/힙합");
		} else if (type.equals(SubmenuType.GENRE_INDIE)) {
			list = musicDao.getListByGenre("인디음악");
		} else if (type.equals(SubmenuType.GENRE_ROCK)) {
			list = musicDao.getListByGenre("록/메탈");
		} else if (type.equals(SubmenuType.GENRE_TEUROTEU)) {
			list = musicDao.getListByGenre("트로트");
		} else if (type.equals(SubmenuType.GENRE_RNB)) {
			list = musicDao.getListByGenre("R&B");
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

	
	private List<MusicDto> getListByPlayCount(List<MusicPlayGroupDto> mList) {
		List<MusicDto>list = new ArrayList<MusicDto>();

		for (MusicPlayGroupDto mpgDto : mList) {
			list.add(musicDao.getByNo(mpgDto.getMusicno()));
		}
		
		return list;
	}
}
