package spring.controller;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.bean.MemberDto;
import spring.service.MemberService;

@Controller
public class MainController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(method= {RequestMethod.GET}, value="/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(method= {RequestMethod.GET}, value="/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping(method= {RequestMethod.POST}, value="/register")
	public String doRegister(@RequestParam String id, @RequestParam String pw, @RequestParam String email) throws NoSuchAlgorithmException, MessagingException {
		MemberDto memberDto = new MemberDto();
		memberDto.setId(id);
		memberDto.setPw(pw);
		memberDto.setEmail(email);
		
		memberService.insert(memberDto);
		
		return "redirect:/home";
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
}
