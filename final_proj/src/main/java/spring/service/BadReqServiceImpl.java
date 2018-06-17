package spring.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

@Service("badReqService")
public class BadReqServiceImpl implements BadReqService {

	@Override
	public BodyBuilder forbiddenReq() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).header(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
	}

}
