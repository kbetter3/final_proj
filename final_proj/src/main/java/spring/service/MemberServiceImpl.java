package spring.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.bean.MemberDto;
import spring.repository.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private EncryptService sha256;
	
	@Autowired
	private EmailService activationEmail; 
	
	@Transactional
	@Override
	public void insert(MemberDto memberDto) throws NoSuchAlgorithmException, MessagingException {
		
		memberDto.setSalt(UUID.randomUUID().toString());
		memberDto.setPwLoop((int)(Math.random() * 9) + 1);
		memberDto.setPw(sha256.encrypt(memberDto.getPw(), memberDto.getSalt(), memberDto.getPwLoop()));
		memberDto.setActivationKey(sha256.encrypt(memberDto.getId(), memberDto.getSalt(), 1));
		
		log.debug("activationKey = {}, {}", memberDto.getSalt(), memberDto.getActivationKey());
		
		memberDao.insert(memberDto);
		
		activationEmail.sendEmail(memberDto);
	}

	@Override
	public List<MemberDto> getList() {
		return memberDao.getList();
	}

	@Override
	public List<MemberDto> getListOrderById() {
		return memberDao.getListOrderById();
	}

	@Override
	public List<MemberDto> getListOrderByRegDate() {
		return memberDao.getListOrderByRegDate();
	}

	@Override
	public List<MemberDto> getListOrderByPower() {
		return memberDao.getListOrderByPower();
	}

	@Override
	public List<MemberDto> getListByPower(int power) {
		return memberDao.getListByPower(power);
	}

	@Override
	public MemberDto getById(String id) {
		return memberDao.getById(id);
	}
	
	@Override
	public MemberDto getByEmail(String email) {
		return memberDao.getByEmail(email);
	}
	
	@Override
	public MemberDto login(String id, String pw) throws NoSuchAlgorithmException {
		MemberDto memberDto = memberDao.getById(id);
		
		if (memberDto == null) {
			return null;
		}
		
		if (memberDto.getPw().equals(sha256.encrypt(pw, memberDto.getSalt(), memberDto.getPwLoop()))) {
			return memberDto;
		} else {
			return null;
		}
	}

	@Override
	public boolean accountActivation(String activationKey) {
		boolean alreadyActivated;		/* 대상 계정이 이미 활성화 상태인지를 검사하여 이미 활성화상태면 1을 아니면 활성화 후 0을 반환 */
		int power = memberDao.getByActivationKey(activationKey).getPower();
		
		if (power == 0) {
			memberDao.accountActivation(activationKey);
			alreadyActivated = false;
		} else {
			alreadyActivated = true;
		}
		
		return alreadyActivated;
	}

	@Override
	public void subDownCount(String uid) {
		memberDao.subDownCount(uid);
	}

}
