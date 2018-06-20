package spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.bean.RespState;
import spring.service.PictureService;
import spring.service.TagService;

@Controller
public class UploaderController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(method= {RequestMethod.GET}, value= {"/uploader/config"})
	public String homoe() {
		return "uploader/config";
	}
	
	
	@RequestMapping("/pictest")
	@ResponseBody
	public ResponseEntity<String> picupload(MultipartHttpServletRequest mRequest) throws IllegalStateException, IOException {
		JSONObject jobj = new JSONObject();
		jobj.put("state", RespState.DATA);
		jobj.put("data", pictureService.saveTempPic(mRequest.getFile("pic")));
		
		return tagService.getEmptyResponse().body(jobj.toString());
	}
	
	@RequestMapping("/pictest2")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> pictest2(String fname, HttpServletResponse response) throws IOException {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pictureService.loadTempPic(fname));
	}
	
	
	@RequestMapping("/artistpic")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> artistPic(String fname) throws IOException {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pictureService.loadArtistPic(fname));
	}
}
