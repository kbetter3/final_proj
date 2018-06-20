package spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.bean.AlbumDto;
import spring.bean.ArtistDto;
import spring.bean.GenreDto;
import spring.bean.MusicDto;
import spring.bean.RespState;
import spring.service.AlbumService;
import spring.service.ArtistService;
import spring.service.GenreService;
import spring.service.MusicService;
import spring.service.PictureService;
import spring.service.TagService;

@Controller
public class SubmenuController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String submenuDir = "submenu\\";
	
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private GenreService genreService;
	
	@RequestMapping("/header")
	@ResponseBody
	public ResponseEntity<String> header(HttpSession session) throws IOException {
		JSONObject jobj = new JSONObject();
		
		String fname = "";
		
		if (session.getAttribute("uid") != null) {
			// 로그인된 사용자의 요청
			fname = "header\\memberheader.txt";
			return tagService.getTag(fname, "uid", session.getAttribute("uid"));
		} else {
			// 로그인하지 않은 사용자의 요청
			fname = "header\\userheader.txt";
			return tagService.getTag(fname);
		}
	}
	
	
	@GetMapping("/voucher")
	@ResponseBody
	public ResponseEntity<String> voucherTag(HttpSession session) throws IOException {
		if (session.getAttribute("uid") != null) {
			// 정상 접근 - 로그인한 사용자가 접근한 경우
			return tagService.getTag("voucher.txt");
		} else {
			// 로그인하지 않은 사용자가 접근한 경우
			JSONObject jobj = new JSONObject();
			jobj.put("state", RespState.MESSAGE);
			jobj.put("msg", "로그인한 사용자만 접근할 수 있습니다.");
			
			return tagService.getEmptyResponse().body(jobj.toString());
		}
	}
	
	
	
	@PostMapping("/menu")
	@ResponseBody
	public ResponseEntity<String> menu() throws IOException {
		return tagService.getTag("menu\\menu.txt");
	}
	
	@RequestMapping("/submenu")
	@ResponseBody
	public ResponseEntity<String> submenuTag(String fname, HttpSession session) throws IOException {
		String fileDir = "submenu\\";
		
		if (fname.equals("mysubmenu")) {
			// 마이뮤직 메뉴에 접근한 상태
			if (session.getAttribute("uid") != null) {
				// 로그인한 사용자가 요청한 상태
				return tagService.getTag(fileDir + fname + ".txt");
			} else {
				// 로그인하지 않은 사용자가 요청한 상태
				JSONObject jobj = new JSONObject();
				jobj.put("state", RespState.MESSAGE);
				jobj.put("msg", "로그인한 사용자만 사용할 수 있습니다.");
				
				return tagService.getEmptyResponse().body(jobj.toString());
			}
		} else {
			// 마이뮤직을 제외한 메뉴를 접근한 상태
			return tagService.getTag(fileDir + fname + ".txt");
		}
	}
	
	
	// 가수/그룹 관리
	@RequestMapping("/artistmgmt")
	@ResponseBody
	public ResponseEntity<String> artistmgmtTag(HttpSession session) throws IOException {
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.MESSAGE);
		jobj.put("msg", "권한이 없습니다.");
		
		if (session.getAttribute("uid") != null) {
			// 로그인한 사용자가 접근할 때
			switch ((int)session.getAttribute("upower")) {
			case 2:	// 업로더가 접근한 경우
			case 9: // 관리자가 접근한 경우
				return tagService.getTag("artistmgmt.txt");
			default:
				break;
			}
			
			return tagService.getEmptyResponse().body(jobj.toString());
		} else {
			// 로그인하지 않은 사용자가 접근한 경우
			return tagService.getEmptyResponse().body(jobj.toString());
		}
	}
	
	// 가수/그룹 컨텐츠
	@RequestMapping("/artistcont")
	@ResponseBody
	public ResponseEntity<String> artistcont(HttpSession session) {
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.MESSAGE);
		jobj.put("msg", "권한이 없습니다.");
		
		if (session.getAttribute("uid") != null) {
			// 로그인한 사용자가 접근한 경우
			List<ArtistDto> artistList;
			JSONArray artistArr = new JSONArray();
			
			switch ((int)session.getAttribute("upower")) {
			case 2:
				// 업로더가 요청한 경우
				artistList = artistService.getListByMemberId((String)session.getAttribute("uid"));
				break;
			case 9:
				// 관리자가 요청한 경우
				artistList = artistService.getList();
				break;
			default:
				// 비정상 요청 - 권한이 없는 사용자의 요청
				return tagService.getEmptyResponse().body(jobj.toString());
			}
			
			for (ArtistDto artist : artistList) {
				artistArr.put(artist.convertToJSON());
			}
			
			jobj.put("artist", artistArr);
			
			return tagService.getEmptyResponse().body(jobj.toString());
		} else {
			// 로그인하지 않은 사용자가 접근한 경우
			return tagService.getEmptyResponse().body(jobj.toString());
		}
	}
	
	@GetMapping("/mgmt/artistupload")
	@ResponseBody
	public ResponseEntity<String> artistuploadTag() throws IOException {
		return tagService.getTag("artistupload.txt");
	}
	
	
	@PostMapping("/mgmt/artistupload")
	@ResponseBody
	public ResponseEntity<String> artistupload(String artistname, String artistmember, String artistdebut, String artisttype, String artistcompany, MultipartHttpServletRequest mRequest, HttpSession session) throws IllegalStateException, IOException {
		ArtistDto artistDto = new ArtistDto();
		artistDto.setName(artistname);
		artistDto.setMember(artistmember);
		artistDto.setActivityType(artisttype);
		artistDto.setAgency(artistcompany);
		artistDto.setDebutDate(artistdebut);
		artistDto.setThumb(pictureService.saveArtistPic(mRequest.getFile("artistpicture")));
		artistDto.setMId((String)session.getAttribute("uid"));
		
		log.debug("artistdto : {}", artistDto);
		
		artistService.insert(artistDto);
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.SUCCESS);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	
	@RequestMapping("/mgmt/albummgmt")
	@ResponseBody
	public ResponseEntity<String> albummgmtTag() throws IOException {
		return tagService.getTag("albummgmt.txt");
	}
	
	@RequestMapping("/mgmt/albumcont")
	@ResponseBody
	public ResponseEntity<String> albumcont(HttpSession session) {
		JSONObject jobj = new JSONObject();
		
		List<AlbumDto> albumList;
		
		switch ((int)session.getAttribute("upower")) {
		case 2:
			albumList = albumService.getListByMemberId((String)session.getAttribute("uid"));
			break;
			
		case 9:
			albumList = albumService.getList();
			break;
			
		default:
			jobj.put("state", RespState.MESSAGE);
			jobj.put("msg", "권한이 없음");
			return tagService.getEmptyResponse().body(jobj.toString());
		}
		
		JSONArray albumArr = new JSONArray();
		for (AlbumDto album : albumList) {
			albumArr.put(album.convertToJSON());
		}
		
		jobj.put("state", RespState.DATA);
		jobj.put("album", albumArr);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	@GetMapping("/mgmt/albumupload")
	@ResponseBody
	public ResponseEntity<String> albumuploadTag() throws IOException {
		return tagService.getTag("albumupload.txt");
	}

	@PostMapping("/mgmt/albumupload")
	@ResponseBody
	public ResponseEntity<String> albumupload(String albumname, int albumartist, String albumlaunch, String albumgenre, String albumcompany, MultipartHttpServletRequest mRequest, HttpSession session) throws IllegalStateException, IOException {
		AlbumDto albumDto = new AlbumDto();
		albumDto.setName(albumname);
		albumDto.setArtistNo(albumartist);
		albumDto.setGenre(albumgenre);
		albumDto.setAgency(albumcompany);
		albumDto.setReleaseDate(albumlaunch);
		albumDto.setMId((String)session.getAttribute("uid"));
		albumDto.setThumb(pictureService.saveAlbumPic(mRequest.getFile("albumpicture")));
		
		log.debug("albumDto = {}", albumDto);
		
		albumService.insert(albumDto);
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.SUCCESS);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	
	@RequestMapping("/mgmt/musicmgmt")
	@ResponseBody
	public ResponseEntity<String> musicmgmtTag() throws IOException {
		return tagService.getTag("musicmgmt.txt");
	}
	
	@RequestMapping("/mgmt/musiccont")
	@ResponseBody
	public ResponseEntity<String> musiccont(HttpSession session) {
		JSONObject jobj = new JSONObject();
		
		List<MusicDto> musicList;
		switch ((int)session.getAttribute("upower")) {
		case 2:
			musicList = musicService.getListByMemberId((String)session.getAttribute("uid"));
			break;
			
		case 9:
			musicList = musicService.getList();
			break;
			
		default:
			jobj.put("state", RespState.MESSAGE);
			jobj.put("msg", "권한이 없습니다.");
			return tagService.getEmptyResponse().body(jobj.toString());
	
		}
		
		JSONArray musicArr = new JSONArray();
		for (MusicDto music : musicList) {
			musicArr.put(music.convertToJSON());
		}
		
		jobj.put("state", RespState.DATA);
		jobj.put("music", musicArr);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	@GetMapping("/mgmt/musicupload")
	@ResponseBody
	public ResponseEntity<String> musicuploadTag() throws IOException {
		return tagService.getTag("musicupload.txt");
	}
	
	@PostMapping("/mgmt/musicupload")
	@ResponseBody
	public ResponseEntity<String> musicupload(String musicname, int musicalbum, String musiclaunch, String musicgenre, String musictype, String musiclyrics, MultipartHttpServletRequest mRequest, HttpSession session) throws IllegalStateException, IOException {
		MusicDto musicDto = new MusicDto();
		
		musicDto.setName(musicname);
		musicDto.setAlbumNo(musicalbum);
		musicDto.setArtistNo(albumService.getByNo(musicalbum).getArtistNo());
		musicDto.setReleaseDate(musiclaunch);
		musicDto.setGenre(musicgenre);
		musicDto.setLoc(musictype);
		musicDto.setLyrics(musiclyrics);
		musicDto.setMFile(musicService.saveMusic(mRequest.getFile("musicmusic")));
		musicDto.setMId((String)session.getAttribute("uid"));
		
		musicService.insert(musicDto);
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.SUCCESS);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	
	// album select 메뉴 내려주기
	@RequestMapping("/mgmt/albumselectitem")
	@ResponseBody
	public ResponseEntity<String> albumselectitem(HttpSession session) {
		List<ArtistDto> artistList = artistService.getListByMemberId((String)session.getAttribute("uid"));
		JSONArray artistArr = new JSONArray();
		for (ArtistDto artist : artistList) {
			JSONObject artistObj = new JSONObject();
			artistObj.put("artistno", artist.getNo());
			artistObj.put("artistname", artist.getName());
			
			artistArr.put(artistObj);
		}
		
		List<GenreDto> genreList = genreService.getList();
		JSONArray genreArr = new JSONArray();
		for (GenreDto genre : genreList) {
			genreArr.put(genre.getGenre());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.DATA);
		jobj.put("artist", artistArr);
		jobj.put("genre", genreArr);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	// music select 메뉴 내려주기
	@RequestMapping("/mgmt/musicselectitem")
	@ResponseBody
	public ResponseEntity<String> musicselectitem(HttpSession session) {
		List<AlbumDto> albumList = albumService.getListByMemberId((String)session.getAttribute("uid"));
		JSONArray albumArr = new JSONArray();
		for (AlbumDto album : albumList) {
			JSONObject albumObj = new JSONObject();
			albumObj.put("albumno", album.getNo());
			albumObj.put("albumname", album.getName());
			albumObj.put("thumb", album.getThumb());
			
			albumArr.put(albumObj);
		}
		
		List<GenreDto> genreList = genreService.getList();
		JSONArray genreArr = new JSONArray();
		for (GenreDto genre : genreList) {
			genreArr.put(genre.getGenre());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.DATA);
		jobj.put("album", albumArr);
		jobj.put("genre", genreArr);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	
	// Album Thumb 내려주기
	@RequestMapping("/albumthumb")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> albumthumbpic(String fname) throws IOException {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pictureService.loadAlbumPic(fname));
	}
}
