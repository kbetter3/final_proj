package spring.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("sha256")
public class SHA256Service implements EncryptService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public String encrypt(String origin, String salt, int loop) throws NoSuchAlgorithmException {
		String result = origin;
		for (int i = 0; i < loop; i++) {
			result = sha256(result, salt);
		}
		
		return result;
	}

	public String sha256(String origin, String salt) throws NoSuchAlgorithmException {
		return sha256(origin + salt);
	}
	
	public String sha256(String origin) throws NoSuchAlgorithmException {
		MessageDigest tool = MessageDigest.getInstance("SHA-256");
		
		// 변환된 도구에 문자열 설정
		tool.update(origin.getBytes());
		
		// 변환된 데이터를 받아보자(자리수 변경)
		byte[] data = tool.digest();
		log.debug("data.len = {}", data.length);
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int value = (data[i] & 0xFF) + 0x100;
			buffer.append(Integer.toString(value, 16).substring(1).toUpperCase());
		}
		
		return buffer.toString();
	}
	
	
	
	public String sha512(String origin) throws NoSuchAlgorithmException {
		MessageDigest tool = MessageDigest.getInstance("SHA-512");
		
		// 변환된 도구에 문자열 설정
		tool.update(origin.getBytes());
		
		// 변환된 데이터를 받아보자(자리수 변경)
		byte[] data = tool.digest();
		log.debug("data.len = {}", data.length);
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int value = (data[i] & 0xFF) + 0x100;
			buffer.append(Integer.toString(value, 16).substring(1).toUpperCase());
		}
		
		return buffer.toString();
	}
}
