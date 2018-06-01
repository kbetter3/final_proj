package spring.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public interface EncryptService {
	public String encrypt(String origin, String salt, int loop) throws NoSuchAlgorithmException;
}
