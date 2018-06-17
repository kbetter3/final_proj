package spring.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
	public ResponseEntity<String> getTag(String fname) throws IOException;
	public ResponseEntity<String> getTag(String fname, String tname, Object tvalue) throws IOException; 
	public BodyBuilder getEmptyResponse();
}
