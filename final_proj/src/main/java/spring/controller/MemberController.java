package spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.bean.MemberDto;
import spring.service.MemberService;

@Controller
public class MemberController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(method= {RequestMethod.GET}, value="/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping(method= {RequestMethod.POST}, value="/register")
	@Transactional
	public String doRegister(@RequestParam String id, @RequestParam String pw, @RequestParam String email) throws NoSuchAlgorithmException, MessagingException {
		MemberDto memberDto = new MemberDto();
		memberDto.setId(id);
		memberDto.setPw(pw);
		memberDto.setEmail(email);
		
		memberService.insert(memberDto);
		
		return "redirect:/chart";
	}
	
	@RequestMapping(method= {RequestMethod.GET}, value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(method= {RequestMethod.POST}, value="/login")
	public String doLogin(@RequestParam String id, @RequestParam String pw, HttpSession session) throws NoSuchAlgorithmException {
		MemberDto memberDto = memberService.login(id, pw);
		
		String returnUrl = "";
		
		if (memberDto != null) {
			// 이메일 미인증 회원 처리해야함
			session.setAttribute("uid", memberDto.getId());
			log.debug("success - {}", memberDto.getId());
			returnUrl = "redirect:/chart";
		} else {
			log.debug("fail to login");
			returnUrl = "redirect:/login";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(method= {RequestMethod.GET}, value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("uid");
		
		return "redirect:/chart";
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
}
