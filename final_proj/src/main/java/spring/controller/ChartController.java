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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.service.TagService;

@Controller
public class ChartController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TagService tagService;
	
	
	@RequestMapping("/chart")
	@ResponseBody
	public ResponseEntity<String> test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return tagService.getTag("chart.txt");
	}
	
	
}
