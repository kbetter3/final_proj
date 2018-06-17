package spring.service;

import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

@Service
public interface BadReqService {
	public BodyBuilder forbiddenReq();
}
