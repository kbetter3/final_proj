package spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.bean.MainDirectory;
import spring.bean.MemberDto;
import spring.bean.MusicDto;
import spring.bean.RespState;
import spring.service.BadReqService;
import spring.service.MemberService;
import spring.service.MusicService;
import spring.service.TagService;

@Controller
public class MemberController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MusicService musicService;

	@Autowired
	private TagService tagService;
	
	@Autowired
	private BadReqService badReqService;
	
	
	@GetMapping("/register")
	@ResponseBody
	public ResponseEntity<String> registerTag() throws IOException {
		return tagService.getTag("register.txt");
	}
	
	@PostMapping("/register")
	@ResponseBody
	@Transactional
	public ResponseEntity<String> doRegister(@RequestParam String id, @RequestParam String pw, @RequestParam String email) throws NoSuchAlgorithmException, MessagingException {
		MemberDto memberDto = new MemberDto();
		memberDto.setId(id);
		memberDto.setPw(pw);
		memberDto.setEmail(email);
		
		memberService.insert(memberDto);
		
		JSONObject jobj = new JSONObject();
		jobj.put("rslt", "등록성공");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8").body(jobj.toString());
	}
	
	
	
	@GetMapping("/login")
	@ResponseBody
	public ResponseEntity<String> loginTag(HttpSession session) throws IOException {
		String uid = (String) session.getAttribute("uid");
		
		if (uid != null) {
			return badReqService.forbiddenReq().body(null);
		} else {
			return tagService.getTag("login.txt");
		}
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(String id, String pw, HttpSession session) throws NoSuchAlgorithmException {
		
		if (session.getAttribute("uid") != null) {
			// 이미 로그인한 사용자가 로그인 요청을 보낼 때
			return badReqService.forbiddenReq().body(null);
		} else {
			// 정상적인 로그인 요청
			MemberDto memberDto = memberService.login(id, pw);
			
			JSONObject jobj = new JSONObject();
			String msg = "";
			
			if (memberDto != null) {
				// id & pw가 일치하여 계정 정보가 넘어온 상태
				if (memberDto.getPower() == 0) {
					// 로그인하였으나 이메일 인증을 하지 않은 상태
					msg = "이메일 인증을 완료해 주세요.";
					jobj.put("state", RespState.MESSAGE);
					jobj.put("msg", msg);
					
					return tagService.getEmptyResponse().body(jobj.toString());
				} else {
					// 정상적으로 로그인을 성공한 상태
					session.setAttribute("uid", memberDto.getId());
					session.setAttribute("upower", memberDto.getPower());
					
					jobj.put("state", RespState.SUCCESS);
					
					return tagService.getEmptyResponse().body(jobj.toString());
				}
			} else {
				// 계정 정보가 올바르지 않아 로그인이 실패한 상태
				msg = "로그인 정보가 올바르지 않습니다.";
				jobj.put("state", RespState.MESSAGE);
				jobj.put("msg", msg);
				
				return tagService.getEmptyResponse().body(jobj.toString());
			}
		}
	}
	
	
	
	@RequestMapping("/logout")
	@ResponseBody
	public ResponseEntity<String> logout(HttpSession session) {
		JSONObject jobj = new JSONObject();
		String msg = "";
		
		if (session.getAttribute("uid") != null) {
			// 로그인 상태인 경우 - 정상적인 접근
			session.invalidate();
			
			jobj.put("state", RespState.SUCCESS);
			
			return tagService.getEmptyResponse().body(jobj.toString());
		} else {
			// 로그인 상태가 아닌 경우
			return badReqService.forbiddenReq().body(null);
		}
	}
	
	@RequestMapping(method= {RequestMethod.GET}, value="/activation")
	public String Activation(@RequestParam String ak) {
		boolean alreadyActivated = memberService.accountActivation(ak);
		
		if (alreadyActivated) {
			return "terminated";
		} else {
			return "activated";
		}
	}
	
	@RequestMapping(value="/enctest")
	public String enctest() {
		return "enctest";
	}
	
	@RequestMapping(value="/iddupcheck")
	@ResponseBody
	public void idDupCheck(String id, HttpServletResponse response) throws IOException {
		MemberDto memberDto = memberService.getById(id);
		
		if (memberDto == null) {
			log.debug("memberDto is null");
		} else {
			PrintWriter out = response.getWriter();
			out.println(memberDto.getId());
			log.debug("{}", memberDto);
		}
	}
	
	@RequestMapping(value="/emaildupcheck")
	@ResponseBody
	public void emailDupCheck(String email, HttpServletResponse response) throws IOException {
		MemberDto memberDto = memberService.getByEmail(email);
		
		if (memberDto == null) {
			log.debug("memberDto is null");
		} else {
			PrintWriter out = response.getWriter();
			out.println(memberDto.getEmail());
			log.debug("{}", memberDto);
		}
	}
	
	
	@RequestMapping("/myinfo")
	@ResponseBody
	public ResponseEntity<String> myInfoTag(HttpSession session) throws IOException {
		String fileDir = "submenu\\";
		
		
		if (session.getAttribute("uid") != null) {
			// 로그인된 사용자일 경우
			switch ((int)session.getAttribute("upower")) {
				case 1:
					// 일반회원일 경우
					return tagService.getTag(fileDir + "membersubmenu.txt");
				case 2:
					// 업로더일 경우
					log.debug("업로더냐");
					return tagService.getTag(fileDir + "uploadersubmenu.txt");
				case 9:
					// 관리자일 경우
					return tagService.getTag(fileDir + "adminsubmenu.txt");
				default:
					return badReqService.forbiddenReq().body(null);
			}
		} else {
			// 비정상 접근 (로그인하지 않은 사용자)
			return badReqService.forbiddenReq().body(null);
		}
	}
	
	
	@RequestMapping("/member/musicdown")
	@ResponseBody
	@Transactional
	public ResponseEntity<ByteArrayResource> musicDownload(HttpSession session, int musicno) throws IOException {
		MemberDto memberDto = memberService.getById((String)session.getAttribute("uid"));
		
		if (memberDto.getDownCount() > 0) {
			// 다운로드 횟수가 있을 경우
			memberService.subDownCount(memberDto.getId());
			MusicDto musicDto = musicService.getByNo(musicno);
			
			File mfile = new File(MainDirectory.DIRECTORY + ":/mp3", musicDto.getMFile());
			byte[] data = FileUtils.readFileToByteArray(mfile);
			
			ByteArrayResource resource = new ByteArrayResource(data);
			ResponseEntity<ByteArrayResource> entity = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(musicDto.getName(), "UTF-8") + ".mp3").contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(data.length).body(resource);
			
			return entity;
		} else {
			// 다운로드 횟수가 없을 경우
			return null;
		}
		
	}
}
