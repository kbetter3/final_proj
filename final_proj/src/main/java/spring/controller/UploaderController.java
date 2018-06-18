package spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.bean.RespState;
import spring.service.TagService;

@Controller
public class UploaderController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(method= {RequestMethod.GET}, value= {"/uploader/config"})
	public String homoe() {
		return "uploader/config";
	}
	
	
	@RequestMapping("/pictest")
	@ResponseBody
	public ResponseEntity<String> picupload(MultipartFile mFile) throws IllegalStateException, IOException {
		String dir = "C:/tempPic";
		
		log.debug("실행됩니까");
		
//		MultipartFile mFile = mRequest.getFile("pic");
		
		String rname = System.currentTimeMillis() + "-" + UUID.randomUUID();
		String fname = mFile.getOriginalFilename();
		long fsize = mFile.getSize();
		String ftype = mFile.getContentType();
		File target = new File(dir, rname);
		
		mFile.transferTo(target);
		
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.DATA);
		jobj.put("data", rname);
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	@RequestMapping("/pictest2")
	@ResponseBody
	public ResponseEntity<String> pictest2(String fname, HttpServletResponse response) throws IOException {
		JSONObject jobj = new JSONObject();
		StringBuffer picData = new StringBuffer("");
		
		File f = new File("c:/tempPic", fname);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		
		while (br.ready()) {
			picData.append(br.readLine());
		}
		
		jobj.put("state", RespState.DATA);
		jobj.put("data", picData);
		
		br.close();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg; charset=UTF-8").body(jobj.toString());
	}
}
