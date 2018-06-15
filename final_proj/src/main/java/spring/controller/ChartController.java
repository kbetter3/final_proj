package spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChartController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/chart")
	@ResponseBody
	public ResponseEntity<String> test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		File f = new File("c:\\tags\\chart.txt");
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		StringBuffer buffer = new StringBuffer("");
		char[] buff = new char[1024];
		int rlen;
		
		while (isr.ready()) {
			rlen = isr.read(buff);
			buffer.append(buff, 0, rlen);
			log.debug("buffer : {}", buffer);
			log.debug("rlen : {}", rlen);
		}
		
		br.close();
		
		JSONObject jobj = new JSONObject();
		jobj.put("tags", buffer);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8").body(jobj.toString());
	}
	
	
}
