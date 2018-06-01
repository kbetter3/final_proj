package spring.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import spring.bean.MemberDto;

@Service
public interface EmailService {
	public void sendEmail(MemberDto memberDto) throws MessagingException;
}
