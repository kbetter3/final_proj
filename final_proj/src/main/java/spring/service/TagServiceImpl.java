package spring.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import spring.bean.RespState;

@Service("tagService")
public class TagServiceImpl implements TagService {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public ResponseEntity<String> getTag(String fname) throws IOException {
		return getTag(fname, null, null);
	}
	
	@Override
	public ResponseEntity<String> getTag(String fname, String key, Object value) throws IOException {
//		String fileDir = "C:\\tags\\";
		String fileDir = "D:\\tags\\";
//		String fileDir = "E:\\tags\\";
		File target = new File(fileDir + fname);
		FileInputStream fis = new FileInputStream(target);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		StringBuffer tags = new StringBuffer("");
		
		while (br.ready()) {
			tags.append(br.readLine());
		}
		
		br.close();
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.TAGS);
		jobj.put("tags", tags);
		
		if (key != null && value != null) {
			jobj.put(key, value);
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8").body(jobj.toString());
	}

	@Override
	public BodyBuilder getEmptyResponse() {
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
	}
}
