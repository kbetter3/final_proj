package spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import spring.bean.RespState;
import spring.service.MusicService;
import spring.service.TagService;

@Controller
public class SubmenuController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String submenuDir = "submenu\\";
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private TagService tagService;
	
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
			switch ((int)session.getAttribute("upower")) {
			case 2:
				// 업로더가 요청한 경우
				break;
			case 9:
				// 관리자가 요청한 경우
				break;
			default:
				// 비정상 요청 - 권한이 없는 사용자의 요청
				break;
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
	public ResponseEntity<String> albumuploadTag() throws IOException {
		return tagService.getTag("albumupload.txt");
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
