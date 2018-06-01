package spring.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import spring.bean.MemberDto;

@Service("activationEmail")
public class AccountActivationEmailServiceImpl implements EmailService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private JavaMailSenderImpl sender = new JavaMailSenderImpl();
	
	@Override
	public void sendEmail(MemberDto memberDto) throws MessagingException {
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		
		sender.setUsername("sw4.finalproj@gmail.com");
		sender.setPassword("finalProj04");
		
		Properties option = new Properties();
		option.put("mail.transport.protocol", "smtp");
		option.put("mail.smtp.auth", "true");
		option.put("mail.debug", "true");
		option.put("mail.smtp.starttls.enable", "true");
		
		sender.setJavaMailProperties(option);
		
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setSubject("finalProj 계정 활성화 메일입니다.");
		helper.setFrom("sw4.finalProj");
		helper.setTo(memberDto.getEmail());
		
		String htmlContent = "<a href=\"http://61.75.27.204:14000/final_proj/activation?ak=" + memberDto.getActivationKey() + "\">링크</a>";
		helper.setText(htmlContent, true);
		
		sender.send(message);
	}

}
