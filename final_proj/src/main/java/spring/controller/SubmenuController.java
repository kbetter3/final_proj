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
import org.springframework.http.HttpHeaders;
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
				JSONObject artistObj = new JSONObject();
			}
			
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
	public ResponseEntity<String> artistcont() {
		JSONObject jobj = new JSONObject();
		
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
		albumDto.setThumb(pictureService.saveAlbumPic(mRequest.getFile("albumpicture")));
		
		log.debug("albumupload - {}", albumDto);
//		albumService.insert(albumDto);
		
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
	public ResponseEntity<String> musiccont() {
		JSONObject jobj = new JSONObject();
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	@GetMapping("/mgmt/musicupload")
	@ResponseBody
	public ResponseEntity<String> musicuploadTag() throws IOException {
		return tagService.getTag("musicupload.txt");
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
	
	
	@RequestMapping("/getmusic")
	@ResponseBody
	public ResponseEntity<String> getMusic(String type, int page) {
		JSONObject jobj = new JSONObject();
		String msg = "";
		jobj.put("state", RespState.SUCCESS);
		
		if (type == null) {
			type = "chartrealtime";
		}
		
		jobj.put("music", musicService.getMusicChart(type, page));
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
}
